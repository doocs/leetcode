func wiggleMaxLength(nums []int) int {
	up, down := 1, 1
	for i := 1; i < len(nums); i++ {
		if nums[i] > nums[i-1] {
			up = max(up, down+1)
		} else if nums[i] < nums[i-1] {
			down = max(down, up+1)
		}
	}
	return max(up, down)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}