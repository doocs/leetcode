func rankTeams(votes []string) string {
	cnt := [26][26]int{}
	for _, vote := range votes {
		for i, c := range vote {
			cnt[c-'A'][i]++
		}
	}
	ans := []byte(votes[0])
	sort.Slice(ans, func(i, j int) bool {
		cnt1, cnt2 := cnt[ans[i]-'A'], cnt[ans[j]-'A']
		for k, a := range cnt1 {
			b := cnt2[k]
			if a != b {
				return a > b
			}
		}
		return ans[i] < ans[j]
	})
	return string(ans)
}