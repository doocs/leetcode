func minimumSize(nums []int, maxOperations int) int {
	r := slices.Max(nums)
	return 1 + sort.Search(r, func(mx int) bool {
		mx++
		s := 0
		for _, x := range nums {
			s += (x - 1) / mx
		}
		return s <= maxOperations
	})
}
