func maximumBeauty(items [][]int, queries []int) []int {
	sort.Slice(items, func(i, j int) bool {
		return items[i][0] < items[j][0]
	})
	for i := 1; i < len(items); i++ {
		items[i][1] = max(items[i-1][1], items[i][1])
	}
	n := len(queries)
	ans := make([]int, n)
	for i, v := range queries {
		left, right := 0, len(items)
		for left < right {
			mid := (left + right) >> 1
			if items[mid][0] > v {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if left > 0 {
			ans[i] = items[left-1][1]
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