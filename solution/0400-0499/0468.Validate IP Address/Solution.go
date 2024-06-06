func validIPAddress(queryIP string) string {
	if isIPv4(queryIP) {
		return "IPv4"
	}
	if isIPv6(queryIP) {
		return "IPv6"
	}
	return "Neither"
}

func isIPv4(s string) bool {
	if strings.HasSuffix(s, ".") {
		return false
	}
	ss := strings.Split(s, ".")
	if len(ss) != 4 {
		return false
	}
	for _, t := range ss {
		if len(t) == 0 || (len(t) > 1 && t[0] == '0') {
			return false
		}
		x := convert(t)
		if x < 0 || x > 255 {
			return false
		}
	}
	return true
}

func isIPv6(s string) bool {
	if strings.HasSuffix(s, ":") {
		return false
	}
	ss := strings.Split(s, ":")
	if len(ss) != 8 {
		return false
	}
	for _, t := range ss {
		if len(t) < 1 || len(t) > 4 {
			return false
		}
		for _, c := range t {
			if !unicode.IsDigit(c) && !strings.ContainsRune("0123456789abcdefABCDEF", c) {
				return false
			}
		}
	}
	return true
}

func convert(s string) int {
	x := 0
	for _, c := range s {
		if !unicode.IsDigit(c) {
			return -1
		}
		x = x*10 + int(c-'0')
		if x > 255 {
			return x
		}
	}
	return x
}