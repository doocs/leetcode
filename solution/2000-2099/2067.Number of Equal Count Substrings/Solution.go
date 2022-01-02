func equalCountSubstrings(s string, count int) int {
	n := len(s)
	if count > n {
		return 0
	}
	counter := make([][]int, n+1)
	for i := range counter {
		counter[i] = make([]int, 26)
	}
	ans := 0
	check := func(i, j int) bool {
		c1, c2 := counter[i], counter[j+1]
		for k := 0; k < 26; k++ {
			if c2[k] == 0 || c1[k] == c2[k] {
				continue
			}
			if c2[k]-c1[k] != count {
				return false
			}
		}
		return true
	}
	for i, c := range s {
		idx := c - 'a'
		for j := 0; j < 26; j++ {
			counter[i+1][j] = counter[i][j]
		}
		counter[i+1][idx] = counter[i][idx] + 1
		l := 0
		for k := 0; k < 26; k++ {
			l += count
			j := i - l + 1
			if j < 0 {
				break
			}
			if check(j, i) {
				ans++
			}
		}
	}
	return ans
}