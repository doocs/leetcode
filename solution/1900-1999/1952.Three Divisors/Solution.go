func isThree(n int) bool {
	cnt := 0
	for i := 2; i < n; i++ {
		if n%i == 0 {
			cnt++
		}
	}
	return cnt == 1
}