class Solution:
    def singleNumber(self, nums: List[int]) -> List[int]:
        eor = 0
        for x in nums:
            eor ^= x
        lowbit = eor & (-eor)
        ans = [0, 0]
        for x in nums:
            if (x & lowbit) == 0:
                ans[0] ^= x
        ans[1] = eor ^ ans[0]
        return ans
