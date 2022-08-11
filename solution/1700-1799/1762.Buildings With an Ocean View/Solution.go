func findBuildings(heights []int) []int {
	mx := 0
	ans := []int{}
	for i := len(heights) - 1; i >= 0; i-- {
		v := heights[i]
		if mx < v {
			ans = append(ans, i)
			mx = v
		}
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return ans
}