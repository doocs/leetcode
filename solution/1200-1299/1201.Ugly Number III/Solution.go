func nthUglyNumber(n int, a int, b int, c int) int {
	left, right := 1, int(2e9)
	for left <= right {
		mid := left + (right-left)/2
		if f(mid, a, b, c) < n {
			left = mid + 1
		} else {
			right = mid - 1
		}
	}
	return left
}

func f(num int, a int, b int, c int) int {
	return num/a + num/b + num/c - num/lcm(a, b) - num/lcm(a, c) - num/lcm(b, c) + num/lcm(lcm(a, b), c)
}

// Least common multiple
func lcm(a, b int) int {
	// Greatest common divisor
	gcd := func(x, y int) int {
		for y != 0 {
			if x < y {
				x, y = y, x
			}
			x, y = y, x%y
		}
		return x
	}
	return a * b / gcd(a, b)
}