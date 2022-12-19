func smallestValue(n int) int {
	for {
		t, s := n, 0
		for i := 2; i <= n/i; i++ {
			for n%i == 0 {
				s += i
				n /= i
			}
		}
		if n > 1 {
			s += n
		}
		if s == t {
			return s
		}
		n = s
	}
}