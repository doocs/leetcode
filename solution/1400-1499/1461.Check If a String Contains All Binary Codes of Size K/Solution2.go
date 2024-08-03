func hasAllCodes(s string, k int) bool {
	n, m := len(s), 1<<k
	if n-k+1 < m {
		return false
	}
	ss := make([]bool, m)
	x, _ := strconv.ParseInt(s[:k], 2, 64)
	ss[x] = true
	for i := k; i < n; i++ {
		a := int64(s[i-k]-'0') << (k - 1)
		b := int64(s[i] - '0')
		x = (x-a)<<1 | b
		ss[x] = true
	}
	for _, v := range ss {
		if !v {
			return false
		}
	}
	return true
}
