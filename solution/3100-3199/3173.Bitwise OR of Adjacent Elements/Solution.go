func orArray(nums []int) (ans []int) {
	for i, x := range nums[1:] {
		ans = append(ans, x|nums[i])
	}
	return
}