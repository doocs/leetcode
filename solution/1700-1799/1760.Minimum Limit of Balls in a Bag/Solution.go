func minimumSize(nums []int, maxOperations int) int {
	return 1 + sort.Search(1e9, func(x int) bool {
		x++
		s := 0
		for _, v := range nums {
			s += (v - 1) / x
		}
		return s <= maxOperations
	})
}