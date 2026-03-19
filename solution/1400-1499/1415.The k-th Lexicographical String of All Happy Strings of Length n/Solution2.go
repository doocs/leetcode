func getHappyString(n int, k int) string {
	if k > 3*(1<<(n-1)) {
		return ""
	}
	cs := "abc"
	ans := make([]byte, 0, n)
	for i := 0; i < n; i++ {
		remain := 1 << (n - i - 1)
		for j := 0; j < len(cs); j++ {
			c := cs[j]
			if len(ans) > 0 && ans[len(ans)-1] == c {
				continue
			}
			if k <= remain {
				ans = append(ans, c)
				break
			}
			k -= remain
		}
	}
	return string(ans)
}
