func minMaxGame(nums []int) int {
	n := len(nums)
	if n == 1 {
		return nums[0]
	}
	var t []int
	for i := 0; i < n>>1; i++ {
		a, b := nums[i<<1], nums[i<<1|1]
		if (i & 1) == 1 {
			t = append(t, max(a, b))
		} else {
			t = append(t, min(a, b))
		}
	}
	return minMaxGame(t)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}