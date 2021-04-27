class Solution:
    def sumOfUnique(self, nums: List[int]) -> int:
        counter = [0] * 101
        for num in nums:
            counter[num] += 1
        return sum([i for i in range(1, 101) if counter[i] == 1])
