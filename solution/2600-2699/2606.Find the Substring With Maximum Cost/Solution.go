func maximumCostSubstring(s string, chars string, vals []int) (ans int) {
	d := [26]int{}
	for i := range d {
		d[i] = -1
	}
	for i, c := range chars {
		d[c-'a'] = i
	}
	tot, mi := 0, 0
	for _, c := range s {
		j := int(c - 'a')
		v := j + 1
		if d[j] != -1 {
			v = vals[d[j]]
		}
		tot += v
		ans = max(ans, tot-mi)
		mi = min(mi, tot)
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