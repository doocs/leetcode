func minimumPushes(word string) (ans int) {
	n := len(word)
	k := 1
	for i := 0; i < n/8; i++ {
		ans += k * 8
		k++
	}
	ans += k * (n % 8)
	return
}