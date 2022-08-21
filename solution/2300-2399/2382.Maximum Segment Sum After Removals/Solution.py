class Solution:
    def maximumSegmentSum(self, nums: List[int], removeQueries: List[int]) -> List[int]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def merge(a, b):
            pa, pb = find(a), find(b)
            p[pa] = pb
            s[pb] += s[pa]

        n = len(nums)
        p = list(range(n))
        s = [0] * n
        ans = [0] * n
        mx = 0
        for j in range(n - 1, 0, -1):
            i = removeQueries[j]
            s[i] = nums[i]
            if i and s[find(i - 1)]:
                merge(i, i - 1)
            if i < n - 1 and s[find(i + 1)]:
                merge(i, i + 1)
            mx = max(mx, s[find(i)])
            ans[j - 1] = mx
        return ans
