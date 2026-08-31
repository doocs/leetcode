"""Fetch Guardian / Knight contest rating cutoffs.

CN (leetcode.cn)
    Local ranking is CN-only. The cutoff is the rating of the last user in the
    top 5% / 25% among users with rating >= 1600.

US (leetcode.com)
    Global ranking mixes LCCN accounts. A raw 5% / 25% split of that list does
    not match the badges shown on leetcode.com. The cutoff is the rating of the
    last .com user who actually holds the Guardian / Knight contest badge.
"""

from __future__ import annotations

import argparse
import os
import re
import time
from dataclasses import dataclass
from typing import Callable, Dict, Iterator, List, Optional

import requests
import urllib3

urllib3.disable_warnings()

RATING_CUTOFF = 1600
GUARDIAN_RATIO = 0.05
KNIGHT_RATIO = 0.25
PAGE_SIZE_FALLBACK = 25
RETRY = 3
TIMEOUT = 20
USER_AGENT = (
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) '
    'Chrome/77.0.3865.120 Safari/537.36'
)
# Guardian > Knight > none. Used so a Guardian still counts as "at least Knight".
BADGE_TIER = {'Guardian': 2, 'Knight': 1}

CN_RANKING_QUERY = (
    '{{ localRankingV2(page:{page}) {{ page totalUsers userPerPage '
    'rankingNodes {{ currentRatingRanking currentRating user {{ userSlug }} }} }} }}'
)
US_RANKING_QUERY = (
    '{{ globalRanking(page:{page}) {{ totalUsers userPerPage rankingNodes {{ '
    'currentGlobalRanking currentRating dataRegion user {{ username }} }} }} }}'
)
US_BADGE_QUERY = (
    'query userContestRankingInfo($username: String!) { '
    'userContestRanking(username: $username) { badge { name } } }'
)
CN_WARMUP_QUERY = {
    'operationName': 'questionData',
    'variables': {'titleSlug': 'two-sum'},
    'query': (
        'query questionData($titleSlug: String!) { '
        'question(titleSlug: $titleSlug) { questionFrontendId titleSlug } }'
    ),
}


@dataclass(frozen=True)
class User:
    rank: int
    rating: float
    uid: str
    region: str = ''


@dataclass(frozen=True)
class Cutoff:
    badge: str
    gatekeeper: User
    just_below: Optional[User]
    just_below_badge: str = ''


@dataclass(frozen=True)
class Site:
    key: str
    origin: str
    ranking_field: str
    ranking_query: str
    cookie_attr: str
    warmup: bool
    use_badges: bool


SITES = {
    'CN': Site(
        key='CN',
        origin='https://leetcode.cn',
        ranking_field='localRankingV2',
        ranking_query=CN_RANKING_QUERY,
        cookie_attr='cn',
        warmup=True,
        use_badges=False,
    ),
    'US': Site(
        key='US',
        origin='https://leetcode.com',
        ranking_field='globalRanking',
        ranking_query=US_RANKING_QUERY,
        cookie_attr='en',
        warmup=False,
        use_badges=True,
    ),
}


def load_cookies() -> Dict[str, str]:
    cookies = {'cn': '', 'en': ''}
    env_file = os.path.join(os.path.dirname(os.path.abspath(__file__)), '.env')
    if not os.path.exists(env_file):
        return cookies
    with open(env_file, encoding='utf-8') as f:
        for line in f:
            if line.startswith('COOKIE_CN'):
                cookies['cn'] = '='.join(line.split('=')[1:]).strip().strip('"')
            elif line.startswith('COOKIE_EN'):
                cookies['en'] = '='.join(line.split('=')[1:]).strip().strip('"')
    return cookies


def csrf_from_cookie(cookie: str) -> str:
    m = re.search(r'(?:^|;\s*)csrftoken=([^;]+)', cookie)
    return m.group(1) if m else ''


def holds_badge(name: Optional[str], badge: str) -> bool:
    return BADGE_TIER.get(name or '', 0) >= BADGE_TIER[badge]


def last_true(lo: int, hi: int, pred: Callable[[int], bool]) -> int:
    """Largest x in [lo, hi] such that pred(x) is True. pred must be prefix-true."""
    while lo < hi:
        mid = (lo + hi + 1) // 2
        if pred(mid):
            lo = mid
        else:
            hi = mid - 1
    return lo


