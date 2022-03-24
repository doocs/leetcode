class Solution:
    def divideArray(self, nums: List[int]) -> bool:
        dic = {}
        for num in nums:
            if num in dic:
                dic[num] += 1
            else:
                dic[num] = 1
        for num in dic:
            if dic[num] % 2 != 0:
                return False
        return True
