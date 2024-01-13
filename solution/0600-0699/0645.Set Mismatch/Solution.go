func findErrorNums(nums []int) []int {
	n := len(nums)
	s1 := (1 + n) * n / 2
	s2, s := 0, 0
	set := map[int]bool{}
	for _, x := range nums {
		if !set[x] {
			set[x] = true
			s2 += x
		}
		s += x
	}
	return []int{s - s2, s1 - s2}
}