class Solution:
    def destroyTargets(self, nums: List[int], space: int) -> int:
        cnt = Counter(v % space for v in nums)
        ans = mx = 0
        for v in nums:
            t = cnt[v % space]
            if t > mx or (t == mx and v < ans):
                ans = v
                mx = t
        return ans
