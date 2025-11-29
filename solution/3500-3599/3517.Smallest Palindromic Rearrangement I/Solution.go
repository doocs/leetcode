func smallestPalindrome(s string) string {
	cnt := make([]int, 26)
	for i := 0; i < len(s); i++ {
		cnt[s[i]-'a']++
	}

	t := make([]byte, 0, len(s)/2)
	var ch byte
	for c := byte('a'); c <= 'z'; c++ {
		v := cnt[c-'a'] / 2
		for i := 0; i < v; i++ {
			t = append(t, c)
		}
		cnt[c-'a'] -= v * 2
		if cnt[c-'a'] == 1 {
			ch = c
		}
	}

	totalLen := len(t) * 2
	if ch != 0 {
		totalLen++
	}
	var sb strings.Builder
	sb.Grow(totalLen)

	sb.Write(t)
	if ch != 0 {
		sb.WriteByte(ch)
	}
	for i := len(t) - 1; i >= 0; i-- {
		sb.WriteByte(t[i])
	}
	return sb.String()
}
