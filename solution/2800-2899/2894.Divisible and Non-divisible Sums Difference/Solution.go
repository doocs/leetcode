func differenceOfSums(n int, m int) (ans int) {
	for i := 1; i <= n; i++ {
		if i%m == 0 {
			ans -= i
		} else {
			ans += i
		}
	}
	return
}