func minDeletions(nums []int) (ans int) {
	for i := 1; i < len(nums); i++ {
		if nums[i] == nums[i-1] {
			ans++
		}
	}
	return
}