class GraphQL:
    def __init__(self, origin: str, cookie: str):
        self.origin = origin
        self.session = requests.Session()
        self.session.verify = False
        headers = {
            'User-Agent': USER_AGENT,
            'Connection': 'keep-alive',
            'Content-Type': 'application/json',
            'Referer': origin + '/contest/',
            'Origin': origin,
        }
        if cookie:
            headers['cookie'] = cookie
            csrf = csrf_from_cookie(cookie)
            if csrf:
                headers['x-csrftoken'] = csrf
                headers['x-requested-with'] = 'XMLHttpRequest'
        self.session.headers.update(headers)

    def post(
        self, url: str, payload: dict, retries: int = RETRY, quiet: bool = False
    ) -> Optional[dict]:
        last_err: object = None
        for attempt in range(retries):
            try:
                resp = self.session.post(url, json=payload, timeout=TIMEOUT)
                if resp.status_code != 200:
                    last_err = f'HTTP {resp.status_code}'
                    time.sleep(min(8, 2**attempt))
                    continue
                try:
                    data = resp.json()
                except ValueError:
                    last_err = 'non-JSON (possible WAF challenge)'
                    time.sleep(min(8, 2**attempt))
                    continue
                if data.get('errors'):
                    last_err = data['errors']
                    time.sleep(1)
                    continue
                return data
            except Exception as e:
                last_err = e
                time.sleep(min(8, 2**attempt))
        if not quiet:
            print(f'GraphQL 请求失败 {url}: {last_err}')
        return None


class Ranking:
    def __init__(self, site: Site, cookies: Dict[str, str]):
        self.site = site
        self.graphql = GraphQL(site.origin, cookies[site.cookie_attr])
        self.ranking_url = site.origin + '/graphql'
        self.total_users = 0
        self.user_per_page = PAGE_SIZE_FALLBACK
        self._pages: Dict[int, List[User]] = {}
        self._badges: Dict[str, Optional[str]] = {}
        if site.warmup:
            # leetcode.cn WAF often blocks the first ranking query unless a
            # normal GraphQL request has already succeeded on this session.
            for _ in range(5):
                if self.graphql.post(
                    self.ranking_url, CN_WARMUP_QUERY, retries=2, quiet=True
                ):
                    break
                time.sleep(2)

    def load_page(self, page: int) -> List[User]:
        if page in self._pages:
            return self._pages[page]
        payload = {'query': self.site.ranking_query.format(page=page)}
        data = self.graphql.post(self.ranking_url, payload)
        if not data or not data.get('data'):
            print(f'[{self.site.key}] 加载第 {page} 页失败')
            return []
        body = data['data'].get(self.site.ranking_field) or {}
        self.total_users = int(body.get('totalUsers') or self.total_users)
        self.user_per_page = int(
            body.get('userPerPage') or self.user_per_page or PAGE_SIZE_FALLBACK
        )
        users: List[User] = []
        for node in body.get('rankingNodes') or []:
            parsed = self._parse_node(node)
            if parsed:
                users.append(parsed)
        self._pages[page] = users
        return users

    def _parse_node(self, node: dict) -> Optional[User]:
        user = node.get('user') or {}
        if self.site.key == 'CN':
            uid = user.get('userSlug')
            rank = node.get('currentRatingRanking')
        else:
            uid = user.get('username')
            rank = node.get('currentGlobalRanking')
        rating = node.get('currentRating')
        if not uid or rank is None or rating is None:
            return None
        return User(
            rank=int(rank),
            rating=float(rating),
            uid=uid,
            region=node.get('dataRegion') or '',
        )

    def user_at(self, rank: int) -> Optional[User]:
        if rank < 1:
            return None
        page_no = (rank - 1) // self.user_per_page + 1
        offset = (rank - 1) % self.user_per_page
        users = self.load_page(page_no)
        if offset >= len(users):
            return None
        return users[offset]

    def count_rating_ge(self, cutoff: float = RATING_CUTOFF) -> int:
        first = self.load_page(1)
        if not first:
            return 0
        total_pages = max(
            1, (self.total_users + self.user_per_page - 1) // self.user_per_page
        )

        def page_still_ge(page_no: int) -> bool:
            users = self.load_page(page_no)
            print(
                f'[{self.site.key}] 第 {page_no} 页',
                users[:1],
                f'(共 {len(users)} 条)',
            )
            return bool(users) and users[0].rating >= cutoff

        page_no = last_true(1, total_pages, page_still_ge)
        users = self.load_page(page_no)
        if not users:
            return 0
        idx = last_true(0, len(users) - 1, lambda i: users[i].rating >= cutoff)
        if users[idx].rating < cutoff:
            return 0
        return users[idx].rank

    def contest_badge(self, uid: str) -> Optional[str]:
        """None = no .com contest profile; '' = profile exists but no contest badge."""
        if uid in self._badges:
            return self._badges[uid]
        data = self.graphql.post(
            self.ranking_url,
            {'query': US_BADGE_QUERY, 'variables': {'username': uid}},
        )
        if not data:
            self._badges[uid] = None
            return None
        ranking = (data.get('data') or {}).get('userContestRanking')
        if ranking is None:
            self._badges[uid] = None
            return None
        badge = (ranking.get('badge') or {}).get('name') or ''
        self._badges[uid] = badge
        return badge

    def _evaluable_us(self, rank: int) -> Optional[User]:
        user = self.user_at(rank)
        if not user or user.region == 'CN':
            return None
        if self.contest_badge(user.uid) is None:
            return None
        return user

    def _nearby_ranks(self, mid: int, lo: int, hi: int) -> Iterator[int]:
        yield mid
        for d in range(1, self.user_per_page + 1):
            if mid - d >= lo:
                yield mid - d
            if mid + d <= hi:
                yield mid + d

    def last_us_with_badge(self, lo: int, hi: int, badge: str) -> Optional[User]:
        ans = None
        while lo <= hi:
            mid = (lo + hi) // 2
            found = None
            for rank in self._nearby_ranks(mid, lo, hi):
                user = self._evaluable_us(rank)
                if user:
                    found = user
                    break
            if found is None:
                break
            name = self.contest_badge(found.uid)
            print(
                f'[{self.site.key}] rank {found.rank}: {found.uid} '
                f'{found.rating:.3f} badge={name!r}'
            )
            if holds_badge(name, badge):
                ans = found
                lo = found.rank + 1
            else:
                hi = found.rank - 1
        return ans

    def next_evaluable_us(self, start: int, limit: int = 40) -> Optional[User]:
        for rank in range(start, start + limit):
            user = self._evaluable_us(rank)
            if user:
                return user
        return None

    def percentile_cutoffs(self, total: int) -> List[Cutoff]:
        result = []
        for badge, ratio in (('Guardian', GUARDIAN_RATIO), ('Knight', KNIGHT_RATIO)):
            last_rank = max(1, int(total * ratio))
            gatekeeper = self.user_at(last_rank)
            if not gatekeeper:
                continue
            result.append(
                Cutoff(
                    badge=badge,
                    gatekeeper=gatekeeper,
                    just_below=self.user_at(last_rank + 1),
                )
            )
        return result

    def badge_cutoffs(self, total: int) -> List[Cutoff]:
        result = []
        for badge, ratio in (('Guardian', GUARDIAN_RATIO), ('Knight', KNIGHT_RATIO)):
            lo = max(1, int(total * ratio * 0.8))
            hi = min(total, int(total * ratio * 1.3))
            gatekeeper = self.last_us_with_badge(lo, hi, badge)
            if not gatekeeper:
                continue
            just_below = self.next_evaluable_us(gatekeeper.rank + 1)
            below_badge = ''
            if just_below:
                below_badge = self.contest_badge(just_below.uid) or ''
            result.append(
                Cutoff(
                    badge=badge,
                    gatekeeper=gatekeeper,
                    just_below=just_below,
                    just_below_badge=below_badge,
                )
            )
        return result

    def fetch_cutoffs(self) -> Optional[List[Cutoff]]:
        total = self.count_rating_ge()
        if not total:
            print(f'[{self.site.key}] 未能获取 {RATING_CUTOFF} 分以上人数')
            return None
        print(f'[{self.site.key}] {RATING_CUTOFF} 分以上共计 {total} 人')
        if self.site.use_badges:
            return self.badge_cutoffs(total)
        return self.percentile_cutoffs(total)


