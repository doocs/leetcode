func findCommonResponse(responses [][]string) string {
	cnt := map[string]int{}
	for _, ws := range responses {
		s := map[string]struct{}{}
		for _, w := range ws {
			if _, ok := s[w]; !ok {
				s[w] = struct{}{}
				cnt[w]++
			}
		}
	}
	ans := responses[0][0]
	for w, v := range cnt {
		if cnt[ans] < v || (cnt[ans] == v && w < ans) {
			ans = w
		}
	}
	return ans
}
