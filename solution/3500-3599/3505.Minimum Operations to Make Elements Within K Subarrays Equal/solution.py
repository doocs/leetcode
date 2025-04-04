from sortedcontainers import SortedList as SL
fmin = lambda a, b: a if a < b else b
class Solution:
    def minOperations(self, nums: List[int], l: int, k: int) -> int:
        n = len(nums)
        lt, gt = SL(), SL()
        ls, gs = 0, 0
        def add(x):
            nonlocal ls, gs
            if not lt or x <= lt[-1]:
                lt.add(x)
                ls += x
            else:
                gt.add(x)
                gs += x
        def remove(x):
            nonlocal ls, gs
            if x <= lt[-1]:
                lt.remove(x)
                ls -= x
            else:
                gt.remove(x)
                gs -= x
        def adjust():
            nonlocal ls, gs
            if len(lt) - len(gt) > 1:
                x = lt.pop(-1)
                ls -= x
                gt.add(x)
                gs += x
            elif len(gt) - len(lt) > 0:
                x = gt.pop(0)
                gs -= x
                lt.add(x)
                ls += x
        def getmed():
            return lt[-1]
        cost = [0] * (n-l+1)
        for i, x in enumerate(nums):
            add(x)
            adjust()
            if i >= l:
                remove(nums[i-l])
            adjust()
            if i >= l-1:
                med = getmed()
                cur = gs - med*(len(gt)) + med*len(lt) - ls
                cost[i-l+1] = cur
        # print(cost)
        dp = [[inf] * (k+1) for _ in range(n+1)]
        dp[0][0] = 0
        for i in range(1, n+1):
            for j in range(k+1):
                dp[i][j] = dp[i-1][j]
                if i-l >= 0 and k > 0:
                    dp[i][j] = fmin(dp[i][j], dp[i-l][j-1] + cost[i-l])

        return dp[n][k]