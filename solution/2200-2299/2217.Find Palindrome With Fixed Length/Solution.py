class Solution:
    def kthPalindrome(self, queries: List[int], intLength: int) -> List[int]:
        l = (intLength + 1) >> 1
        start, end = 10 ** (l - 1), 10**l - 1
        ans = []
        for q in queries:
            v = start + q - 1
            if v > end:
                ans.append(-1)
                continue
            s = str(v)
            s += s[::-1][intLength % 2 :]
            ans.append(int(s))
        return ans
