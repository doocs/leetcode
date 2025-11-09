func numberOfSubstrings(s string) int {
	n := len(s)
	nxt := make([]int, n+1)
	nxt[n] = n
	for i := n - 1; i >= 0; i-- {
		nxt[i] = nxt[i+1]
		if s[i] == '0' {
			nxt[i] = i
		}
	}
	ans := 0
	for i := 0; i < n; i++ {
		cnt0 := 0
		if s[i] == '0' {
			cnt0 = 1
		}
		j := i
		for j < n && int64(cnt0*cnt0) <= int64(n) {
			cnt1 := nxt[j+1] - i - cnt0
			if cnt1 >= cnt0*cnt0 {
				ans += min(nxt[j+1]-j, cnt1-cnt0*cnt0+1)
			}
			j = nxt[j+1]
			cnt0++
		}
	}
	return ans
}
