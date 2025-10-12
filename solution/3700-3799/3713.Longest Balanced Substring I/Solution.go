func longestBalanced(s string) (ans int) {
	n := len(s)
	for i := 0; i < n; i++ {
		cnt := [26]int{}
		mx, v := 0, 0
		for j := i; j < n; j++ {
			c := s[j] - 'a'
			cnt[c]++
			if cnt[c] == 1 {
				v++
			}
			mx = max(mx, cnt[c])
			if mx*v == j-i+1 {
				ans = max(ans, j-i+1)
			}
		}
	}

	return ans
}
