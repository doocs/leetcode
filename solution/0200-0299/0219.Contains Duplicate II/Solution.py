class Solution:
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        helper = {}
        for i, v in enumerate(nums):
            if v in helper and i - helper[v] <= k:
                return True
            helper[v] = i
        return False
