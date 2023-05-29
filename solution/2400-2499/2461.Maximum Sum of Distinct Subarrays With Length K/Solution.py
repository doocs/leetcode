class Solution:
    def maximumSubarraySum(self, nums: List[int], k: int) -> int:
        cnt = Counter(nums[:k])
        s = sum(nums[:k])
        ans = s if len(cnt) == k else 0
        for i in range(k, len(nums)):
            cnt[nums[i]] += 1
            s += nums[i]
            cnt[nums[i - k]] -= 1
            s -= nums[i - k]
            if cnt[nums[i - k]] == 0:
                del cnt[nums[i - k]]
            if len(cnt) == k:
                ans = max(ans, s)
        return ans
