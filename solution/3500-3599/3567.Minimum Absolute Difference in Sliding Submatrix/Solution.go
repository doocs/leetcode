func minAbsDiff(grid [][]int, k int) [][]int {
	m, n := len(grid), len(grid[0])
	ans := make([][]int, m-k+1)
	for i := range ans {
		ans[i] = make([]int, n-k+1)
	}
	for i := 0; i <= m-k; i++ {
		for j := 0; j <= n-k; j++ {
			var nums []int
			for x := i; x < i+k; x++ {
				for y := j; y < j+k; y++ {
					nums = append(nums, grid[x][y])
				}
			}
			sort.Ints(nums)
			d := math.MaxInt
			for t := 1; t < len(nums); t++ {
				if nums[t] != nums[t-1] {
					diff := abs(nums[t] - nums[t-1])
					if diff < d {
						d = diff
					}
				}
			}
			if d != math.MaxInt {
				ans[i][j] = d
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
