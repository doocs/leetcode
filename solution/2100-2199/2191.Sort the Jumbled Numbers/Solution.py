class Solution:
    def sortJumbled(self, mapping: List[int], nums: List[int]) -> List[int]:
        m = []
        for i, v in enumerate(nums):
            a, b, t = v, 0, 1
            while 1:
                a, x = divmod(a, 10)
                x = mapping[x]
                b = x * t + b
                t *= 10
                if a == 0:
                    break
            m.append((b, i, v))
        m.sort()
        for i, v in enumerate(m):
            nums[i] = v[2]
        return nums
