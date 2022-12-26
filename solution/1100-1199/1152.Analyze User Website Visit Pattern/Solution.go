func mostVisitedPattern(username []string, timestamp []int, website []string) []string {
	d := map[string][]pair{}
	for i, user := range username {
		ts := timestamp[i]
		site := website[i]
		d[user] = append(d[user], pair{ts, site})
	}
	cnt := map[string]int{}
	for _, sites := range d {
		m := len(sites)
		s := map[string]bool{}
		if m > 2 {
			sort.Slice(sites, func(i, j int) bool { return sites[i].ts < sites[j].ts })
			for i := 0; i < m-2; i++ {
				for j := i + 1; j < m-1; j++ {
					for k := j + 1; k < m; k++ {
						s[sites[i].site+","+sites[j].site+","+sites[k].site] = true
					}
				}
			}
		}
		for t := range s {
			cnt[t]++
		}
	}
	mx, t := 0, ""
	for p, v := range cnt {
		if mx < v || (mx == v && p < t) {
			mx = v
			t = p
		}
	}
	return strings.Split(t, ",")
}

type pair struct {
	ts   int
	site string
}