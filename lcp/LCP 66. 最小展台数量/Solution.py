class Solution:
    def minNumBooths(self, demand: List[str]) -> int:
        cnt = Counter()
        ans = 0
        for s in demand:
            for c in s:
                if cnt[c]:
                    cnt[c] -= 1
                else:
                    ans += 1
            for c in s:
                cnt[c] += 1
        return ans
