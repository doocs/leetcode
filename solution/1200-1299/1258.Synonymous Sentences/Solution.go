type unionFind struct {
	p, size []int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) {
	pa, pb := uf.find(a), uf.find(b)
	if pa != pb {
		if uf.size[pa] > uf.size[pb] {
			uf.p[pb] = pa
			uf.size[pa] += uf.size[pb]
		} else {
			uf.p[pa] = pb
			uf.size[pb] += uf.size[pa]
		}
	}
}

func generateSentences(synonyms [][]string, text string) (ans []string) {
	ss := map[string]bool{}
	for _, pairs := range synonyms {
		ss[pairs[0]] = true
		ss[pairs[1]] = true
	}
	words := []string{}
	for w := range ss {
		words = append(words, w)
	}
	d := map[string]int{}
	for i, w := range words {
		d[w] = i
	}
	uf := newUnionFind(len(words))
	for _, pairs := range synonyms {
		uf.union(d[pairs[0]], d[pairs[1]])
	}
	g := make([][]int, len(words))
	for i := range g {
		g[uf.find(i)] = append(g[uf.find(i)], i)
	}
	for i := range g {
		sort.Slice(g[i], func(a, b int) bool { return words[g[i][a]] < words[g[i][b]] })
	}
	t := []string{}
	sentences := strings.Split(text, " ")
	var dfs func(int)
	dfs = func(i int) {
		if i >= len(sentences) {
			ans = append(ans, strings.Join(t, " "))
			return
		}
		if _, ok := ss[sentences[i]]; !ok {
			t = append(t, sentences[i])
			dfs(i + 1)
			t = t[:len(t)-1]
			return
		} else {
			for _, j := range g[uf.find(d[sentences[i]])] {
				t = append(t, words[j])
				dfs(i + 1)
				t = t[:len(t)-1]
			}
		}
	}
	dfs(0)
	return
}