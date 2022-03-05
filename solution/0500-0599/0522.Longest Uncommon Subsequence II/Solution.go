func findLUSlength(strs []string) int {
	check := func(a, b string) bool {
		j := 0
		for i := 0; i < len(a) && j < len(b); i++ {
			if a[i] == b[j] {
				j++
			}
		}
		return j == len(b)
	}

	ans := -1
	for i, j, n := 0, 0, len(strs); i < n; i++ {
		for j = 0; j < n; j++ {
			if i == j {
				continue
			}
			if check(strs[j], strs[i]) {
				break
			}
		}
		if j == n && ans < len(strs[i]) {
			ans = len(strs[i])
		}
	}
	return ans
}