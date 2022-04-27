class Solution:
    def intersection(self, nums: List[List[int]]) -> List[int]:
        cnt = [0] * 1001
        for num in nums:
            for v in num:
                cnt[v] += 1
        n = len(nums)
        return [i for i, v in enumerate(cnt) if v == n]
