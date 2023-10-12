func maximumSumOfHeights(maxHeights []int) (ans int64) {
	n := len(maxHeights)
	for i, x := range maxHeights {
		y, t := x, x
		for j := i - 1; j >= 0; j-- {
			y = min(y, maxHeights[j])
			t += y
		}
		y = x
		for j := i + 1; j < n; j++ {
			y = min(y, maxHeights[j])
			t += y
		}
		ans = max(ans, int64(t))
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}