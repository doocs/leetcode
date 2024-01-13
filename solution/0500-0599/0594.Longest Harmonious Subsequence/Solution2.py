class Solution:
    def findLHS(self, nums: List[int]) -> int:
        counter = Counter(nums)
        return max(
            [counter[num] + counter[num + 1] for num in nums if num + 1 in counter],
            default=0,
        )
