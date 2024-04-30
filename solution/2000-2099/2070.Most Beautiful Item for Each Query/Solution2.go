func maximumBeauty(items [][]int, queries []int) []int {
	sort.Slice(items, func(i, j int) bool {
		return items[i][0] < items[j][0]
	})
	n, m := len(items), len(queries)
	prices := make([]int, n)
	prices[0] = items[0][0]
	for i := 1; i < n; i++ {
		prices[i] = items[i][0]
		items[i][1] = max(items[i][1], items[i-1][1])
	}
	ans := make([]int, m)
	for i, q := range queries {
		j := sort.SearchInts(prices, q+1) - 1
		if j >= 0 {
			ans[i] = items[j][1]
		}
	}
	return ans
}