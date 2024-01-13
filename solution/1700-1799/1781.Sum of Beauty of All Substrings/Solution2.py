class Solution:
    def beautySum(self, s: str) -> int:
        ans, n = 0, len(s)
        for i in range(n):
            cnt = Counter()
            freq = Counter()
            mi = mx = 1
            for j in range(i, n):
                freq[cnt[s[j]]] -= 1
                cnt[s[j]] += 1
                freq[cnt[s[j]]] += 1

                if cnt[s[j]] == 1:
                    mi = 1
                if freq[mi] == 0:
                    mi += 1
                if cnt[s[j]] > mx:
                    mx = cnt[s[j]]

                ans += mx - mi
        return ans
