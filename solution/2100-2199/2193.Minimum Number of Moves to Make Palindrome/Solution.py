class Solution:
    def minMovesToMakePalindrome(self, s: str) -> int:
        cs = list(s)
        ans, n = 0, len(s)
        i, j = 0, n - 1
        while i < j:
            even = False
            for k in range(j, i, -1):
                if cs[i] == cs[k]:
                    even = True
                    while k < j:
                        cs[k], cs[k + 1] = cs[k + 1], cs[k]
                        k += 1
                        ans += 1
                    j -= 1
                    break
            if not even:
                ans += n // 2 - i
            i += 1
        return ans
