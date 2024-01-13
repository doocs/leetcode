class Solution:
    def numberOfBoomerangs(self, points: List[List[int]]) -> int:
        ans = 0
        for p1 in points:
            cnt = Counter()
            for p2 in points:
                d = dist(p1, p2)
                ans += cnt[d]
                cnt[d] += 1
        return ans << 1
