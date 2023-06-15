func moveZeroes(nums []int) {
	i := -1
	for j, x := range nums {
		if x != 0 {
			i++
			nums[i], nums[j] = nums[j], nums[i]
		}
	}
}