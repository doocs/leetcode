func selfDividingNumbers(left int, right int) (ans []int) {
	check := func(x int) bool {
		for y := x; y > 0; y /= 10 {
			if y%10 == 0 || x%(y%10) != 0 {
				return false
			}
		}
		return true
	}
	for x := left; x <= right; x++ {
		if check(x) {
			ans = append(ans, x)
		}
	}
	return
}
