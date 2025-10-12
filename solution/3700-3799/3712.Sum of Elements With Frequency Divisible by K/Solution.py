class Solution:
    def sumDivisibleByK(self, nums: List[int], k: int) -> int:
        cnt = Counter(nums)
        return sum(x * v for x, v in cnt.items() if v % k == 0)
