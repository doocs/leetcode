func removeDuplicates(nums []int) int {
	k := 0
	for _, x := range nums {
		if k == 0 || x != nums[k-1] {
			nums[k] = x
			k++
		}
	}
	return k
}