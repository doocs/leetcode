func topKFrequent(words []string, k int) (ans []string) {
	cnt := map[string]int{}
	for _, w := range words {
		cnt[w]++
	}
	for w := range cnt {
		ans = append(ans, w)
	}
	sort.Slice(ans, func(i, j int) bool { a, b := ans[i], ans[j]; return cnt[a] > cnt[b] || cnt[a] == cnt[b] && a < b })
	return ans[:k]
}
