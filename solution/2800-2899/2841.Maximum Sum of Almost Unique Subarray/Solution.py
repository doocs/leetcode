class Solution:
    def maxSum(self, nums: List[int], m: int, k: int) -> int:
        cnt = Counter(nums[:k])
        s = sum(nums[:k])
        ans = 0
        if len(cnt) >= m:
            ans = s
        for i in range(k, len(nums)):
            cnt[nums[i]] += 1
            cnt[nums[i - k]] -= 1
            s += nums[i] - nums[i - k]
            if cnt[nums[i - k]] == 0:
                cnt.pop(nums[i - k])
            if len(cnt) >= m:
                ans = max(ans, s)
        return ans
