func isMatch(s string, p string) bool {
	lenS := len(s)
	lenP := len(p)
	// init dp note
	note := make([][]int8, lenS+1)
	for i:=0; i<=lenS; i++  {
		note[i] = make([]int8, lenP+1)
		for j:=0; j<=lenP; j++ {
			note[i][j] = -1
		}
	}

	return dp(0, 0, s, p, note)
}

func dp(i,j int, s,p string, note [][]int8) bool {
	if note[i][j] != -1 {
		return note[i][j] == 1
	}
	var ans bool
	if j == len(p) {
		ans = i == len(s)
	} else {
		fm := i < len(s) && (s[i] == p[j] || p[j] == '.')
		if j + 1 < len(p) && p[j+1] == '*' {
			ans = dp(i, j+2, s, p, note) || (fm && dp(i+1, j, s, p, note))
		} else {
			ans = fm && dp(i+1, j+1, s, p, note)
		}
	}

	if ans {
		note[i][j] = 1
	} else {
		note[i][j] = 0
	}
	return ans
}