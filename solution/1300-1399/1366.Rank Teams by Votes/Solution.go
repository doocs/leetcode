func rankTeams(votes []string) string {
	n := len(votes[0])
	counter := make(map[byte][]int)
	for _, v := range votes[0] {
		counter[byte(v)] = make([]int, n)
	}
	for _, vote := range votes {
		for i, v := range vote {
			counter[byte(v)][i]++
		}
	}
	ans := []byte(votes[0])
	sort.Slice(ans, func(i, j int) bool {
		v1, v2 := counter[ans[i]], counter[ans[j]]
		for i := range v1 {
			if v1[i] != v2[i] {
				return v1[i] > v2[i]
			}
		}
		return ans[i] < ans[j]
	})
	return string(ans)
}