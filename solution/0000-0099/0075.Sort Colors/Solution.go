func sortColors(nums []int) {
	i, j, cur := -1, len(nums), 0
	for cur < j {
		if nums[cur] == 0 {
			i++
			nums[cur], nums[i] = nums[i], nums[cur]
			cur++
		} else if nums[cur] == 1 {
			cur++
		} else {
			j--
			nums[cur], nums[j] = nums[j], nums[cur]
		}
	}
}