class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        d = defaultdict(int, {0: 1})
        ans, sum = 0, 0
        for num in nums:
            sum += num
            ans += d[sum - k]
            d[sum] += 1
        return ans
