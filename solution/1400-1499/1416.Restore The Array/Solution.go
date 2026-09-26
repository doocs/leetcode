func numberOfArrays(s string, k int) int {
	const mod = int(1e9 + 7)
	n := len(s)
	f := make([]int, n+1)
	f[n] = 1
	for i := n - 1; i >= 0; i-- {
		if s[i] == '0' {
			continue
		}
		x := 0
		for j := i; j < n; j++ {
			x = x*10 + int(s[j]-'0')
			if x > k {
				break
			}
			f[i] = (f[i] + f[j+1]) % mod
		}
	}
	return f[0]
}
