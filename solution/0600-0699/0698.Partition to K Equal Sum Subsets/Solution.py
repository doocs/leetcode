class Solution:
    def canPartitionKSubsets(self, nums: List[int], k: int) -> bool:
        s = sum(nums)
        target, m = divmod(s, k)
        if m != 0:
            return False

        cur = [0] * k
        n = len(nums)

        def dfs(i: int) -> bool:
            if i == n:
                return True
            for j in range(k):
                if j > 0 and cur[j - 1] == cur[j]:
                    continue
                cur[j] += nums[i]
                if cur[j] <= target and dfs(i + 1):
                    return True
                cur[j] -= nums[i]
            return False

        nums.sort(reverse=True)
        return dfs(0)
