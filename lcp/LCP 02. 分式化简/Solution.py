class Solution:
    def fraction(self, cont: List[int]) -> List[int]:
        def dfs(i: int) -> List[int]:
            if i == len(cont) - 1:
                return [cont[i], 1]
            a, b = dfs(i + 1)
            x, y = a * cont[i] + b, a
            g = gcd(x, y)
            return [x // g, y // g]

        return dfs(0)
