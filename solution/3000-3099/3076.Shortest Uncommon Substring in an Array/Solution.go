func shortestSubstrings(arr []string) []string {
	ans := make([]string, len(arr))
	for i, s := range arr {
		m := len(s)
		for j := 1; j <= m && len(ans[i]) == 0; j++ {
			for l := 0; l <= m-j; l++ {
				sub := s[l : l+j]
				if len(ans[i]) == 0 || ans[i] > sub {
					ok := true
					for k, t := range arr {
						if k != i && strings.Contains(t, sub) {
							ok = false
							break
						}
					}
					if ok {
						ans[i] = sub
					}
				}
			}
		}
	}
	return ans
}