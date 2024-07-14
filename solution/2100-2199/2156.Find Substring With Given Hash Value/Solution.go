func subStrHash(s string, power int, modulo int, k int, hashValue int) string {
	h, p := 0, 1
	n := len(s)
	for i := n - 1; i >= n-k; i-- {
		val := int(s[i] - 'a' + 1)
		h = (h*power%modulo + val) % modulo
		if i != n-k {
			p = p * power % modulo
		}
	}
	j := n - k
	for i := n - k - 1; i >= 0; i-- {
		pre := int(s[i+k] - 'a' + 1)
		cur := int(s[i] - 'a' + 1)
		h = ((h-pre*p%modulo+modulo)*power%modulo + cur) % modulo
		if h == hashValue {
			j = i
		}
	}
	return s[j : j+k]
}