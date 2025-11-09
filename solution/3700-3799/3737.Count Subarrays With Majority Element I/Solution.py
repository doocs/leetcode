class Solution:
    def countMajoritySubarrays(self, nums: List[int], target: int) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            cnt = Counter()
            for j in range(i, n):
                k = j - i + 1
                cnt[nums[j]] += 1
                if cnt[target] > k // 2:
                    ans += 1
        return ans
