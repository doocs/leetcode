class Solution:
    def minAllOneMultiple(self, k: int) -> int:
        if k % 2 == 0:
            return -1
        x = 1 % k
        ans = 1
        for _ in range(k):
            x = (x * 10 + 1) % k
            ans += 1
            if x == 0:
                return ans
        return -1
