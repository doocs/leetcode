func beautySum(s string) int {
	ans, n := 0, len(s)
	for i := 0; i < n; i++ {
		counter := make([]int, 26)
		for j := i; j < n; j++ {
			counter[s[j]-'a']++
			mi, mx := 1000, 0
			for _, v := range counter {
				if v > 0 {
					mi = min(mi, v)
					mx = max(mx, v)
				}
			}
			ans += mx - mi
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}