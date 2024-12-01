class Solution:
    def getLargestOutlier(self, nums: List[int]) -> int:
        s = sum(nums)
        cnt = Counter(nums)
        ans = -inf
        for x, v in cnt.items():
            t = s - x
            if t % 2 or cnt[t // 2] == 0:
                continue
            if x != t // 2 or v > 1:
                ans = max(ans, x)
        return ans
