class Solution:
    def totalWaviness(self, num1: int, num2: int) -> int:
        def f(x: int) -> int:
            nums = []
            while x:
                nums.append(x % 10)
                x //= 10
            m = len(nums)
            if m < 3:
                return 0
            s = 0
            for i in range(1, m - 1):
                if nums[i] > nums[i - 1] and nums[i] > nums[i + 1]:
                    s += 1
                elif nums[i] < nums[i - 1] and nums[i] < nums[i + 1]:
                    s += 1
            return s

        return sum(f(x) for x in range(num1, num2 + 1))
