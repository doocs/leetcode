func smallestStringWithSwaps(s string, pairs [][]int) string {
	n := len(s)
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, pair := range pairs {
		p[find(pair[0])] = find(pair[1])
	}
	mp := make(map[int][]rune)
	for i, c := range s {
		mp[find(i)] = append(mp[find(i)], c)
	}
	for _, v := range mp {
		sort.Slice(v, func(i, j int) bool {
			return v[i] < v[j]
		})
	}
	var ans []rune
	for i := 0; i < n; i++ {
		ans = append(ans, mp[find(i)][0])
		mp[find(i)] = mp[find(i)][1:]
	}
	return string(ans)
}