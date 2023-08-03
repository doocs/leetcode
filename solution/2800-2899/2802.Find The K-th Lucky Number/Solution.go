func kthLuckyNumber(k int) string {
	n := 1
	for k > 1<<n {
		k -= 1 << n
		n++
	}
	ans := []byte{}
	for n > 0 {
		n--
		if k <= 1<<n {
			ans = append(ans, '4')
		} else {
			ans = append(ans, '7')
			k -= 1 << n
		}
	}
	return string(ans)
}