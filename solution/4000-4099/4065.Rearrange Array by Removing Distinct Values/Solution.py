class Solution:
    def rearrangeArray(self, nums: list[int]) -> list[int]:
        mx = max(nums)
        cnt = [0] * (mx + 1)
        for x in nums:
            cnt[x] += 1

        ans = []
        while len(ans) < len(nums):
            for x in range(1, mx + 1):
                if cnt[x]:
                    ans.append(x)
                    cnt[x] -= 1
        return ans
