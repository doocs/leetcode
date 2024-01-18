class Solution:
    def countSubranges(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        s1, s2 = sum(nums1), sum(nums2)
        f = [[0] * (s1 + s2 + 1) for _ in range(n)]
        ans = 0
        mod = 10**9 + 7
        for i, (a, b) in enumerate(zip(nums1, nums2)):
            f[i][a + s2] += 1
            f[i][-b + s2] += 1
            if i:
                for j in range(s1 + s2 + 1):
                    if j >= a:
                        f[i][j] = (f[i][j] + f[i - 1][j - a]) % mod
                    if j + b < s1 + s2 + 1:
                        f[i][j] = (f[i][j] + f[i - 1][j + b]) % mod
            ans = (ans + f[i][s2]) % mod
        return ans
