func topKFrequent(words []string, k int) []string {
	cnt := map[string]int{}
	for _, v := range words {
		cnt[v]++
	}
	ans := []string{}
	for v := range cnt {
		ans = append(ans, v)
	}
	sort.Slice(ans, func(i, j int) bool {
		a, b := ans[i], ans[j]
		return cnt[a] > cnt[b] || cnt[a] == cnt[b] && a < b
	})
	return ans[:k]
}