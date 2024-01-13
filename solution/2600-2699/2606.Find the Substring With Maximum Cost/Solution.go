func maximumCostSubstring(s string, chars string, vals []int) (ans int) {
	d := [26]int{}
	for i := range d {
		d[i] = i + 1
	}
	for i, c := range chars {
		d[c-'a'] = vals[i]
	}
	tot, mi := 0, 0
	for _, c := range s {
		v := d[c-'a']
		tot += v
		ans = max(ans, tot-mi)
		mi = min(mi, tot)
	}
	return
}