func nthUglyNumber(n int, a int, b int, c int) int {
	ab, bc, ac := lcm(a, b), lcm(b, c), lcm(a, c)
	abc := lcm(ab, c)
	var l, r int = 1, 2e9
	for l < r {
		mid := (l + r) >> 1
		if mid/a+mid/b+mid/c-mid/ab-mid/bc-mid/ac+mid/abc >= n {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

func lcm(a, b int) int {
	return a * b / gcd(a, b)
}