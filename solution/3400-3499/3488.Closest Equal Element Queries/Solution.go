func solveQueries(nums []int, queries []int) []int {
	n := len(nums)
	m := n * 2
	d := make([]int, m)
	for i := range d {
		d[i] = m
	}

	left := make(map[int]int)
	for i := 0; i < m; i++ {
		x := nums[i%n]
		if idx, exists := left[x]; exists {
			d[i] = min(d[i], i-idx)
		}
		left[x] = i
	}

	right := make(map[int]int)
	for i := m - 1; i >= 0; i-- {
		x := nums[i%n]
		if idx, exists := right[x]; exists {
			d[i] = min(d[i], idx-i)
		}
		right[x] = i
	}

	for i := 0; i < n; i++ {
		d[i] = min(d[i], d[i+n])
	}

	ans := make([]int, len(queries))
	for i, query := range queries {
		if d[query] >= n {
			ans[i] = -1
		} else {
			ans[i] = d[query]
		}
	}
	return ans
}
