func jump(nums []int) int {
	mx, steps, end := 0, 0, 0
	for i := 0; i < len(nums)-1; i++ {
		mx = max(mx, i+nums[i])
		if i == end {
			end = mx
			steps++
		}
	}
	return steps
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}