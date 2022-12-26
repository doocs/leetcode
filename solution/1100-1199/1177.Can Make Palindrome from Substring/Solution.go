func canMakePaliQueries(s string, queries [][]int) (ans []bool) {
	n := len(s)
	cnt := make([][26]int, n+1)
	for i := 1; i <= n; i++ {
		j := s[i-1] - 'a'
		for k := 0; k < 26; k++ {
			cnt[i][k] = cnt[i-1][k]
		}
		cnt[i][j]++
	}
	for _, q := range queries {
		left, right, k := q[0], q[1], q[2]
		x := 0
		for j := 0; j < 26; j++ {
			x += (cnt[right+1][j] - cnt[left][j]) & 1
		}
		ans = append(ans, x/2 <= k)
	}
	return
}