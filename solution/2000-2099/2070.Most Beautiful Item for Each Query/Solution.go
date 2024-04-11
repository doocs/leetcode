func maximumBeauty(items [][]int, queries []int) []int {
	sort.Slice(items, func(i, j int) bool {
		return items[i][0] < items[j][0]
	})
	n, m := len(items), len(queries)
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return queries[idx[i]] < queries[idx[j]] })
	ans := make([]int, m)
	i, mx := 0, 0
	for _, j := range idx {
		for i < n && items[i][0] <= queries[j] {
			mx = max(mx, items[i][1])
			i++
		}
		ans[j] = mx
	}
	return ans
}