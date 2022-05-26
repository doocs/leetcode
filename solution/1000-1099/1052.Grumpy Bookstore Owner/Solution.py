class Solution:
    def maxSatisfied(self, customers: List[int], grumpy: List[int], X: int) -> int:
        s = t = 0
        win, n = 0, len(customers)
        for i in range(n):
            if grumpy[i] == 0:
                s += customers[i]
            else:
                win += customers[i]
            if i >= X and grumpy[i - X] == 1:
                win -= customers[i - X]
            t = max(t, win)
        return s + t
