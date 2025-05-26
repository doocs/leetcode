func maxFreqSum(s string) int {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	a, b := 0, 0
	for i := range cnt {
		c := byte(i + 'a')
		if c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' {
			a = max(a, cnt[i])
		} else {
			b = max(b, cnt[i])
		}
	}
	return a + b
}