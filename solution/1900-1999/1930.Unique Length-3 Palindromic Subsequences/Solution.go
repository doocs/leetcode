func countPalindromicSubsequence(s string) (ans int) {
	for c := 'a'; c <= 'z'; c++ {
		l, r := strings.Index(s, string(c)), strings.LastIndex(s, string(c))
		cs := map[byte]struct{}{}
		for i := l + 1; i < r; i++ {
			cs[s[i]] = struct{}{}
		}
		ans += len(cs)
	}
	return
}