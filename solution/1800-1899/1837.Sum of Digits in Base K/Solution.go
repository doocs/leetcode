func sumBase(n int, k int) (ans int) {
	for n > 0 {
		ans += n % k
		n /= k
	}
	return
}