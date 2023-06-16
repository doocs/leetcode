func maximumValue(strs []string) (ans int) {
	f := func(s string) (x int) {
		for _, c := range s {
			if c >= 'a' && c <= 'z' {
				return len(s)
			}
			x = x*10 + int(c-'0')
		}
		return
	}
	for _, s := range strs {
		if x := f(s); ans < x {
			ans = x
		}
	}
	return
}