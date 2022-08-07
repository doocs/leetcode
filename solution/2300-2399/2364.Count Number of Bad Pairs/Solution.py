class Solution:
    def countBadPairs(self, nums: List[int]) -> int:
        arr = [i - v for i, v in enumerate(nums)]
        cnt = Counter(arr)
        n = len(arr)
        return sum(v * (n - v) for v in cnt.values()) >> 1
