func findMaxConsecutiveOnes(nums []int) int {
	l, r := 0, 0
	k := 1
	for ; r < len(nums); r++ {
		if nums[r] == 0 {
			k--
		}
		if k < 0 {
			if nums[l] == 0 {
				k++
			}
			l++
		}
	}
	return r - l
}