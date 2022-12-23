class Solution:
    def minimumBoxes(self, n: int) -> int:
        s, k = 0, 1
        while s + k * (k + 1) // 2 <= n:
            s += k * (k + 1) // 2
            k += 1
        k -= 1
        ans = k * (k + 1) // 2
        k = 1
        while s < n:
            ans += 1
            s += k
            k += 1
        return ans
