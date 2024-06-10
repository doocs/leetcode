func numberOfChild(n int, k int) int {
	mod := k % (n - 1)
	k /= (n - 1)
	if k%2 == 1 {
		return n - mod - 1
	}
	return mod
}