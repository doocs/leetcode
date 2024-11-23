class Solution:
    def largestInteger(self, num: int) -> int:
        nums = [int(c) for c in str(num)]
        cnt = Counter(nums)
        idx = [8, 9]
        ans = 0
        for x in nums:
            while cnt[idx[x & 1]] == 0:
                idx[x & 1] -= 2
            ans = ans * 10 + idx[x & 1]
            cnt[idx[x & 1]] -= 1
        return ans
