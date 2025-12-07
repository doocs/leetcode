class Solution:
    def sortByReflection(self, nums: List[int]) -> List[int]:
        def f(x: int) -> int:
            y = 0
            while x:
                y = y << 1 | (x & 1)
                x >>= 1
            return y

        nums.sort(key=lambda x: (f(x), x))
        return nums
