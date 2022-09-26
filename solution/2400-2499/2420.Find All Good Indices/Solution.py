class Solution:
    def goodIndices(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        decr = [1] * (n + 1)
        incr = [1] * (n + 1)
        for i in range(2, n - 1):
            if nums[i - 1] <= nums[i - 2]:
                decr[i] = decr[i - 1] + 1
        for i in range(n - 3, -1, -1):
            if nums[i + 1] <= nums[i + 2]:
                incr[i] = incr[i + 1] + 1
        return [i for i in range(k, n - k) if decr[i] >= k and incr[i] >= k]
