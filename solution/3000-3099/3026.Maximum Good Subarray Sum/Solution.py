class Solution:
    def maximumSubarraySum(self, nums: List[int], k: int) -> int:
        p = {}
        r = float('-inf')
        p[nums[0]] = 0
        s = 0
        n = len(nums)
        for i in range(n):
            s += nums[i]
            if nums[i] - k in p:
                r = max(r, s - p[nums[i] - k])
            if nums[i] + k in p:
                r = max(r, s - p[nums[i] + k])
            if i + 1 == n:
                break
            if nums[i + 1] not in p or p[nums[i + 1]] > s:
                p[nums[i + 1]] = s
        return r if r != float('-inf') else 0
