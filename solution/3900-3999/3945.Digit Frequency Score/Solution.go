func digitFrequencyScore(n int) (ans int) {
	for ; n > 0; n /= 10 {
		ans += n % 10
	}
	return
}