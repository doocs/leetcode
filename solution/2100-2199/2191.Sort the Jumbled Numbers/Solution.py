class Solution:
    def sortJumbled(self, mapping: List[int], nums: List[int]) -> List[int]:
        def f(x: int) -> int:
            if x == 0:
                return mapping[0]
            y, k = 0, 1
            while x:
                x, v = divmod(x, 10)
                v = mapping[v]
                y = k * v + y
                k *= 10
            return y

        arr = sorted((f(x), i) for i, x in enumerate(nums))
        return [nums[i] for _, i in arr]
