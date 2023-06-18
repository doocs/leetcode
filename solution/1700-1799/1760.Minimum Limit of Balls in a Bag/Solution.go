func minimumSize(nums []int, maxOperations int) int {
	r := 0
	for _, x := range nums {
		r = max(r, x)
	}
	return 1 + sort.Search(r, func(mx int) bool {
		mx++
		cnt := 0
		for _, x := range nums {
			cnt += (x - 1) / mx
		}
		return cnt <= maxOperations
	})
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}