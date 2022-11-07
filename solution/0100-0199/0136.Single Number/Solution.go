func singleNumber(nums []int) (ans int) {
	for _, v := range nums {
		ans ^= v
	}
	return
}