func maxBoxesInWarehouse(boxes []int, warehouse []int) (ans int) {
	n := len(warehouse)
	left := make([]int, n)
	right := make([]int, n)
	const inf = 1 << 30
	left[0] = inf
	right[n-1] = inf
	for i := 1; i < n; i++ {
		left[i] = min(left[i-1], warehouse[i-1])
	}
	for i := n - 2; i >= 0; i-- {
		right[i] = min(right[i+1], warehouse[i+1])
	}
	for i := 0; i < n; i++ {
		warehouse[i] = min(warehouse[i], max(left[i], right[i]))
	}
	sort.Ints(boxes)
	sort.Ints(warehouse)
	i := 0
	for _, x := range boxes {
		for i < n && warehouse[i] < x {
			i++
		}
		if i == n {
			break
		}
		ans++
		i++
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}