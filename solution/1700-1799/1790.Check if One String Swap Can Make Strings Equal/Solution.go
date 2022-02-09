func areAlmostEqual(s1 string, s2 string) bool {
	var c1, c2 byte
	cnt, n := 0, len(s1)
	for i := 0; i < n; i++ {
		if s1[i] != s2[i] {
			cnt++
			if (cnt == 2 && (c1 != s2[i] || c2 != s1[i])) || cnt > 2 {
				return false
			}
			c1, c2 = s1[i], s2[i]
		}
	}
	return cnt == 0 || cnt == 2
}