func missingMultiple(nums []int, k int) int {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for i := 1; ; i++ {
		if x := k * i; !s[x] {
			return x
		}
	}
}
