func mostPopularCreator(creators []string, ids []string, views []int) (ans [][]string) {
	cnt := map[string]int{}
	d := map[string]int{}
	x := map[string]string{}
	for k, c := range creators {
		i, v := ids[k], views[k]
		cnt[c] += v
		if t, ok := d[c]; !ok || t < v || (t == v && x[c] > i) {
			d[c] = v
			x[c] = i
		}
	}
	pre := -1
	for a, b := range cnt {
		if b > pre {
			ans = [][]string{[]string{a, x[a]}}
			pre = b
		} else if b == pre {
			ans = append(ans, []string{a, x[a]})
		}
	}
	return ans
}