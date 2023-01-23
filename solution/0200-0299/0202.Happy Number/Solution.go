func isHappy(n int) bool {
	next := func(x int) (y int) {
		for ; x > 0; x /= 10 {
			y += (x % 10) * (x % 10)
		}
		return
	}
	slow, fast := n, next(n)
	for slow != fast {
		slow = next(slow)
		fast = next(next(fast))
	}
	return slow == 1
}