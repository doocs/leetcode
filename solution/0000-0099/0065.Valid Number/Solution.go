func isNumber(s string) bool {
	i, n := 0, len(s)
	if s[i] == '+' || s[i] == '-' {
		i++
	}
	if i == n {
		return false
	}
	if s[i] == '.' && (i+1 == n || s[i+1] == 'e' || s[i+1] == 'E') {
		return false
	}
	var dot, e int
	for j := i; j < n; j++ {
		if s[j] == '.' {
			if e > 0 || dot > 0 {
				return false
			}
			dot++
		} else if s[j] == 'e' || s[j] == 'E' {
			if e > 0 || j == i || j == n-1 {
				return false
			}
			e++
			if s[j+1] == '+' || s[j+1] == '-' {
				j++
				if j == n-1 {
					return false
				}
			}
		} else if s[j] < '0' || s[j] > '9' {
			return false
		}
	}
	return true
}