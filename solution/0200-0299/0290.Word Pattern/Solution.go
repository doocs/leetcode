func wordPattern(pattern string, s string) bool {
	ss := strings.Split(s, " ")
	n := len(pattern)
	if n != len(ss) {
		return false
	}
	c2str := make(map[byte]string)
	str2c := make(map[string]byte)
	for i := 0; i < n; i++ {
		k, v := pattern[i], ss[i]
		if c2str[k] != "" && c2str[k] != v {
			return false
		}
		if str2c[v] > 0 && str2c[v] != k {
			return false
		}
		c2str[k], str2c[v] = v, k
	}
	return true
}