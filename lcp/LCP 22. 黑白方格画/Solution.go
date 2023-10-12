func paintingPlan(n int, k int) (ans int) {
	if k == n*n {
		return 1
	}
	c := make([][]int, n+1)
	for i := range c {
		c[i] = make([]int, n+1)
	}
	for i := 0; i <= n; i++ {
		c[i][0] = 1
		for j := 1; j <= i; j++ {
			c[i][j] = c[i-1][j] + c[i-1][j-1]
		}
	}
	for i := 0; i <= n; i++ {
		for j := 0; j <= n; j++ {
			if n*(i+j)-i*j == k {
				ans += c[n][i] * c[n][j]
			}
		}
	}
	return
}