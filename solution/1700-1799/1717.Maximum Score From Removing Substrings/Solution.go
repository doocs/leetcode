func maximumGain(s string, x int, y int) int {
	if x < y {
		t := []rune(s)
		for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
			t[i], t[j] = t[j], t[i]
		}
		return maximumGain(string(t), y, x)
	}
	ans := 0
	var stk1 []byte
	var stk2 []byte
	for i := range s {
		if s[i] != 'b' {
			stk1 = append(stk1, s[i])
		} else {
			if len(stk1) > 0 && stk1[len(stk1)-1] == 'a' {
				stk1 = stk1[0 : len(stk1)-1]
				ans += x
			} else {
				stk1 = append(stk1, s[i])
			}
		}
	}
	for _, c := range stk1 {
		if c != 'a' {
			stk2 = append(stk2, c)
		} else {
			if len(stk2) > 0 && stk2[len(stk2)-1] == 'b' {
				stk2 = stk2[0 : len(stk2)-1]
				ans += y
			} else {
				stk2 = append(stk2, c)
			}
		}
	}
	return ans
}