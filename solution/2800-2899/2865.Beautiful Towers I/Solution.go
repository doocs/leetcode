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