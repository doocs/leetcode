func findPairs(nums []int, k int) int {
	ans := make(map[int]struct{})
	vis := make(map[int]struct{})

	for _, x := range nums {
		if _, ok := vis[x-k]; ok {
			ans[x-k] = struct{}{}
		}
		if _, ok := vis[x+k]; ok {
			ans[x] = struct{}{}
		}
		vis[x] = struct{}{}
	}
	return len(ans)
}
