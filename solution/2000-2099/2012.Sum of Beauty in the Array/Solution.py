class Solution:
    def sumOfBeauties(self, nums: List[int]) -> int:
        n = len(nums)
        lmx = [0] * n
        for i in range(1, n):
            lmx[i] = max(lmx[i - 1], nums[i - 1])
        rmi = [100001] * n
        for i in range(n - 2, -1, -1):
            rmi[i] = min(rmi[i + 1], nums[i + 1])
        ans = 0
        for i in range(1, n - 1):
            if lmx[i] < nums[i] < rmi[i]:
                ans += 2
            elif nums[i - 1] < nums[i] < nums[i + 1]:
                ans += 1
        return ans
