class Solution:
    def isThree(self, n: int) -> bool:
        cnt = 0
        i = 1
        while i <= n // i:
            if n % i == 0:
                cnt += 1 if i == n // i else 2
            i += 1
        return cnt == 3
