func smallestNumber(n int, t int) int {
	for i := n; ; i++ {
		p := 1
		for x := i; x > 0; x /= 10 {
			p *= x % 10
		}
		if p%t == 0 {
			return i
		}
	}
}
