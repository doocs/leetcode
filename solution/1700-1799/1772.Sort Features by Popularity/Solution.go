func sortFeatures(features []string, responses []string) []string {
	cnt := map[string]int{}
	for _, s := range responses {
		vis := map[string]bool{}
		for _, w := range strings.Split(s, " ") {
			if !vis[w] {
				cnt[w]++
				vis[w] = true
			}
		}
	}
	sort.SliceStable(features, func(i, j int) bool { return cnt[features[i]] > cnt[features[j]] })
	return features
}