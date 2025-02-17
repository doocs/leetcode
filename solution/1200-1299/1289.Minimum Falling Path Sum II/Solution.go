func minFallingPathSum(grid [][]int) int {
	f := make([]int, len(grid))
	const inf = math.MaxInt32
	for _, row := range grid {
		g := slices.Clone(row)
		for i := range f {
			t := inf
			for j := range row {
				if j != i {
					t = min(t, f[j])
				}
			}
			if t != inf {
				g[i] += t
			}
		}
		f = g
	}
	return slices.Min(f)
}
