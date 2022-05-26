class Solution:
    def sumOfUnique(self, nums: List[int]) -> int:
        counter = Counter(nums)
        return sum(num for num, cnt in counter.items() if cnt == 1)
