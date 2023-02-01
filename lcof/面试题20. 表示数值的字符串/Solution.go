func isNumber(s string) bool {
	i, j := 0, len(s)-1
	for i < j && s[i] == ' ' {
		i++
	}
	for i <= j && s[j] == ' ' {
		j--
	}
	if i > j {
		return false
	}
	digit, dot, e := false, false, false
	for ; i <= j; i++ {
		if s[i] == '+' || s[i] == '-' {
			if i > 0 && s[i-1] != ' ' && s[i-1] != 'e' && s[i-1] != 'E' {
				return false
			}
		} else if s[i] >= '0' && s[i] <= '9' {
			digit = true
		} else if s[i] == '.' {
			if dot || e {
				return false
			}
			dot = true
		} else if s[i] == 'e' || s[i] == 'E' {
			if !digit || e {
				return false
			}
			digit, e = false, true
		} else {
			return false
		}
	}
	return digit
}