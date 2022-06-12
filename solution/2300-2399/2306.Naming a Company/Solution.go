func distinctNames(ideas []string) int64 {
	s := map[string]bool{}
	for _, v := range ideas {
		s[v] = true
	}
	f := make([][]int, 26)
	for i := range f {
		f[i] = make([]int, 26)
	}
	for _, v := range ideas {
		i := int(v[0] - 'a')
		t := []byte(v)
		for j := 0; j < 26; j++ {
			t[0] = 'a' + byte(j)
			if !s[string(t)] {
				f[i][j]++
			}
		}
	}
	var ans int64
	for _, v := range ideas {
		i := int(v[0] - 'a')
		t := []byte(v)
		for j := 0; j < 26; j++ {
			t[0] = 'a' + byte(j)
			if !s[string(t)] {
				ans += int64(f[j][i])
			}
		}
	}
	return ans
}