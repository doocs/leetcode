class Solution:
    def platesBetweenCandles(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        presum = [0] * (n + 1)
        for i, c in enumerate(s):
            presum[i + 1] = presum[i] + (c == '*')

        left, right = [0] * n, [0] * n
        l = r = -1
        for i, c in enumerate(s):
            if c == '|':
                l = i
            left[i] = l
        for i in range(n - 1, -1, -1):
            if s[i] == '|':
                r = i
            right[i] = r

        ans = [0] * len(queries)
        for k, (l, r) in enumerate(queries):
            i, j = right[l], left[r]
            if i >= 0 and j >= 0 and i < j:
                ans[k] = presum[j] - presum[i + 1]
        return ans
