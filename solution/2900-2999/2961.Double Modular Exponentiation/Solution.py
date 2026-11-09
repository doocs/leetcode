class Solution:
    def getGoodIndices(self, variables: list[list[int]], target: int) -> list[int]:
        return [
            i
            for i, (a, b, c, m) in enumerate(variables)
            if pow(pow(a, b, 10), c, m) == target
        ]
