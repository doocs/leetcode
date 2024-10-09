func edgeScore(edges []int) (ans int) {
	cnt := make([]int, len(edges))
	for i, j := range edges {
		cnt[j] += i
		if cnt[ans] < cnt[j] || (cnt[ans] == cnt[j] && j < ans) {
			ans = j
		}
	}
	return
}
