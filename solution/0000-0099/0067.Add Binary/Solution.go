func addBinary(a string, b string) string {
	i, j := len(a)-1, len(b)-1
	ans := []byte{}
	for carry := 0; i >= 0 || j >= 0 || carry > 0; i, j = i-1, j-1 {
		if i >= 0 {
			carry += int(a[i] - '0')
		}
		if j >= 0 {
			carry += int(b[j] - '0')
		}
		ans = append(ans, byte(carry%2+'0'))
		carry /= 2
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}