func removeZeros(n int64) (ans int64) {
	k := int64(1)
	for n > 0 {
		x := n % 10
		if x > 0 {
			ans = k*x + ans
			k *= 10
		}
		n /= 10
	}
	return
}
