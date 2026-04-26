func minOperations(nums []int) (ans int64) {
	for i := 1; i < len(nums); i++ {
		ans += max(int64(nums[i-1]-nums[i]), 0)
	}
	return
}
