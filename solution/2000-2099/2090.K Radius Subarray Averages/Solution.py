class Solution:
    def getAverages(self, nums: List[int], k: int) -> List[int]:
        k = k << 1 | 1
        n = len(nums)
        ans = [-1] * n
        if k > n:
            return ans
        s = sum(nums[:k])
        j = k // 2
        ans[j] = s // k
        for i in range(k, n):
            j += 1
            s += nums[i] - nums[i - k]
            ans[j] = s // k
        return ans
