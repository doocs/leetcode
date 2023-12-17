func findMissingAndRepeatedValues(grid [][]int) []int {
	n := len(grid)
	ans := make([]int, 2)
	cnt := make([]int, n*n+1)
	for _, row := range grid {
		for _, x := range row {
			cnt[x]++
			if cnt[x] == 2 {
				ans[0] = x
			}
		}
	}
	for x := 1; ; x++ {
		if cnt[x] == 0 {
			ans[1] = x
			return ans
		}
	}
}