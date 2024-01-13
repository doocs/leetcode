class Solution:
    def getAverages(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        ans = [-1] * n
        s = list(accumulate(nums, initial=0))
        for i in range(n):
            if i - k >= 0 and i + k < n:
                ans[i] = (s[i + k + 1] - s[i - k]) // (k << 1 | 1)
        return ans
