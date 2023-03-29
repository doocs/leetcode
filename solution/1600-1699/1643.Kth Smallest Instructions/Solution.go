func kthSmallestPath(destination []int, k int) string {
	v, h := destination[0], destination[1]
	n := v + h
	c := make([][]int, n+1)
	for i := range c {
		c[i] = make([]int, h+1)
		c[i][0] = 1
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= h; j++ {
			c[i][j] = c[i-1][j] + c[i-1][j-1]
		}
	}
	ans := []byte{}
	for i := 0; i < n; i++ {
		if h == 0 {
			ans = append(ans, 'V')
		} else {
			x := c[v+h-1][h-1]
			if k > x {
				ans = append(ans, 'V')
				k -= x
				v--
			} else {
				ans = append(ans, 'H')
				h--
			}
		}
	}
	return string(ans)
}