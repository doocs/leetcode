class Solution:
    def maximumSubarraySum(self, nums: List[int], k: int) -> int:
        n = len(nums)
        cnt = Counter(nums[:k])
        s = sum(nums[:k])
        ans = 0
        for i in range(k, n):
            if len(cnt) == k:
                ans = max(ans, s)
            cnt[nums[i]] += 1
            cnt[nums[i - k]] -= 1
            s += nums[i]
            s -= nums[i - k]
            if cnt[nums[i - k]] == 0:
                cnt.pop(nums[i - k])
        if len(cnt) == k:
            ans = max(ans, s)
        return ans
