class Solution:
    def matrixReshape(self, nums: List[List[int]], r: int, c: int) -> List[List[int]]:
        m, n = len(nums), len(nums[0])
        if m * n != r * c:
            return nums
        res = [[0] * c for _ in range(r)]
        for x in range(m * n):
            res[x // c][x % c] = nums[x // n][x % n]
        return res
