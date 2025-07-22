func binaryGap(n int) (ans int) {
	for pre, cur := 100, 0; n != 0; n >>= 1 {
		if n&1 == 1 {
			ans = max(ans, cur-pre)
			pre = cur
		}
		cur++
	}
	return
}
