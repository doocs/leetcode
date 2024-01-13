func longestOnes(nums []int, k int) int {
	l, r := -1, -1
	for r < len(nums)-1 {
		r++
		if nums[r] == 0 {
			k--
		}
		if k < 0 {
			l++
			if nums[l] == 0 {
				k++
			}
		}
	}
	return r - l
}