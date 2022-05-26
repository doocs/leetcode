func minMoves(nums []int) int {
	mi := math.MaxInt32
	s := 0
	for _, num := range nums {
		s += num
		if num < mi {
			mi = num
		}
	}
	return s - mi*len(nums)

}