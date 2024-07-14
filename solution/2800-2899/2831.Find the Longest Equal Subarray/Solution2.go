func longestEqualSubarray(nums []int, k int) (ans int) {
	g := make([][]int, len(nums)+1)
	for i, x := range nums {
		g[x] = append(g[x], i)
	}
	for _, ids := range g {
		l := 0
		for r := range ids {
			for ids[r]-ids[l]-(r-l) > k {
				l++
			}
			ans = max(ans, r-l+1)
		}
	}
	return
}