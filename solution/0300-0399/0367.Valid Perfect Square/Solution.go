func isPerfectSquare(num int) bool {
	l := sort.Search(num, func(i int) bool { return i*i >= num })
	return l*l == num
}