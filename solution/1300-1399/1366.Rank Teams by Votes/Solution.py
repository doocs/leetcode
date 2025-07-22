class Solution:
    def rankTeams(self, votes: List[str]) -> str:
        m = len(votes[0])
        cnt = defaultdict(lambda: [0] * m)
        for vote in votes:
            for i, c in enumerate(vote):
                cnt[c][i] += 1
        return "".join(sorted(cnt, key=lambda c: (cnt[c], -ord(c)), reverse=True))
