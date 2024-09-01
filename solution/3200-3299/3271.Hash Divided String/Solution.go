func stringHash(s string, k int) string {
	n := len(s)
	ans := make([]byte, 0, n/k)

	for i := 0; i < n; i += k {
		t := 0
		for j := i; j < i+k; j++ {
			t += int(s[j] - 'a')
		}
		hashedChar := t % 26
		ans = append(ans, 'a'+byte(hashedChar))
	}

	return string(ans)
}
