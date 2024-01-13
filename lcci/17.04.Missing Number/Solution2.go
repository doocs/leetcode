func missingNumber(nums []int) (ans int) {
	ans = len(nums)
	for i, x := range nums {
		ans += i - x
	}
	return
}