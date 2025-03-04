func countPalindromicSubsequence(s string) (ans int) {
	for c := 'a'; c <= 'z'; c++ {
		l, r := strings.Index(s, string(c)), strings.LastIndex(s, string(c))
		mask := 0
		for i := l + 1; i < r; i++ {
			j := int(s[i] - 'a')
			if mask>>j&1 == 0 {
				mask |= 1 << j
				ans++
			}
		}
	}
	return
}
