func minSideJumps(obstacles []int) int {
	inf := 0x3f3f3f3f
	f := [3]int{1, 0, 1}
	for _, v := range obstacles[1:] {
		g := [3]int{inf, inf, inf}
		for j := 0; j < 3; j++ {
			if v != j+1 {
				g[j] = f[j]
			}
		}
		if v != 1 {
			g[0] = min(g[0], min(g[1], g[2])+1)
		}
		if v != 2 {
			g[1] = min(g[1], min(g[0], g[2])+1)
		}
		if v != 3 {
			g[2] = min(g[2], min(g[0], g[1])+1)
		}
		f = g
	}
	return min(f[0], min(f[1], f[2]))
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}