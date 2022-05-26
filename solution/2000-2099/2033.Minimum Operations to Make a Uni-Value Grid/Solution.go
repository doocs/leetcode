func minOperations(grid [][]int, x int) int {
	var nums []int
	m, n, base := len(grid), len(grid[0]), grid[0][0]
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if abs(grid[i][j]-base)%x != 0 {
				return -1
			}
			nums = append(nums, grid[i][j])
		}
	}
	sort.Ints(nums)
	mid := nums[len(nums)>>1]
	ans := 0
	for _, num := range nums {
		ans += abs(num-mid) / x
	}
	return ans
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}