func limitOccurrences(nums []int, k int) []int {
	n := len(nums)
	cnt, l := 1, 1

	for r := 1; r < n; r++ {
		if nums[r] != nums[r-1] {
			cnt = 1
		} else {
			cnt++
		}

		if cnt <= k {
			nums[l] = nums[r]
			l++
		}
	}

	return nums[:l]
}