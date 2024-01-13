func missingNumber(nums []int) int {
	sort.Ints(nums)
	for i, x := range nums {
		if i != x {
			return i
		}
	}
	return len(nums)
}