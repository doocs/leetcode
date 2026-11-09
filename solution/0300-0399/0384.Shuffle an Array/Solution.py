class Solution:
    def __init__(self, nums: list[int]):
        self.nums = nums
        self.original = nums.copy()

    def reset(self) -> list[int]:
        self.nums = self.original.copy()
        return self.nums

    def shuffle(self) -> list[int]:
        for i in range(len(self.nums)):
            j = random.randrange(i, len(self.nums))
            self.nums[i], self.nums[j] = self.nums[j], self.nums[i]
        return self.nums


# Your Solution object will be instantiated and called as such:
# obj = Solution(nums)
# param_1 = obj.reset()
# param_2 = obj.shuffle()
