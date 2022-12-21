class Solution:
    def cycleLengthQueries(self, n: int, queries: List[List[int]]) -> List[int]:
        ans = []
        for a, b in queries:
            t = 1
            while a != b:
                if a > b:
                    a >>= 1
                else:
                    b >>= 1
                t += 1
            ans.append(t)
        return ans
