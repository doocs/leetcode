func selfDividingNumbers(left int, right int) []int {
	check := func(num int) bool {
		for t := num; t != 0; t /= 10 {
			x := t % 10
			if x == 0 || num%x != 0 {
				return false
			}
		}
		return true
	}

	var ans []int
	for i := left; i <= right; i++ {
		if check(i) {
			ans = append(ans, i)
		}
	}
	return ans
}