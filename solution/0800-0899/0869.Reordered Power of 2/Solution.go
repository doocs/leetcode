func reorderedPowerOf2(n int) bool {
	convert := func(n int) []byte {
		cnt := make([]byte, 10)
		for ; n > 0; n /= 10 {
			cnt[n%10]++
		}
		return cnt
	}
	s := convert(n)
	for i := 1; i <= 1e9; i <<= 1 {
		if bytes.Equal(s, convert(i)) {
			return true
		}
	}
	return false
}