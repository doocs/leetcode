class Solution:
    def singleNumbers(self, nums: List[int]) -> List[int]:
        xor_res = 0
        for num in nums:
            xor_res ^= num
        pos = 0
        while (xor_res & 1) == 0:
            pos += 1
            xor_res >>= 1
        
        a = b = 0
        for num in nums:
            t = num >> pos
            if (t & 1) == 0:
                a ^= num
            else:
                b ^= num
        return [a, b]