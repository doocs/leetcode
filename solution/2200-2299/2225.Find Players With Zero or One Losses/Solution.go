func findWinners(matches [][]int) [][]int {
	cnt := map[int]int{}
	for _, m := range matches {
		a, b := m[0], m[1]
		if _, ok := cnt[a]; !ok {
			cnt[a] = 0
		}
		cnt[b]++
	}
	ans := make([][]int, 2)
	for u, v := range cnt {
		if v < 2 {
			ans[v] = append(ans[v], u)
		}
	}
	sort.Ints(ans[0])
	sort.Ints(ans[1])
	return ans
}