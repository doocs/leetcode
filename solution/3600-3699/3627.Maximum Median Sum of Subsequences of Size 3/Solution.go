func maximumMedianSum(nums []int) (ans int64) {
	sort.Ints(nums)
	n := len(nums)
	for i := n / 3; i < n; i += 2 {
		ans += int64(nums[i])
	}
	return
}