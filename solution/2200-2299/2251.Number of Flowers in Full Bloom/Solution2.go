func fullBloomFlowers(flowers [][]int, people []int) []int {
	d := map[int]int{}
	for _, f := range flowers {
		d[f[0]]++
		d[f[1]+1]--
	}
	ts := []int{}
	for t := range d {
		ts = append(ts, t)
	}
	sort.Ints(ts)
	m := len(people)
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return people[idx[i]] < people[idx[j]] })
	ans := make([]int, m)
	s, i := 0, 0
	for _, j := range idx {
		t := people[j]
		for i < len(ts) && ts[i] <= t {
			s += d[ts[i]]
			i++
		}
		ans[j] = s
	}
	return ans
}