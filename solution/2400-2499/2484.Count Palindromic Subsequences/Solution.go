func countPalindromes(s string) int {
	n := len(s)
	pre := [10010][10][10]int{}
	suf := [10010][10][10]int{}
	t := make([]int, n)
	for i, c := range s {
		t[i] = int(c - '0')
	}
	c := [10]int{}
	for i := 1; i <= n; i++ {
		v := t[i-1]
		for j := 0; j < 10; j++ {
			for k := 0; k < 10; k++ {
				pre[i][j][k] = pre[i-1][j][k]
			}
		}
		for j := 0; j < 10; j++ {
			pre[i][j][v] += c[j]
		}
		c[v]++
	}
	c = [10]int{}
	for i := n; i > 0; i-- {
		v := t[i-1]
		for j := 0; j < 10; j++ {
			for k := 0; k < 10; k++ {
				suf[i][j][k] = suf[i+1][j][k]
			}
		}
		for j := 0; j < 10; j++ {
			suf[i][j][v] += c[j]
		}
		c[v]++
	}
	ans := 0
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 0; j < 10; j++ {
			for k := 0; k < 10; k++ {
				ans += pre[i-1][j][k] * suf[i+1][j][k]
				ans %= mod
			}
		}
	}
	return ans
}