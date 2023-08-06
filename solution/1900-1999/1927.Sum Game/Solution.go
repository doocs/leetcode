func sumGame(num string) bool {
	n := len(num)
	var cnt1, cnt2, s1, s2 int
	for i := 0; i < n/2; i++ {
		if num[i] == '?' {
			cnt1++
		} else {
			s1 += int(num[i] - '0')
		}
	}
	for i := n / 2; i < n; i++ {
		if num[i] == '?' {
			cnt2++
		} else {
			s2 += int(num[i] - '0')
		}
	}
	return (cnt1+cnt2)%2 == 1 || s1-s2 != (cnt2-cnt1)*9/2
}