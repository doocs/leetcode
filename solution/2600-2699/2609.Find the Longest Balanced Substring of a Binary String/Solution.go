func findTheLongestBalancedSubstring(s string) (ans int) {
	n := len(s)
	check := func(i, j int) bool {
		cnt := 0
		for k := i; k <= j; k++ {
			if s[k] == '1' {
				cnt++
			} else if cnt > 0 {
				return false
			}
		}
		return cnt*2 == j-i+1
	}
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if check(i, j) {
				ans = max(ans, j-i+1)
			}
		}
	}
	return
}