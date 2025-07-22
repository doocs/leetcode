func countGoodSubstrings(s string) (ans int) {
	mask, l := 0, 0
	for r, c := range s {
		x := int(c - 'a')
		for (mask>>x)&1 == 1 {
			y := int(s[l] - 'a')
			l++
			mask ^= 1 << y
		}
		mask |= 1 << x
		if r-l+1 >= 3 {
			ans++
		}
	}
	return
}
