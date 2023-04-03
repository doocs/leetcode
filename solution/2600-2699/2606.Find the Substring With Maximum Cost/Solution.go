func maximumCostSubstring(s string, chars string, vals []int) (ans int) {
	d := [26]int{}
	for i := range d {
		d[i] = i + 1
	}
	for i, c := range chars {
		d[c-'a'] = vals[i]
	}
	f := 0
	for _, c := range s {
		v := d[c-'a']
		f = max(f, 0) + v
		ans = max(ans, f)
	}
	return
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