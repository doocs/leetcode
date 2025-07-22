func minOperations(nums []int) (ans int) {
	for i, x := range nums[1:] {
		if x != nums[i] {
			ans++
		}
	}
	return
}
