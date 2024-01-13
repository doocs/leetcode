class Solution:
    def maxOperations(self, nums: List[int], k: int) -> int:
        cnt = Counter()
        ans = 0
        for x in nums:
            if cnt[k - x]:
                ans += 1
                cnt[k - x] -= 1
            else:
                cnt[x] += 1
        return ans
