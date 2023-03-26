class Solution:
    def minOperations(self, nums: List[int], queries: List[int]) -> List[int]:
        nums.sort()
        s = list(accumulate(nums, initial=0))
        ans = []
        for x in queries:
            i = bisect_left(nums, x + 1)
            t = s[-1] - s[i] - (len(nums) - i) * x
            i = bisect_left(nums, x)
            t += x * i - s[i]
            ans.append(t)
        return ans
