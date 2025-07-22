func lengthOfLongestSubstringKDistinct(s string, k int) int {
	cnt := map[byte]int{}
	l := 0
	for _, c := range s {
		cnt[byte(c)]++
		if len(cnt) > k {
			cnt[s[l]]--
			if cnt[s[l]] == 0 {
				delete(cnt, s[l])
			}
			l++
		}
	}
	return len(s) - l
}