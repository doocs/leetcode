func minimumSwaps(nums []int) int {
	mi, mx := nums[0], 0
	for _, v := range nums {
		mi = min(mi, v)
		mx = max(mx, v)
	}
	i, j := -1, -1
	for k, v := range nums {
		if v == mi && i == -1 {
			i = k
		}
		if v == mx {
			j = k
		}
	}
	if i == j {
		return 0
	}
	n := len(nums)
	if i < j {
		return i + n - 1 - j
	}
	return i + n - 2 - j
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}