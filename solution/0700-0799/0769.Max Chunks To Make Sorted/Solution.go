func maxChunksToSorted(arr []int) int {
	ans, mx := 0, 0
	for i, v := range arr {
		mx = max(mx, v)
		if i == mx {
			ans++
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