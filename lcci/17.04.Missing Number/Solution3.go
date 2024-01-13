func missingNumber(nums []int) (ans int) {
	for i, x := range nums {
		ans ^= (i + 1) ^ x
	}
	return
}