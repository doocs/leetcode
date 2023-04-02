class Solution:
    def makeSubKSumEqual(self, arr: List[int], k: int) -> int:
        n = len(arr)
        g = gcd(n, k)
        ans = 0
        for i in range(g):
            t = sorted(arr[i:n:g])
            mid = t[len(t) >> 1]
            ans += sum(abs(x - mid) for x in t)
        return ans
