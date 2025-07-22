class Solution:
    def minOperations(self, nums: List[int]) -> int:
        g = []
        for x in nums:
            l, r = 0, len(g)
            while l < r:
                mid = (l + r) >> 1
                if g[mid] < x:
                    r = mid
                else:
                    l = mid + 1
            if l == len(g):
                g.append(x)
            else:
                g[l] = x
        return len(g)
