func getSmallestString(s string, k int) string {
	cs := []byte(s)
	for i, c1 := range cs {
		for c2 := byte('a'); c2 < c1; c2++ {
			d := int(min(c1-c2, 26-c1+c2))
			if d <= k {
				cs[i] = c2
				k -= d
				break
			}
		}
	}
	return string(cs)
}