class Solution:
    def minOperationsMaxProfit(
        self, customers: List[int], boardingCost: int, runningCost: int
    ) -> int:
        ans = -1
        mx = t = 0
        wait = 0
        i = 0
        while wait or i < len(customers):
            wait += customers[i] if i < len(customers) else 0
            up = wait if wait < 4 else 4
            wait -= up
            t += up * boardingCost - runningCost
            i += 1
            if t > mx:
                mx = t
                ans = i
        return ans
