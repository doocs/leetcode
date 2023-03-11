func trulyMostPopular(names []string, synonyms []string) (ans []string) {
	g := map[string][]string{}
	for _, s := range synonyms {
		i := strings.Index(s, ",")
		a, b := s[1:i], s[i+1:len(s)-1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	s := map[string]struct{}{}
	cnt := map[string]int{}
	for _, e := range names {
		i := strings.Index(e, "(")
		name, num := e[:i], e[i+1:len(e)-1]
		x, _ := strconv.Atoi(num)
		cnt[name] += x
		s[name] = struct{}{}
	}
	freq := 0
	vis := map[string]struct{}{}
	var dfs func(string) string
	dfs = func(a string) string {
		vis[a] = struct{}{}
		freq += cnt[a]
		res := a
		for _, b := range g[a] {
			if _, ok := vis[b]; !ok {
				t := dfs(b)
				if t < res {
					res = t
				}
			}
		}
		return res
	}
	for name := range s {
		if _, ok := vis[name]; !ok {
			freq = 0
			root := dfs(name)
			ans = append(ans, root+"("+strconv.Itoa(freq)+")")
		}
	}
	return
}