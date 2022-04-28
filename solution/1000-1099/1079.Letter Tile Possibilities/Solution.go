func numTilePossibilities(tiles string) int {
	cnt := make([]int, 26)
	for _, c := range tiles {
		cnt[c-'A']++
	}
	var dfs func() int
	dfs = func() int {
		res := 0
		for i := 0; i < 26; i++ {
			if cnt[i] > 0 {
				res++
				cnt[i]--
				res += dfs()
				cnt[i]++
			}
		}
		return res
	}
	return dfs()
}