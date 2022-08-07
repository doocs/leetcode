func arithmeticTriplets(nums []int, diff int) int {
	vis := make([]bool, 310)
	for _, v := range nums {
		vis[v] = true
	}
	ans := 0
	for _, v := range nums {
		if vis[v+diff] && vis[v+diff+diff] {
			ans++
		}
	}
	return ans
}