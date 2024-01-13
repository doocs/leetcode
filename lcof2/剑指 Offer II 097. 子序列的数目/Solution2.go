func numDistinct(s string, t string) int {
	n := len(t)
	f := make([]int, n+1)
	f[0] = 1
	for _, a := range s {
		for j := n; j > 0; j-- {
			if b := t[j-1]; byte(a) == b {
				f[j] += f[j-1]
			}
		}
	}
	return f[n]
}