class Solution:
    def kLengthApart(self, nums: List[int], k: int) -> bool:
        j = -inf
        for i, x in enumerate(nums):
            if x:
                if i - j - 1 < k:
                    return False
                j = i
        return True
