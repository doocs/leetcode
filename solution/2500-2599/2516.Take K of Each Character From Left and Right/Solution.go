func takeCharacters(s string, k int) int {
	cnt := [3]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for _, x := range cnt {
		if x < k {
			return -1
		}
	}
	mx, j := 0, 0
	for i, c := range s {
		c -= 'a'
		for cnt[c]--; cnt[c] < k; j++ {
			cnt[s[j]-'a']++
		}
		mx = max(mx, i-j+1)
	}
	return len(s) - mx
}