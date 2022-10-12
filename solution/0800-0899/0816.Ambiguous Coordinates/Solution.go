func ambiguousCoordinates(s string) []string {
	f := func(i, j int) []string {
		res := []string{}
		for k := 1; k <= j-i; k++ {
			l, r := s[i:i+k], s[i+k:j]
			ok := (l == "0" || l[0] != '0') && (r == "" || r[len(r)-1] != '0')
			if ok {
				t := ""
				if k < j-i {
					t = "."
				}
				res = append(res, l+t+r)
			}
		}
		return res
	}

	n := len(s)
	ans := []string{}
	for i := 2; i < n-1; i++ {
		for _, x := range f(1, i) {
			for _, y := range f(i, n-1) {
				ans = append(ans, "("+x+", "+y+")")
			}
		}
	}
	return ans
}