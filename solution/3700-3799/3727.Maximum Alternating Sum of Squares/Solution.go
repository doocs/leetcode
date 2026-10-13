func maxAlternatingSum(nums []int) (ans int64) {
	for i, x := range nums {
		nums[i] *= x
	}
	slices.Sort(nums)
	m := len(nums) >> 1
	for i, x := range nums {
		if i < m {
			ans -= int64(x)
		} else {
			ans += int64(x)
		}
	}
	return
}
