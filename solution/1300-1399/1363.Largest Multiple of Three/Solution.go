func largestMultipleOfThree(digits []int) string {
	sort.Ints(digits)
	n := len(digits)
	const inf = 1 << 30
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, 3)
		for j := range f[i] {
			f[i][j] = -inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= n; i++ {
		for j := 0; j < 3; j++ {
			f[i][j] = max(f[i-1][j], f[i-1][(j-digits[i-1]%3+3)%3]+1)
		}
	}
	if f[n][0] <= 0 {
		return ""
	}
	ans := []byte{}
	for i, j := n, 0; i > 0; i-- {
		k := (j - digits[i-1]%3 + 3) % 3
		if f[i][j] == f[i-1][k]+1 {
			ans = append(ans, byte('0'+digits[i-1]))
			j = k
		}
	}
	i := 0
	for i < len(ans)-1 && ans[i] == '0' {
		i++
	}
	return string(ans[i:])
}