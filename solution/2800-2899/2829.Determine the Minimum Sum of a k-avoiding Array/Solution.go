func minimumSum(n int, k int) int {
	s, i := 0, 1
	vis := make([]bool, k+n*n+1)
	for ; n > 0; n-- {
		for vis[i] {
			i++
		}
		vis[i] = true
		if k >= i {
			vis[k-i] = true
		}
		s += i
	}
	return s
}