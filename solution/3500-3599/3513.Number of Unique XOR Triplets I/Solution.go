func uniqueXorTriplets(nums []int) int {
	n := len(nums)
	if n <= 2 {
		return n
	}
	return 1 << bits.Len(uint(n))
}
