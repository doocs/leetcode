class Solution:
    def findLHS(self, nums: List[int]) -> int:
        ans = 0
        counter = Counter(nums)
        for num in nums:
            if num + 1 in counter:
                ans = max(ans, counter[num] + counter[num + 1])
        return ans
