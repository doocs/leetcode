func maxTurbulenceSize(arr []int) int {
	ans, f, g := 1, 1, 1
	for i, x := range arr[1:] {
		ff, gg := 1, 1
		if arr[i] < x {
			ff = g + 1
		}
		if arr[i] > x {
			gg = f + 1
		}
		f, g = ff, gg
		ans = max(ans, max(f, g))
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}