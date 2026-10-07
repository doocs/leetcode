class Solution:
    def minimumCost(self, sentence: str, k: int) -> int:
        nums = [len(w) for w in sentence.split()]
        n = len(nums)
        s = list(accumulate(nums, initial=0))
        f = [0] * n
        for i in range(n - 1, -1, -1):
            if s[n] - s[i] + n - i - 1 <= k:
                continue
            ans = inf
            j = i + 1
            while j < n and (m := s[j] - s[i] + j - i - 1) <= k:
                ans = min(ans, f[j] + (k - m) ** 2)
                j += 1
            f[i] = ans
        return f[0]
