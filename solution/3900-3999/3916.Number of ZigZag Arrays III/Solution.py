class Solution:
    def countOppositeParity(self, nums: list[int]) -> list[int]:
        n = len(nums)
        ans = [0] * n
        cnt = [0] * 2
        for i in range(n - 1, -1, -1):
            x = nums[i] % 2  # x 的奇偶性
            ans[i] = cnt[1 - x]  # 查询右侧奇偶性不等于 x 的元素个数 
            cnt[x] += 1
        return ans