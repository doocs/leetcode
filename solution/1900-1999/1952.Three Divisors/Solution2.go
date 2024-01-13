func isThree(n int) bool {
	cnt := 0
	for i := 1; i <= n/i; i++ {
		if n%i == 0 {
			if n/i == i {
				cnt++
			} else {
				cnt += 2
			}
		}
	}
	return cnt == 3
}