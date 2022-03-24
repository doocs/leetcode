class Solution:
    def divideArray(self, nums: List[int]) -> bool:
        dic = Counter(nums)
        for num in dic:
            if dic[num] % 2 != 0:
                return False
        return True
