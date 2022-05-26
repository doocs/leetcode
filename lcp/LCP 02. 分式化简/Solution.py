class Solution:
    def fraction(self, cont: List[int]) -> List[int]:
        def dfs(cont):
            if len(cont) == 1:
                return [cont[0], 1]
            a, b = dfs(cont[1:])
            return [a * cont[0] + b, a]

        return dfs(cont)
