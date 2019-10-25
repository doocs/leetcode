func romanToInt(s string) int {
	symbols := map[string]int{"I": 1, "V": 5, "X": 10, "L": 50, "C": 100, "D": 500, "M": 1000}
	ret := 0
	l := len(s)
	for i := 0; i < l-1; i++ {
		if symbols[s[i:i+1]] < symbols[s[i+1:i+2]] {
			ret -= symbols[s[i:i+1]]
		} else {
			ret += symbols[s[i:i+1]]
		}
	}
	ret += symbols[s[l-1:]]
	return ret
}
