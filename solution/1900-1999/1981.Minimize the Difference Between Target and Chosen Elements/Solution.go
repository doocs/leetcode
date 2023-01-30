func minimizeTheDifference(mat [][]int, target int) int {
	f := []int{1}
	for _, row := range mat {
		mx := 0
		for _, x := range row {
			mx = max(mx, x)
		}
		g := make([]int, len(f)+mx)
		for _, x := range row {
			for j := x; j < len(f)+x; j++ {
				g[j] |= f[j-x]
			}
		}
		f = g
	}
	ans := 1 << 30
	for j, v := range f {
		if v == 1 {
			ans = min(ans, abs(j-target))
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}