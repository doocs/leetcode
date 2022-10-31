func getSmallestString(n int, k int) string {
	ans := make([]byte, n)
	for i := range ans {
		ans[i] = 'a'
	}
	i := n - 1
	d := k - n
	for d > 25 {
		ans[i] = 'z'
		i--
		d -= 25
	}
	ans[i] += byte(d)
	return string(ans)
}