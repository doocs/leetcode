func findPairs(nums []int, k int) int {
	vis := map[int]bool{}
	ans := map[int]bool{}
	for _, v := range nums {
		if vis[v-k] {
			ans[v-k] = true
		}
		if vis[v+k] {
			ans[v] = true
		}
		vis[v] = true
	}
	return len(ans)
}