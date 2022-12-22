func lengthOfLongestSubstringKDistinct(s string, k int) (ans int) {
	cnt := map[byte]int{}
	j := 0
	for i := range s {
		cnt[s[i]]++
		for len(cnt) > k {
			cnt[s[j]]--
			if cnt[s[j]] == 0 {
				delete(cnt, s[j])
			}
			j++
		}
		ans = max(ans, i-j+1)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}