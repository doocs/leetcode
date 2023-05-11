class Solution:
    def queryString(self, s: str, n: int) -> bool:
        if n > 1000:
            return False
        return all(bin(i)[2:] in s for i in range(n, n // 2, -1))
