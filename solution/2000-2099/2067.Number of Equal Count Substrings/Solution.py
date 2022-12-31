class Solution:
    def equalCountSubstrings(self, s: str, count: int) -> int:
        ans = 0
        for x in range(1, 27):
            m = count * x
            if m > len(s):
                break
            cnt = Counter()
            y = 0
            for i, c in enumerate(s):
                cnt[c] += 1
                y += cnt[c] == count
                y -= cnt[c] == count + 1
                j = i - m
                if j >= 0:
                    cnt[s[j]] -= 1
                    y += cnt[s[j]] == count
                    y -= cnt[s[j]] == count - 1
                ans += x == y
        return ans
