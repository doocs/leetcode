func averageHeightOfBuildings(buildings [][]int) [][]int {
	height := make(map[int]int)
	cnt := make(map[int]int)
	for _, v := range buildings {
		s, e, h := v[0], v[1], v[2]
		cnt[s]++
		cnt[e]--
		height[s] += h
		height[e] -= h
	}
	keys := make([]int, len(cnt))
	for k := range cnt {
		keys = append(keys, k)
	}
	sort.Ints(keys)
	i, h, n := 0, 0, 0
	ans := [][]int{}
	for _, j := range keys {
		if n > 0 {
			t := []int{i, j, h / n}
			if len(ans) > 0 && ans[len(ans)-1][1] == i && ans[len(ans)-1][2] == t[2] {
				ans[len(ans)-1][1] = j
			} else {
				ans = append(ans, t)
			}
		}
		i = j
		h += height[j]
		n += cnt[j]
	}
	return ans
}