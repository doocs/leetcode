func rotatedDigits(n int) int {
	d := map[int]int{0: 0, 1: 1, 8: 8, 2: 5, 5: 2, 6: 9, 9: 6}
	check := func(x int) bool {
		y, t := 0, x
		k := 1
		for ; t > 0; t /= 10 {
			v := t % 10
			if _, ok := d[v]; !ok {
				return false
			}
			y = d[v]*k + y
			k *= 10
		}
		return x != y
	}
	ans := 0
	for i := 1; i <= n; i++ {
		if check(i) {
			ans++
		}
	}
	return ans
}