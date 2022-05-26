func removeDuplicates(nums []int) int {
	i := 0
	for _, num := range nums {
		if i < 2 || num != nums[i-2] {
			nums[i] = num
			i++
		}
	}
	return i
}