func findBuildings(heights []int) (ans []int) {
	mx := 0
	for i := len(heights) - 1; i >= 0; i-- {
		if v := heights[i]; v > mx {
			ans = append(ans, i)
			mx = v
		}
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return
}