func canJump(nums []int) bool {
	mx := 0
	for i, num := range nums {
		if i > mx {
			return false
		}
		mx = max(mx, i+num)
	}
	return true
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}