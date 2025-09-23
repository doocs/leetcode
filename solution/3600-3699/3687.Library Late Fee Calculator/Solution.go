func lateFee(daysLate []int) (ans int) {
	f := func(x int) int {
		if x == 1 {
			return 1
		} else if x > 5 {
			return 3 * x
		}
		return 2 * x
	}
	for _, x := range daysLate {
		ans += f(x)
	}
	return
}
