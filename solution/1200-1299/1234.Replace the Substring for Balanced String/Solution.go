func balancedString(s string) int {
	cnt := [4]int{}
	t := "QWER"
	n := len(s)
	for i := range s {
		cnt[strings.IndexByte(t, s[i])]++
	}
	m := n / 4
	if cnt[0] == m && cnt[1] == m && cnt[2] == m && cnt[3] == m {
		return 0
	}
	ans := n
	for i, j := 0, 0; i < n; i++ {
		cnt[strings.IndexByte(t, s[i])]--
		for j <= i && cnt[0] <= m && cnt[1] <= m && cnt[2] <= m && cnt[3] <= m {
			ans = min(ans, i-j+1)
			cnt[strings.IndexByte(t, s[j])]++
			j++
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}