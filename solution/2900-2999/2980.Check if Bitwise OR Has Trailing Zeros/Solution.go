func hasTrailingZeros(nums []int) bool {
	cnt := 0
	for _, x := range nums {
		cnt += (x&1 ^ 1)
	}
	return cnt >= 2
}