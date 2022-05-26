func longestDupSubstring(s string) string {
	base, n := 131, len(s)
	p := make([]int64, n+10)
	h := make([]int64, n+10)
	p[0] = 1
	for i := 0; i < n; i++ {
		p[i+1] = p[i] * int64(base)
		h[i+1] = h[i]*int64(base) + int64(s[i])
	}
	check := func(l int) string {
		vis := make(map[int64]bool)
		for i := 1; i+l-1 <= n; i++ {
			j := i + l - 1
			t := h[j] - h[i-1]*p[j-i+1]
			if vis[t] {
				return s[i-1 : j]
			}
			vis[t] = true
		}
		return ""
	}
	left, right := 0, n
	ans := ""
	for left < right {
		mid := (left + right + 1) >> 1
		t := check(mid)
		if len(t) > 0 {
			left = mid
			ans = t
		} else {
			right = mid - 1
		}
	}
	return ans
}