func canAliceWin(a []string, b []string) bool {
	i, j, k := 1, 0, 1
	w := a[0]
	for {
		if k&1 == 1 {
			if j == len(b) {
				return true
			}
			if (b[j][0] == w[0] && w < b[j]) || b[j][0]-w[0] == 1 {
				w = b[j]
				k ^= 1
			}
			j++
		} else {
			if i == len(a) {
				return false
			}
			if (a[i][0] == w[0] && w < a[i]) || a[i][0]-w[0] == 1 {
				w = a[i]
				k ^= 1
			}
			i++
		}
	}
}