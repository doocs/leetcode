func countSubstrings(s string, c byte) int64 {
	cnt := int64(strings.Count(s, string(c)))
	return cnt + cnt*(cnt-1)/2
}