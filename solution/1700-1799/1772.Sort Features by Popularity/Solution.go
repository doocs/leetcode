func sortFeatures(features []string, responses []string) []string {
	cnt := map[string]int{}
	for _, r := range responses {
		ws := map[string]bool{}
		for _, s := range strings.Split(r, " ") {
			ws[s] = true
		}
		for w := range ws {
			cnt[w]++
		}
	}
	n := len(features)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool {
		d := cnt[features[idx[i]]] - cnt[features[idx[j]]]
		return d > 0 || (d == 0 && idx[i] < idx[j])
	})
	ans := make([]string, n)
	for i := range ans {
		ans[i] = features[idx[i]]
	}
	return ans
}