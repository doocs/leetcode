class Solution:
    def primeSubOperation(self, nums: List[int]) -> bool:
        p = []
        for i in range(2, max(nums)):
            for j in p:
                if i % j == 0:
                    break
            else:
                p.append(i)

        n = len(nums)
        for i in range(n - 2, -1, -1):
            if nums[i] < nums[i + 1]:
                continue
            j = bisect_right(p, nums[i] - nums[i + 1])
            if j == len(p) or p[j] >= nums[i]:
                return False
            nums[i] -= p[j]
        return True
