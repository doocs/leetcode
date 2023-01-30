func canConvertString(s string, t string, k int) bool {
	if len(s) != len(t) {
		return false
	}
	cnt := [26]int{}
	for i := range s {
		x := (t[i] - s[i] + 26) % 26
		cnt[x]++
	}
	for i := 1; i < 26; i++ {
		if i+26*(cnt[i]-1) > k {
			return false
		}
	}
	return true
}