func cyclicShift(n int, grid [][]int, rowShift []int, colShift []int) [][]int {
	t := make([][]int, n)
	for i := range t {
		t[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			t[i][(j-rowShift[i]+n)%n] = grid[i][j]
		}
	}
	ans := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for j := 0; j < n; j++ {
		for i := 0; i < n; i++ {
			ans[(i-colShift[j]+n)%n][j] = t[i][j]
		}
	}
	return ans
}
