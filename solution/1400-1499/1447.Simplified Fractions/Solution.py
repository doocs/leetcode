class Solution:
    def simplifiedFractions(self, n: int) -> List[str]:
        return [
            f'{i}/{j}'
            for i in range(1, n)
            for j in range(i + 1, n + 1)
            if gcd(i, j) == 1
        ]
