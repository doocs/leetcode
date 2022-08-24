func hasAllCodes(s string, k int) bool {
	n := len(s)
	if n-k+1 < (1 << k) {
		return false
	}
	vis := make([]bool, 1<<k)
	num := 0
	for i := 0; i < k; i++ {
		num = num<<1 | int(s[i]-'0')
	}
	vis[num] = true
	for i := k; i < n; i++ {
		a := int(s[i-k]-'0') << (k - 1)
		num = (num-a)<<1 | int(s[i]-'0')
		vis[num] = true
	}
	for _, v := range vis {
		if !v {
			return false
		}
	}
	return true
}