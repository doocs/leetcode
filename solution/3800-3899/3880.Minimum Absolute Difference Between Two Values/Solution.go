func minAbsoluteDifference(nums []int) int {
	n := len(nums)
	ans := n + 1

	last := []int{-ans, -ans, -ans}

	for i, x := range nums {
		if x != 0 {
			ans = min(ans, i-last[3-x])
			last[x] = i
		}
	}

	if ans > n {
		return -1
	}
	return ans
}
