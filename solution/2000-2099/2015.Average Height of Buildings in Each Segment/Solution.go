func averageHeightOfBuildings(buildings [][]int) [][]int {
	cnt := make(map[int]int)
	d := make(map[int]int)

	for _, e := range buildings {
		start, end, height := e[0], e[1], e[2]
		cnt[start]++
		cnt[end]--
		d[start] += height
		d[end] -= height
	}

	s, m := 0, 0
	last := -1
	var ans [][]int

	keys := make([]int, 0, len(d))
	for k := range d {
		keys = append(keys, k)
	}
	sort.Ints(keys)

	for _, k := range keys {
		v := d[k]
		if m > 0 {
			avg := s / m
			if len(ans) > 0 && ans[len(ans)-1][2] == avg && ans[len(ans)-1][1] == last {
				ans[len(ans)-1][1] = k
			} else {
				ans = append(ans, []int{last, k, avg})
			}
		}
		s += v
		m += cnt[k]
		last = k
	}

	return ans
}
