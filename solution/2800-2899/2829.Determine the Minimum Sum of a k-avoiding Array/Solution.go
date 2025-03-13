func minimumSum(n int, k int) int {
	s, i := 0, 1
	vis := make([]bool, n+k+1)
	for ; n > 0; n-- {
		for vis[i] {
			i++
		}
		if k >= i {
			vis[k-i] = true
		}
		s += i
		i++
	}
	return s
}
