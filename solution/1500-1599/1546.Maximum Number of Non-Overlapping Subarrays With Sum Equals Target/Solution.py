class Solution:
    def maxNonOverlapping(self, nums: List[int], target: int) -> int:
        ans = 0
        i, n = 0, len(nums)
        while i < n:
            s = 0
            vis = {0}
            while i < n:
                s += nums[i]
                if s - target in vis:
                    ans += 1
                    break
                i += 1
                vis.add(s)
            i += 1
        return ans
