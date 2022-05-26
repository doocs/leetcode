func trap(height []int) int {
	n := len(height)
	if n < 3 {
		return 0
	}

	lmx, rmx := make([]int, n), make([]int, n)
	lmx[0], rmx[n-1] = height[0], height[n-1]
	for i := 1; i < n; i++ {
		lmx[i] = max(lmx[i-1], height[i])
		rmx[n-1-i] = max(rmx[n-i], height[n-1-i])
	}

	res := 0
	for i := 0; i < n; i++ {
		res += min(lmx[i], rmx[i]) - height[i]
	}
	return res
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