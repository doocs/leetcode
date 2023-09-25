"""
DP, Z-algorithm, Fast mod.
Approach
How to represent a string?
Each operation is just a rotation. Each result string can be represented by an integer from 0 to n - 1. Namely, it's just the new index of s[0].
How to find the integer(s) that can represent string t?
Create a new string s + t + t (length = 3 * n).
Use Z-algorithm (or KMP), for each n <= index < 2 * n, calculate the maximum prefix length that each substring starts from index can match, if the length >= n, then (index - n) is a valid integer representation.
How to get the result?
It's a very obvious DP.
If we use an integer to represent a string, we only need to consider the transition from zero to non-zero and from non-zero to zero. In other words, all the non-zero strings should have the same result.
So let dp[t][i = 0/1] be the number of ways to get the zero/nonzero string
after excatly t steps.
Then
dp[t][0] = dp[t - 1][1] * (n - 1).
All the non zero strings can make it.
dp[t][1] = dp[t - 1][0] + dp[t - 1] * (n - 2).
For a particular non zero string, all the other non zero strings and zero string can make it.
We have dp[0][0] = 1 and dp[0][1] = 0
Use matrix multiplication.
How to calculate dp[k][x = 0, 1] faster?
Use matrix multiplication
vector (dp[t - 1][0], dp[t - 1][1])
multiplies matrix
[0 1]
[n - 1 n - 2]
== vector (dp[t][0], dp[t - 1][1]).
So we just need to calculate the kth power of the matrix which can be done by fast power algorith.
Complexity
Time complexity:
O(n + logk)
Space complexity:
O(n)
"""


class Solution:
    M: int = 1000000007

    def add(self, x: int, y: int) -> int:
        x += y
        if x >= self.M:
            x -= self.M
        return x

    def mul(self, x: int, y: int) -> int:
        return int(x * y % self.M)

    def getZ(self, s: str) -> List[int]:
        n = len(s)
        z = [0] * n
        left = right = 0
        for i in range(1, n):
            if i <= right and z[i - left] <= right - i:
                z[i] = z[i - left]
            else:
                z_i = max(0, right - i + 1)
                while i + z_i < n and s[i + z_i] == s[z_i]:
                    z_i += 1
                z[i] = z_i
            if i + z[i] - 1 > right:
                left = i
                right = i + z[i] - 1
        return z

    def matrixMultiply(self, a: List[List[int]], b: List[List[int]]) -> List[List[int]]:
        m = len(a)
        n = len(a[0])
        p = len(b[0])
        r = [[0] * p for _ in range(m)]
        for i in range(m):
            for j in range(p):
                for k in range(n):
                    r[i][j] = self.add(r[i][j], self.mul(a[i][k], b[k][j]))
        return r

    def matrixPower(self, a: List[List[int]], y: int) -> List[List[int]]:
        n = len(a)
        r = [[0] * n for _ in range(n)]
        for i in range(n):
            r[i][i] = 1
        x = [a[i][:] for i in range(n)]
        while y > 0:
            if y & 1:
                r = self.matrixMultiply(r, x)
            x = self.matrixMultiply(x, x)
            y >>= 1
        return r

    def numberOfWays(self, s: str, t: str, k: int) -> int:
        n = len(s)
        dp = self.matrixPower([[0, 1], [n - 1, n - 2]], k)[0]
        s += t + t
        z = self.getZ(s)
        m = n + n
        result = 0
        for i in range(n, m):
            if z[i] >= n:
                result = self.add(result, dp[0] if i - n == 0 else dp[1])
        return result
