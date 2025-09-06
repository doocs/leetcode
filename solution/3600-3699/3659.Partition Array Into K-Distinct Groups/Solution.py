class Solution:
    def partitionArray(self, nums: List[int], k: int) -> bool:
        m, mod = divmod(len(nums), k)
        if mod:
            return False
        return max(Counter(nums).values()) <= m
