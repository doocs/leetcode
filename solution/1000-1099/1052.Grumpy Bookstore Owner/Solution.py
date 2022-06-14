class Solution:
    def maxSatisfied(
        self, customers: List[int], grumpy: List[int], minutes: int
    ) -> int:
        s = sum(a * b for a, b in zip(customers, grumpy))
        cs = sum(customers)
        t = ans = 0
        for i, (a, b) in enumerate(zip(customers, grumpy), 1):
            t += a * b
            if (j := i - minutes) >= 0:
                ans = max(ans, cs - (s - t))
                t -= customers[j] * grumpy[j]
        return ans
