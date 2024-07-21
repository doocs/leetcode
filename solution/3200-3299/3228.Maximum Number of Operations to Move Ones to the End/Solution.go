func maxOperations(s string) (ans int) {
	cnt := 0
	for i, c := range s {
		if c == '1' {
			cnt++
		} else if i > 0 && s[i-1] == '1' {
			ans += cnt
		}
	}
	return
}