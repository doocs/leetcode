func canPermutePalindrome(s string) bool {
	vis := map[rune]bool{}
	cnt := 0
	for _, c := range s {
		if vis[c] {
			vis[c] = false
			cnt--
		} else {
			vis[c] = true
			cnt++
		}
	}
	return cnt < 2
}