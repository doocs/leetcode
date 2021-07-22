func sumNums(n int) int {
	s := 0
	var sum func(int) bool
	sum = func(n int) bool {
		s += n
		return n > 0 && sum(n-1)
	}
	sum(n)
	return s
}