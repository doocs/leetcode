class Solution:
    def minimumCost(self, nums: List[int], cost: List[int], k: int) -> int:
        n = len(nums)
        sumN = [0] * (n + 1)
        sumC = [0] * (n + 1)
        
        for i in range(1, n + 1):
            sumN[i] = sumN[i - 1] + nums[i - 1]
            sumC[i] = sumC[i - 1] + cost[i - 1]

        prevDp = [sys.maxsize] * (n + 1)
        prevDp[0] = 0

        minCost = sys.maxsize

        for m in range(1, n + 1):
            currentDp = [sys.maxsize] * (n + 1)
            queue = []

            for r in range(m, n + 1):
                l = r - 1
                if l >= m - 1:
                    x = sumC[l]
                    y = prevDp[l]

                    while len(queue) >= 2:
                        x1, y1 = queue[-2][0], queue[-2][1]
                        x2, y2 = queue[-1][0], queue[-1][1]
                        if (x2 - x1) * (y - y2) <= (x - x2) * (y2 - y1):
                            queue.pop()
                        else:
                            break
                    queue.append((x, y, l))

                a = -(sumN[r] + k * m)

                while len(queue) >= 2:
                    x1, y1 = queue[0][0], queue[0][1]
                    x2, y2 = queue[1][0], queue[1][1]
                    if a * x1 + y1 >= a * x2 + y2:
                        queue.pop(0)
                    else:
                        break

                if queue:
                    x, y = queue[0][0], queue[0][1]
                    best = a * x + y
                    currentCost = (sumN[r] + k * m) * sumC[r]
                    currentCost += best
                    if currentCost < currentDp[r]:
                        currentDp[r] = currentCost

            if currentDp[n] < minCost:
                minCost = currentDp[n]
            prevDp = currentDp

        return minCost