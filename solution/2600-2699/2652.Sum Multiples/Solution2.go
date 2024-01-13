func sumOfMultiples(n int) int {
	f := func(x int) int {
		m := n / x
		return (x + m*x) * m / 2
	}
	return f(3) + f(5) + f(7) - f(3*5) - f(3*7) - f(5*7) + f(3*5*7)
}