func createTargetArray(nums []int, index []int) []int {
	target := make([]int, len(nums))
	for i, v := range nums {
		copy(target[index[i]+1:], target[index[i]:])
		target[index[i]] = v
	}
	return target
}