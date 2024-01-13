class Solution:
    def wiggleSort(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        bucket = [0] * 5001
        for v in nums:
            bucket[v] += 1
        n = len(nums)
        j = 5000
        for i in range(1, n, 2):
            while bucket[j] == 0:
                j -= 1
            nums[i] = j
            bucket[j] -= 1
        for i in range(0, n, 2):
            while bucket[j] == 0:
                j -= 1
            nums[i] = j
            bucket[j] -= 1
