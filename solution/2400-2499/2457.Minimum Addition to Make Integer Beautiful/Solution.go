func makeIntegerBeautiful(n int64, target int) (x int64) {
	f := func(x int64) (y int) {
		for ; x > 0; x /= 10 {
			y += int(x % 10)
		}
		return
	}
	for f(n+x) > target {
		y := n + x
		var p int64 = 10
		for y%10 == 0 {
			y /= 10
			p *= 10
		}
		x = (y/10+1)*p - n
	}
	return
}