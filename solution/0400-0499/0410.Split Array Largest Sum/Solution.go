func splitArray(nums []int, k int) int {
	left, right := 0, 0
	for _, x := range nums {
		left = max(left, x)
		right += x
	}
	return left + sort.Search(right-left, func(mx int) bool {
		mx += left
		s, cnt := 1<<30, 0
		for _, x := range nums {
			s += x
			if s > mx {
				s = x
				cnt++
			}
		}
		return cnt <= k
	})
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}