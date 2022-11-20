func missingNumber(nums []int) (ans int) {
	n := len(nums)
	ans = n
	for i, v := range nums {
		ans ^= (i ^ v)
	}
	return
}