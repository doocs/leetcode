class Solution:
    def maxSubarray(self, nums: List[int]) -> int:
        mx = max(nums)
        cnt_s = [0] * (mx << 1 | 1)
        cnt_d = [0] * (mx + 1)
        ans = l = 0

        for r, x in enumerate(nums):
            while cnt_s[x] > 0 or cnt_d[x] > 0:
                y = nums[l]
                l += 1
                for z in nums[l:r]:
                    cnt_s[y + z] -= 1
                    cnt_d[abs(y - z)] -= 1

            for y in nums[l:r]:
                cnt_s[x + y] += 1
                cnt_d[abs(x - y)] += 1

            ans = max(ans, r - l + 1)
        return ans
