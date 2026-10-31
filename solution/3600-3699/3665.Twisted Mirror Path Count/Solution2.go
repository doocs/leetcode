func uniquePaths(grid [][]int) int {
	const mod = int(1e9 + 7)
	n := len(grid[0])
	f := make([][2]int, n+1)
	f[1] = [2]int{1, 1}
	for _, row := range grid {
		for j, x := range row {
			r, d := f[j][0], f[j+1][1]
			if x == 1 {
				f[j+1] = [2]int{d, r}
			} else {
				s := (r + d) % mod
				f[j+1] = [2]int{s, s}
			}
		}
	}
	return f[n][0]
}
