class Solution:
    def maxNonOverlapping(self, nums: List[int], target: int) -> int:
        i, n = 0, len(nums)
        ans = 0
        while i < n:
            s = 0
            seen = {0}
            while i < n:
                s += nums[i]
                if s - target in seen:
                    ans += 1
                    break
                i += 1
                seen.add(s)
            i += 1
        return ans
