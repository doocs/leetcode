func maximumCount(nums []int) int {
	var a, b int
	for _, x := range nums {
		if x > 0 {
			a++
		} else if x < 0 {
			b++
		}
	}
	return max(a, b)
}