func minMoves(nums []int) int {
	mx, s := 0, 0
	for _, x := range nums {
		mx = max(mx, x)
		s += x
	}
	return mx*len(nums) - s
}
