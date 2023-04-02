class Solution:
    def makeSubKSumEqual(self, arr: List[int], k: int) -> int:
        n = len(arr)
        if n == k:
            return 0
        g = gcd(n, k)
        ans = 0
        for i in range(g):
            t = sorted(arr[j] for j in range(i, n, g))
            mid = t[len(t) >> 1]
            s = sum(abs(x - mid) for x in t)
            ans += s
        return ans
