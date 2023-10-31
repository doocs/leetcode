func minFlips(s string) int {
	n := len(s)
	target := "01"
	cnt := 0
	for i := range s {
		if s[i] != target[i&1] {
			cnt++
		}
	}
	ans := min(cnt, n-cnt)
	for i := range s {
		if s[i] != target[i&1] {
			cnt--
		}
		if s[i] != target[(i+n)&1] {
			cnt++
		}
		ans = min(ans, min(cnt, n-cnt))
	}
	return ans
}