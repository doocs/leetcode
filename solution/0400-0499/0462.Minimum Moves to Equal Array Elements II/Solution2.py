class Solution:
    def minMoves2(self, nums: List[int]) -> int:
        def move(i):
            v = nums[i]
            a = v * i - s[i]
            b = s[-1] - s[i + 1] - v * (n - i - 1)
            return a + b

        nums.sort()
        s = [0] + list(accumulate(nums))
        n = len(nums)
        return min(move(i) for i in range(n))
