func findLongestSubarray(array []string) []string {
	seen := map[int]int{0: -1}
	t, mx, j := 0, 0, 0
	for i, s := range array {
		if unicode.IsDigit(rune(s[0])) {
			t++
		} else {
			t--
		}
		if k, ok := seen[t]; ok {
			if mx < i-k {
				mx = i - k
				j = k + 1
			}
		} else {
			seen[t] = i
		}
	}
	return array[j : j+mx]
}