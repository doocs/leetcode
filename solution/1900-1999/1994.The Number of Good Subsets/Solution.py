class Solution:
    def numberOfGoodSubsets(self, nums: List[int]) -> int:
        counter = Counter(nums)
        mod = 10**9 + 7
        prime = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        n = len(prime)
        dp = [0] * (1 << n)
        dp[0] = 1
        for x in range(2, 31):
            if counter[x] == 0 or x % 4 == 0 or x % 9 == 0 or x % 25 == 0:
                continue
            mask = 0
            for i, p in enumerate(prime):
                if x % p == 0:
                    mask |= 1 << i
            for state in range(1 << n):
                if mask & state:
                    continue
                dp[mask | state] = (dp[mask | state] + counter[x] * dp[state]) % mod
        ans = 0
        for i in range(1, 1 << n):
            ans = (ans + dp[i]) % mod
        for i in range(counter[1]):
            ans = (ans << 1) % mod
        return ans
