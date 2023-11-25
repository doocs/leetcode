func minimumSize(nums []int, maxOperations int) int {
	r := slices.Max(nums)
	return 1 + sort.Search(r, func(mx int) bool {
		mx++
		cnt := 0
		for _, x := range nums {
			cnt += (x - 1) / mx
		}
		return cnt <= maxOperations
	})
}