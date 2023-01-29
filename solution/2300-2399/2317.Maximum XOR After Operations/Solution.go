func maximumXOR(nums []int) (ans int) {
	for _, x := range nums {
		ans |= x
	}
	return
}