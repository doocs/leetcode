func removeKdigits(num string, k int) string {
	stk, remain := make([]byte, 0), len(num)-k
	for i := 0; i < len(num); i++ {
		n := len(stk)
		for k > 0 && n > 0 && stk[n-1] > num[i] {
			stk = stk[:n-1]
			n, k = n-1, k-1
		}
		stk = append(stk, num[i])
	}

	for i := 0; i < len(stk) && i < remain; i++ {
		if stk[i] != '0' {
			return string(stk[i:remain])
		}
	}
	return "0"
}