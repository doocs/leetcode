func minOperations(grid [][]int, x int) int {
	mod := grid[0][0] % x
	nums := []int{}
	for _, row := range grid {
		for _, v := range row {
			if v%x != mod {
				return -1
			}
			nums = append(nums, v)
		}
	}
	sort.Ints(nums)
	mid := nums[len(nums)>>1]
	ans := 0
	for _, v := range nums {
		ans += abs(v-mid) / x
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}