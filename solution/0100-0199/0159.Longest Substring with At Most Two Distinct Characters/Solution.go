func lengthOfLongestSubstringTwoDistinct(s string) int {
	mp := make(map[byte]int)
	i, j, ans := 0, 0, 0
	for _, c := range s {
		mp[byte(c)]++
		for len(mp) > 2 {
			mp[s[i]]--
			if mp[s[i]] == 0 {
				delete(mp, s[i])
			}
			i++
		}
		ans = max(ans, j-i+1)
		j++
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}