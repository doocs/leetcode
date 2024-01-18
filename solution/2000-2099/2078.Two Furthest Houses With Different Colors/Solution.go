func maxDistance(colors []int) int {
	ans, n := 0, len(colors)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if colors[i] != colors[j] {
				ans = max(ans, abs(i-j))
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x >= 0 {
		return x
	}
	return -x
}