class Solution:
    def getCollisionTimes(self, cars: List[List[int]]) -> List[float]:
        stk = []
        n = len(cars)
        ans = [-1] * n
        for i in range(n - 1, -1, -1):
            while stk:
                j = stk[-1]
                if cars[i][1] > cars[j][1]:
                    t = (cars[j][0] - cars[i][0]) / (cars[i][1] - cars[j][1])
                    if ans[j] == -1 or t <= ans[j]:
                        ans[i] = t
                        break
                stk.pop()
            stk.append(i)
        return ans
