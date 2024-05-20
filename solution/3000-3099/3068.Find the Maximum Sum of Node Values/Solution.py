class Solution:
    def maximumValueSum(self, nums: List[int], k: int, edges: List[List[int]]) -> int:
        n = len(nums)
        res = [(x^k)-x for x in nums]
        res.sort(reverse=True)
        ans = sum(nums)
        val = ans
        for i in range(0, n-1, 2):
            ans += res[i] + res[i+1]
            val = max(ans, val)
        return val
