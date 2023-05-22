class Solution:
    def sortJumbled(self, mapping: List[int], nums: List[int]) -> List[int]:
        arr = []
        for i, x in enumerate(nums):
            y = mapping[0] if x == 0 else 0
            k = 1
            while x:
                x, v = divmod(x, 10)
                y = mapping[v] * k + y
                k *= 10
            arr.append((y, i))
        arr.sort()
        return [nums[i] for _, i in arr]
