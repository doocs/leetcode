func findValidPair(s string) string {
	cnt := [10]int{}
	for _, c := range s {
		cnt[c-'0']++
	}
	for i := 1; i < len(s); i++ {
		x, y := int(s[i-1]-'0'), int(s[i]-'0')
		if x != y && cnt[x] == x && cnt[y] == y {
			return s[i-1 : i+1]
		}
	}
	return ""
}
