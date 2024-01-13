func arithmeticTriplets(nums []int, diff int) (ans int) {
	vis := [301]bool{}
	for _, x := range nums {
		vis[x] = true
	}
	for _, x := range nums {
		if vis[x+diff] && vis[x+diff+diff] {
			ans++
		}
	}
	return
}