func nextBeautifulNumber(n int) int {
	check := func(num int) bool {
		s := strconv.Itoa(num)
		counter := make([]int, 10)
		for _, c := range s {
			counter[int(c-'0')]++
		}
		for _, c := range s {
			if counter[int(c-'0')] != int(c-'0') {
				return false
			}
		}
		return true
	}

	for i := n + 1; i <= 10000000; i++ {
		if check(i) {
			return i
		}
	}
	return -1
}