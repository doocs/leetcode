func minimumTime(s string) int {
	n := len(s)
	pre := make([]int, n+1)
	suf := make([]int, n+1)
	for i, c := range s {
		pre[i+1] = pre[i]
		if c == '1' {
			pre[i+1] = min(pre[i]+2, i+1)
		}
	}
	for i := n - 1; i >= 0; i-- {
		suf[i] = suf[i+1]
		if s[i] == '1' {
			suf[i] = min(suf[i+1]+2, n-i)
		}
	}
	ans := 0x3f3f3f3f
	for i := 1; i <= n; i++ {
		ans = min(ans, pre[i]+suf[i])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}