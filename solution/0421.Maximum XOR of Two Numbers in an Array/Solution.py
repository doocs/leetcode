class Solution:
    def findMaximumXOR(self, nums: List[int]) -> int:
        max = 0
        mask = 0
        for i in range(30, -1, -1):
            current = 1 << i
            # 期望的二进制前缀
            mask = mask ^ current
            # 在当前前缀下, 数组内的前缀位数所有情况集合
            _set = set()
            for num in nums:
                _set.add(num & mask)
            # 期望最终异或值的从右数第i位为1, 再根据异或运算的特性推算假设是否成立
            flag = max | current
            for prefix in _set:
                if prefix ^ flag in _set:
                    max = flag
                    break
        return max