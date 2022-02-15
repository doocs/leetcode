class Solution:
    def singleNumbers(self, nums: List[int]) -> List[int]:
        eor = 0
        for num in nums:
            eor ^= num
        # 找出最右边的 1
        diff = eor & (~eor + 1)
        a = 0
        for num in nums:
            if (num & diff) == 0:
                a ^= num
        b = eor ^ a
        return [a, b]
