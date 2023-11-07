func scoreOfStudents(s string, answers []int) int {
	n := len(s)
	x := cal(s)
	m := (n + 1) >> 1
	f := make([][]map[int]bool, m)
	for i := range f {
		f[i] = make([]map[int]bool, m)
		for j := range f[i] {
			f[i][j] = make(map[int]bool)
		}
		f[i][i][int(s[i<<1]-'0')] = true
	}
	for i := m - 1; i >= 0; i-- {
		for j := i; j < m; j++ {
			for k := i; k < j; k++ {
				for l := range f[i][k] {
					for r := range f[k+1][j] {
						op := s[k<<1|1]
						if op == '+' && l+r <= 1000 {
							f[i][j][l+r] = true
						} else if op == '*' && l*r <= 1000 {
							f[i][j][l*r] = true
						}
					}
				}
			}
		}
	}
	cnt := [1001]int{}
	for _, v := range answers {
		cnt[v]++
	}
	ans := cnt[x] * 5
	for k, v := range cnt {
		if k != x && f[0][m-1][k] {
			ans += v << 1
		}
	}
	return ans
}

func cal(s string) int {
	res, pre := 0, int(s[0]-'0')
	for i := 1; i < len(s); i += 2 {
		cur := int(s[i+1] - '0')
		if s[i] == '+' {
			res += pre
			pre = cur
		} else {
			pre *= cur
		}
	}
	res += pre
	return res
}