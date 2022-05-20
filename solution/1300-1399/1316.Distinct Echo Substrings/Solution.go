func distinctEchoSubstrings(text string) int {
	n := len(text)
	base := 131
	h := make([]int, n+10)
	p := make([]int, n+10)
	p[0] = 1
	for i, c := range text {
		t := int(c-'a') + 1
		p[i+1] = p[i] * base
		h[i+1] = h[i]*base + t
	}
	get := func(l, r int) int {
		return h[r] - h[l-1]*p[r-l+1]
	}
	vis := map[int]bool{}
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j += 2 {
			k := (i + j) >> 1
			a, b := get(i+1, k+1), get(k+2, j+1)
			if a == b {
				vis[a] = true
			}
		}
	}
	return len(vis)
}