def print_cutoffs(site_key: str, cutoffs: List[Cutoff]) -> None:
    print(f'\n[{site_key}] 分数线')
    for item in cutoffs:
        g = item.gatekeeper
        print(
            f'  {item.badge:<9} 守门员  rank={g.rank:<6} rating={g.rating:.3f}  {g.uid}'
        )
        b = item.just_below
        if not b:
            continue
        note = f'  badge={item.just_below_badge!r}' if site_key == 'US' else ''
        print(
            f'  {item.badge:<9} 差一线  rank={b.rank:<6} rating={b.rating:.3f}  {b.uid}{note}'
        )


def run(region: str) -> Dict[str, List[Cutoff]]:
    cookies = load_cookies()
    keys = ['CN', 'US'] if region == 'all' else [region]
    out: Dict[str, List[Cutoff]] = {}
    for i, key in enumerate(keys):
        if i:
            print('\n------------------------------\n')
        ranking = Ranking(SITES[key], cookies)
        cutoffs = ranking.fetch_cutoffs()
        if cutoffs:
            print_cutoffs(key, cutoffs)
            out[key] = cutoffs
    return out


if __name__ == '__main__':
    parser = argparse.ArgumentParser(
        description='Fetch Guardian / Knight rating cutoffs'
    )
    parser.add_argument('--region', choices=['CN', 'US', 'all'], default='all')
    args = parser.parse_args()
    results = run(args.region)
    print('\nREADME 分数线（守门员分数，四舍五入到两位）：')
    for key, cutoffs in results.items():
        parts = [
            f'{item.badge} >= {round(item.gatekeeper.rating, 2):.2f}'
            for item in cutoffs
        ]
        label = '国服' if key == 'CN' else '全球'
        print(f'  {label}  ' + '，'.join(parts))
