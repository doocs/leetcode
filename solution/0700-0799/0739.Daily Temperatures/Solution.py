class Solution:
    def dailyTemperatures(self, T: List[int]) -> List[int]:
        n = len(T)
        res = [0 for _ in range(n)]
        s = []
        for i in range(n):
            while s and T[s[-1]] < T[i]:
                j = s.pop()
                res[j] = i - j
            s.append(i)
        return res
