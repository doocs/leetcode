func countSubstrings(s string) (ans int) {
	n := len(s)
	f := func(i, j int) (cnt int) {
		for ; i >= 0 && j < n && s[i] == s[j]; i, j = i-1, j+1 {
			cnt++
		}
		return
	}
	for i := range s {
		ans += f(i, i)
		ans += f(i, i+1)
	}
	return
}