func maximumCoins(heroes []int, monsters []int, coins []int) (ans []int64) {
	m := len(monsters)
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return monsters[idx[i]] < monsters[idx[j]] })
	s := make([]int64, m+1)
	for i, j := range idx {
		s[i+1] = s[i] + int64(coins[j])
	}
	for _, h := range heroes {
		i := sort.Search(m, func(i int) bool { return monsters[idx[i]] > h })
		ans = append(ans, s[i])
	}
	return
}