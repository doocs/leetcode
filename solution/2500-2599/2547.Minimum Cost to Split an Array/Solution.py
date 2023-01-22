class Solution:
    def minCost(self, nums: List[int], k: int) -> int:
        @cache
        def dfs(i):
            if i >= n:
                return 0
            cnt = Counter()
            one = 0
            ans = inf
            for j in range(i, n):
                cnt[nums[j]] += 1
                if cnt[nums[j]] == 1:
                    one += 1
                elif cnt[nums[j]] == 2:
                    one -= 1
                ans = min(ans, k + j - i + 1 - one + dfs(j + 1))
            return ans

        n = len(nums)
        return dfs(0)
