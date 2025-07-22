func maxLength(arr []string) (ans int) {
	s := []int{0}
	for _, t := range arr {
		x := 0
		for _, c := range t {
			b := int(c - 'a')
			if (x>>b)&1 == 1 {
				x = 0
				break
			}
			x |= 1 << b
		}
		if x > 0 {
			for i := len(s) - 1; i >= 0; i-- {
				y := s[i]
				if (x & y) == 0 {
					s = append(s, x|y)
					ans = max(ans, bits.OnesCount(uint(x|y)))
				}
			}
		}
	}
	return ans
}
