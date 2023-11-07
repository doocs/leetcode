func getMaxRepetitions(s1 string, n1 int, s2 string, n2 int) (ans int) {
	n := len(s2)
	d := make([][2]int, n)
	for i := 0; i < n; i++ {
		j := i
		cnt := 0
		for k := range s1 {
			if s1[k] == s2[j] {
				j++
				if j == n {
					cnt++
					j = 0
				}
			}
		}
		d[i] = [2]int{cnt, j}
	}
	for j := 0; n1 > 0; n1-- {
		ans += d[j][0]
		j = d[j][1]
	}
	ans /= n2
	return
}