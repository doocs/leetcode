func processStr(s string, k int64) byte {
	var m int64 = 0
	for i := 0; i < len(s); i++ {
		c := s[i]
		if c == '*' {
			if m-1 > 0 {
				m = m - 1
			} else {
				m = 0
			}
		} else if c == '#' {
			m <<= 1
		} else if c != '%' {
			m += 1
		}
	}
	if k >= m {
		return '.'
	}
	for i := len(s) - 1; ; i-- {
		c := s[i]
		if c == '*' {
			m += 1
		} else if c == '#' {
			m /= 2
			if k >= m {
				k -= m
			}
		} else if c == '%' {
			k = m - 1 - k
		} else {
			m -= 1
			if k == m {
				return c
			}
		}
	}
}