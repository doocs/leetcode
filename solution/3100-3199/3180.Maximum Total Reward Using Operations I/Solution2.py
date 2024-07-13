class Solution:
    def maxTotalReward(self, rewardValues: List[int]) -> int:
        nums = sorted(set(rewardValues))
        m = nums[-1] << 1
        f = [False] * m
        f[0] = True
        for v in nums:
            for j in range(m):
                if 0 <= j - v < v:
                    f[j] |= f[j - v]
        ans = m - 1
        while not f[ans]:
            ans -= 1
        return ans
