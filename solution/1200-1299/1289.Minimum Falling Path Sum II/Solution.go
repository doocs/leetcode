func minFallingPathSum(grid [][]int) int {
	n := len(grid)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n)
	}
	const inf = 1 << 30
	for i, row := range grid {
		i++
		for j, v := range row {
			x := inf
			for k := range row {
				if k != j {
					x = min(x, f[i-1][k])
				}
			}
			if x == inf {
				x = 0
			}
			f[i][j] = v + x
		}
	}
	return slices.Min(f[n])
}