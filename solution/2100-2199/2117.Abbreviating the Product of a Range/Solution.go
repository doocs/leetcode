func abbreviateProduct(left int, right int) string {
	cnt2, cnt5 := 0, 0
	for i := left; i <= right; i++ {
		x := i
		for x%2 == 0 {
			cnt2++
			x /= 2
		}
		for x%5 == 0 {
			cnt5++
			x /= 5
		}
	}
	c := int(math.Min(float64(cnt2), float64(cnt5)))
	cnt2 = c
	cnt5 = c
	suf := int64(1)
	pre := float64(1)
	gt := false
	for i := left; i <= right; i++ {
		for suf *= int64(i); cnt2 > 0 && suf%2 == 0; {
			cnt2--
			suf /= int64(2)
		}
		for cnt5 > 0 && suf%5 == 0 {
			cnt5--
			suf /= int64(5)
		}
		if float64(suf) >= 1e10 {
			gt = true
			suf %= int64(1e10)
		}
		for pre *= float64(i); pre > 1e5; {
			pre /= 10
		}
	}
	if gt {
		return fmt.Sprintf("%05d...%05de%d", int(pre), int(suf)%int(1e5), c)
	}
	return fmt.Sprintf("%de%d", suf, c)
}