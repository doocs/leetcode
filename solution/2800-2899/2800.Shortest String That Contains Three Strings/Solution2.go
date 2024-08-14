func minimumString(a string, b string, c string) string {
	f := func(s, t string) string {
		if strings.Contains(s, t) {
			return s
		}
		if strings.Contains(t, s) {
			return t
		}
		p := t + "#" + s + "$"
		n := len(p)
		next := make([]int, n)
		next[0] = -1
		for i, j := 2, 0; i < n; {
			if p[i-1] == p[j] {
				j++
				next[i] = j
				i++
			} else if j > 0 {
				j = next[j]
			} else {
				next[i] = 0
				i++
			}
		}
		return s + t[next[n-1]:]
	}
	s := [3]string{a, b, c}
	ans := ""
	for _, p := range [][]int{{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}} {
		i, j, k := p[0], p[1], p[2]
		t := f(f(s[i], s[j]), s[k])
		if ans == "" || len(t) < len(ans) || (len(t) == len(ans) && t < ans) {
			ans = t
		}
	}
	return ans
}
