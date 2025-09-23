func maxKDistinct(nums []int, k int) (ans []int) {
	slices.Sort(nums)
	n := len(nums)
	for i := n - 1; i >= 0; i-- {
		if i+1 < n && nums[i] == nums[i+1] {
			continue
		}
		ans = append(ans, nums[i])
		if k--; k == 0 {
			break
		}
	}
	return
}
