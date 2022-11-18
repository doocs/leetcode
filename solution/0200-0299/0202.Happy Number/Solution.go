func isHappy(n int) bool {
	m := make(map[int]bool)
	for n != 1 && !m[n] {
		n, m[n] = getSum(n), true
	}
	return n == 1
}

func getSum(n int) (sum int) {
	for n > 0 {
		sum += (n % 10) * (n % 10)
		n = n / 10
	}
	return
}
