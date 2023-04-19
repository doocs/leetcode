func canJump(nums []int) bool {
	mx := 0
	for i, x := range nums {
		if mx < i {
			return false
		}
		mx = max(mx, i+x)
	}
	return true
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}