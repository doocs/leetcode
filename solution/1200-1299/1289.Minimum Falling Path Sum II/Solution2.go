func minFallingPathSum(grid [][]int) int {
	const inf = 1 << 30
	f, g := 0, 0
	fp := -1
	for _, row := range grid {
		ff, gg := inf, inf
		ffp := -1
		for j, v := range row {
			s := f
			if j == fp {
				s = g
			}
			s += v
			if s < ff {
				ff, gg, ffp = s, ff, j
			} else if s < gg {
				gg = s
			}
		}
		f, g, fp = ff, gg, ffp
	}
	return f
}