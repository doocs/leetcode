func countPalindromicSubsequence(s string) int {
	res := 0
	for c := 'a'; c <= 'z'; c++ {
		l, r := strings.Index(s, string(c)), strings.LastIndex(s, string(c))
		chars := make(map[byte]bool)
		for i := l + 1; i < r; i++ {
			chars[s[i]] = true
		}
		res += len(chars)
	}
	return res
}