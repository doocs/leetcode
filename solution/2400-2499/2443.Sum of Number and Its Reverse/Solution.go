func sumOfNumberAndReverse(num int) bool {
	for x := 0; x <= num; x++ {
		k, y := x, 0
		for k > 0 {
			y = y*10 + k%10
			k /= 10
		}
		if x+y == num {
			return true
		}
	}
	return false
}