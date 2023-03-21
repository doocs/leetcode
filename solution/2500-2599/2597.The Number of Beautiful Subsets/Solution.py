class Solution:
    def beautifulSubsets(self, nums: List[int], k: int) -> int:
        def dfs(i: int) -> None:
            nonlocal ans
            if i >= len(nums):
                ans += 1
                return
            dfs(i + 1)
            if cnt[nums[i] + k] == 0 and cnt[nums[i] - k] == 0:
                cnt[nums[i]] += 1
                dfs(i + 1)
                cnt[nums[i]] -= 1

        ans = -1
        cnt = Counter()
        dfs(0)
        return ans
