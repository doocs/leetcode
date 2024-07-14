class Solution:
    def maximumPrimeDifference(self, nums: List[int]) -> int:
        def is_prime(x: int) -> bool:
            if x < 2:
                return False
            return all(x % i for i in range(2, int(sqrt(x)) + 1))

        for i, x in enumerate(nums):
            if is_prime(x):
                for j in range(len(nums) - 1, i - 1, -1):
                    if is_prime(nums[j]):
                        return j - i
