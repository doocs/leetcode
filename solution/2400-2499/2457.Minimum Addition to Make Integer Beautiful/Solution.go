func makeIntegerBeautiful(n int64, target int) int64 {
	f := func(x int64) int {
		v := 0
		for x > 0 {
			v += int(x % 10)
			x /= 10
		}
		return v
	}
	var x int64
	for f(n+x) > target {
		y := n + x
		var p int64 = 10
		for y%10 == 0 {
			y /= 10
			p *= 10
		}
		x = (y/10+1)*p - n
	}
	return x
}