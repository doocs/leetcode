class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        m = max(nums).bit_length()
        cnt = [0] * m
        s, i = -1, 0
        ans = inf
        for j, x in enumerate(nums):
            s &= x
            ans = min(ans, abs(s - k))
            for h in range(m):
                if x >> h & 1 ^ 1:
                    cnt[h] += 1
            while i < j and s < k:
                y = nums[i]
                for h in range(m):
                    if y >> h & 1 ^ 1:
                        cnt[h] -= 1
                        if cnt[h] == 0:
                            s |= 1 << h
                i += 1
                ans = min(ans, abs(s - k))
        return ans
