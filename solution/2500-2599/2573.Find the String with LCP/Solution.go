func findTheString(lcp [][]int) string {
	i, n := 0, len(lcp)
	s := make([]byte, n)
	for c := 'a'; c <= 'z'; c++ {
		for i < n && s[i] != 0 {
			i++
		}
		if i == n {
			break
		}
		for j := i; j < n; j++ {
			if lcp[i][j] > 0 {
				s[j] = byte(c)
			}
		}
	}
	if bytes.IndexByte(s, 0) >= 0 {
		return ""
	}
	for i := n - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if s[i] == s[j] {
				if i == n-1 || j == n-1 {
					if lcp[i][j] != 1 {
						return ""
					}
				} else if lcp[i][j] != lcp[i+1][j+1]+1 {
					return ""
				}
			} else if lcp[i][j] > 0 {
				return ""
			}
		}
	}
	return string(s)
}