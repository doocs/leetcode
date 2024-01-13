class Solution:
    def reductionOperations(self, nums: List[int]) -> int:
        ans = cnt = 0
        for _, v in sorted(Counter(nums).items()):
            ans += cnt * v
            cnt += 1
        return ans
