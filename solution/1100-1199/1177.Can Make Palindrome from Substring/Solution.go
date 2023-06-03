func canMakePaliQueries(s string, queries [][]int) (ans []bool) {
	n := len(s)
	ss := make([][26]int, n+1)
	for i := 1; i <= n; i++ {
		for j := 0; j < 26; j++ {
			ss[i][j] = ss[i-1][j]
		}
		ss[i][s[i-1]-'a']++
	}
	for _, q := range queries {
		l, r, k := q[0], q[1], q[2]
		x := 0
		for j := 0; j < 26; j++ {
			x += (ss[r+1][j] - ss[l][j]) & 1
		}
		ans = append(ans, x/2 <= k)
	}
	return
}