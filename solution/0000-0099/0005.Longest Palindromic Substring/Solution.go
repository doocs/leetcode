func longestPalindrome(s string) string {
	length := len(s)
	if length == 0 {
		return ""
	}
	res := make([][]bool, length)
	for i := 0; i < length; i++ {
		res[i] = make([]bool, length)
	}
	max := 1
	start := 0
	for i := 0; i < length; i++ {
		for j := 0; j <= i; j++ {
			if i - j < 2  {
				res[j][i] = s[j] == s[i]
			} else {
				res[j][i] = s[j] == s[i] && res[j+1][i-1]
			}

			if res[j][i] && max < i - j + 1 {
				max = i - j + 1
				start = j
			}
		}
	}

	return s[start:start + max]
}