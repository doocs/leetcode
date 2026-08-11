func missingInteger(nums []int) int {
	s := nums[0]
	for j := 1; j < len(nums) && nums[j] == nums[j-1]+1; j++ {
		s += nums[j]
	}

	const m = 51
	st := make([]bool, m)
	for _, x := range nums {
		st[x] = true
	}

	for s < m && st[s] {
		s++
	}
	return s
}
