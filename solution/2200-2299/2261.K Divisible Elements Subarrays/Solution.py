class Solution:
    def countDistinct(self, nums: List[int], k: int, p: int) -> int:
        s = set()
        n = len(nums)
        base1, base2 = 131, 13331
        mod1, mod2 = 10**9 + 7, 10**9 + 9
        for i in range(n):
            h1 = h2 = cnt = 0
            for j in range(i, n):
                cnt += nums[j] % p == 0
                if cnt > k:
                    break
                h1 = (h1 * base1 + nums[j]) % mod1
                h2 = (h2 * base2 + nums[j]) % mod2
                s.add(h1 << 32 | h2)
        return len(s)
