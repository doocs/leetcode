class Solution:
    def countMagicalPairs(self, s: str) -> int:
        n = len(s)
        ans = 0
        for j in range(n - 1):
            cnt1 = defaultdict(int)
            cnt = [0] * 27
            for i in range(j, -1, -1):
                cnt1[s[i]] += 1
                cnt[len(cnt1)] += 1

            cnt2 = defaultdict(int)
            for k in range(j + 1, n):
                cnt2[s[k]] += 1
                ans += cnt[len(cnt2)]

        return ans
