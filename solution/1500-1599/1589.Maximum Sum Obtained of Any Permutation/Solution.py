class Solution:
    def maxSumRangeQuery(self, nums: List[int], requests: List[List[int]]) -> int:
        n = 100010
        delta = [0] * n
        for start, end in requests:
            delta[start] += 1
            delta[end + 1] -= 1
        for i in range(1, n):
            delta[i] += delta[i - 1]
        nums.sort()
        delta.sort()
        i, j, res = n - 1, len(nums) - 1, 0
        while i > 0 and delta[i] > 0:
            res += delta[i] * nums[j]
            i -= 1
            j -= 1
        return res % 1000000007
