func xorBeauty(nums []int) (ans int) {
	for _, x := range nums {
		ans ^= x
	}
	return
}