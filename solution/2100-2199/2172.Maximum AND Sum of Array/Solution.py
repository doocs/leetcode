class Solution:
    def maximumANDSum(self, nums: List[int], numSlots: int) -> int:
        n = len(nums)
        m = numSlots << 1
        f = [0] * (1 << m)
        for i in range(1 << m):
            cnt = i.bit_count()
            if cnt > n:
                continue
            for j in range(m):
                if i >> j & 1:
                    f[i] = max(f[i], f[i ^ (1 << j)] + (nums[cnt - 1] & (j // 2 + 1)))
        return max(f)
