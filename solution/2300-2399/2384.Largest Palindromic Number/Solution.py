class Solution:
    def largestPalindromic(self, num: str) -> str:
        cnt = Counter(num)
        ans = ''
        for i in range(9, -1, -1):
            v = str(i)
            if cnt[v] % 2:
                ans = v
                cnt[v] -= 1
                break
        for i in range(10):
            v = str(i)
            if cnt[v]:
                cnt[v] //= 2
                s = cnt[v] * v
                ans = s + ans + s
        return ans.strip('0') or '0'
