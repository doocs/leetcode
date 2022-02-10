func minNumberOfFrogs(croakOfFrogs string) int {
	c, r, o, a, k, ans := 0, 0, 0, 0, 0, 0
	for i := range croakOfFrogs {
		ch := croakOfFrogs[i]
		if ch == 'c' {
			c++
			if k > 0 {
				k--
			} else {
				ans++
			}
		} else if ch == 'r' {
			r++
			c--
		} else if ch == 'o' {
			o++
			r--
		} else if ch == 'a' {
			a++
			o--
		} else {
			k++
			a--
		}
		if c < 0 || r < 0 || o < 0 || a < 0 {
			return -1
		}
	}
	if c == 0 && r == 0 && o == 0 && a == 0 {
		return ans
	}
	return -1
}