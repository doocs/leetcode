func canPermutePalindrome(s string) bool {
	vis := map[rune]bool{}
	for _, c := range s {
		if vis[c] {
			delete(vis, c)
		} else {
			vis[c] = true
		}
	}
	return len(vis) < 2
}
