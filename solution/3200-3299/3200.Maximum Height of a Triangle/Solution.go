func maxHeightOfTriangle(red int, blue int) (ans int) {
	for k := 0; k < 2; k++ {
		c := [2]int{red, blue}
		for i, j := 1, k; i <= c[j]; i, j = i+1, j^1 {
			c[j] -= i
			ans = max(ans, i)
		}
	}
	return
}