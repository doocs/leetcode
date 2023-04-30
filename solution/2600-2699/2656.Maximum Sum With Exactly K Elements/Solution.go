func maximizeSum(nums []int, k int) int {
	x := 0
	for _, v := range nums {
		x = max(x, v)
	}
	return k*x + k*(k-1)/2
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}