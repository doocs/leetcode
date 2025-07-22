func sumOfGoodNumbers(nums []int, k int) (ans int) {
	for i, x := range nums {
		if i >= k && x <= nums[i-k] {
			continue
		}
		if i+k < len(nums) && x <= nums[i+k] {
			continue
		}
		ans += x
	}
	return
}
