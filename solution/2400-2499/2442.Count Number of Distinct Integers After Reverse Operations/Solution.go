func countDistinctIntegers(nums []int) int {
	s := map[int]struct{}{}
	for _, x := range nums {
		s[x] = struct{}{}
	}
	for _, x := range nums {
		y := 0
		for x > 0 {
			y = y*10 + x%10
			x /= 10
		}
		s[y] = struct{}{}
	}
	return len(s)
}