func minOperations(nums []int) int {
	st := make(map[int]struct{})
	for i := len(nums) - 1; i >= 0; i-- {
		if _, ok := st[nums[i]]; ok {
			return i/3 + 1
		}
		st[nums[i]] = struct{}{}
	}
	return 0
}
