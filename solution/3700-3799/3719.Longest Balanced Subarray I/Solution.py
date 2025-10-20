class Solution:
    def longestBalanced(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            cnt = [0, 0]
            vis = set()
            for j in range(i, n):
                if nums[j] not in vis:
                    cnt[nums[j] & 1] += 1
                    vis.add(nums[j])
                if cnt[0] == cnt[1]:
                    ans = max(ans, j - i + 1)
        return ans
