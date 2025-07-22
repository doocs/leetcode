func minOperations(nums []int, k int) (ans int) {
	for _, x := range nums {
		ans = (ans + x) % k
	}
	return
}