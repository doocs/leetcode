class Solution:
    MOD = 10**9 + 7

    def solve(self, k):
        sumDifferences = k * len(self.nums) - self.sumNums
        ones = max(2 * (k - self.minNums) - sumDifferences, 0)
        if (sumDifferences - ones) & 1 != 0:
            ones += 1
        return ones * self.cost1 + ((sumDifferences - ones) // 2) * self.cost2

    def minCostToEqualizeArray(self, nums: list[int], cost1: int, cost2: int) -> int:
        from bisect import bisect_left

        cost2 = min(2 * cost1, cost2)
        self.nums = nums
        self.minNums = min(nums)
        self.sumNums = sum(nums)
        self.cost1 = cost1
        self.cost2 = cost2
        m = max(nums)
        sameParity = range(m, 10**18, 2)
        diffParity = range(m + 1, 10**18, 2)
        i = bisect_left(
            sameParity, 0, key=lambda i: self.solve(i + 2) - self.solve(i)
        )
        j = bisect_left(
            diffParity, 0, key=lambda j: self.solve(j + 2) - self.solve(j)
        )
        return min(self.solve(sameParity[i]), self.solve(diffParity[j])) % Solution.MOD
