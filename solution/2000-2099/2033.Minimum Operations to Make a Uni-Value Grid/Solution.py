class Solution:
    def minOperations(self, grid: List[List[int]], x: int) -> int:
        nums = []
        m, n = len(grid), len(grid[0])
        base = grid[0][0]
        for i in range(m):
            for j in range(n):
                if abs(grid[i][j] - base) % x != 0:
                    return -1
                nums.append(grid[i][j])
        nums.sort()
        mid = nums[len(nums) >> 1]
        ans = 0
        for num in nums:
            ans += abs(num - mid) // x
        return ans
