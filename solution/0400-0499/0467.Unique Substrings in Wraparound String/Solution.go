func findSubstringInWraproundString(p string) int {
	dp := make([]int, 26)
	k := 0
	for i := range p {
		c := p[i]
		if i > 0 && (c-p[i-1]+26)%26 == 1 {
			k++
		} else {
			k = 1
		}
		dp[c-'a'] = max(dp[c-'a'], k)
	}
	ans := 0
	for _, v := range dp {
		ans += v
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}