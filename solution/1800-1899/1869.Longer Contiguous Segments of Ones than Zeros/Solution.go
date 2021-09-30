func checkZeroOnes(s string) bool {
	n0, n1 := 0, 0
	t0, t1 := 0, 0
	for _, c := range s {
		if c == '0' {
			t0++
			t1 = 0
		} else {
			t1++
			t0 = 0
		}
		n0 = max(n0, t0)
		n1 = max(n1, t1)
	}
	return n1 > n0
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}