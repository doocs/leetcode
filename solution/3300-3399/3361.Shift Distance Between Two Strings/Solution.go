func shiftDistance(s string, t string, nextCost []int, previousCost []int) (ans int64) {
	m := 26
	s1 := make([]int64, (m<<1)+1)
	s2 := make([]int64, (m<<1)+1)
	for i := 0; i < (m << 1); i++ {
		s1[i+1] = s1[i] + int64(nextCost[i%m])
		s2[i+1] = s2[i] + int64(previousCost[(i+1)%m])
	}
	for i := 0; i < len(s); i++ {
		x := int(s[i] - 'a')
		y := int(t[i] - 'a')
		z := y
		if y < x {
			z += m
		}
		c1 := s1[z] - s1[x]
		z = x
		if x < y {
			z += m
		}
		c2 := s2[z] - s2[y]
		ans += min(c1, c2)
	}
	return
}
