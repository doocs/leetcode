type Hashing struct {
	p   []int64
	h   []int64
	mod int64
}

func NewHashing(word string, base, mod int64) *Hashing {
	n := len(word)
	p := make([]int64, n+1)
	h := make([]int64, n+1)
	p[0] = 1
	for i := 1; i <= n; i++ {
		p[i] = p[i-1] * base % mod
		h[i] = (h[i-1]*base + int64(word[i-1])) % mod
	}
	return &Hashing{p, h, mod}
}

func (hs *Hashing) query(l, r int) int64 {
	return (hs.h[r] - hs.h[l-1]*hs.p[r-l+1]%hs.mod + hs.mod) % hs.mod
}

func findAnswer(parent []int, s string) (ans []bool) {
	n := len(s)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	dfsStr := []byte{}
	pos := make([][2]int, n)
	var dfs func(int)
	dfs = func(i int) {
		l := len(dfsStr) + 1
		for _, j := range g[i] {
			dfs(j)
		}
		dfsStr = append(dfsStr, s[i])
		r := len(dfsStr)
		pos[i] = [2]int{l, r}
	}

	const base = 13331
	const mod = 998244353
	dfs(0)
	h1 := NewHashing(string(dfsStr), base, mod)
	for i, j := 0, len(dfsStr)-1; i < j; i, j = i+1, j-1 {
		dfsStr[i], dfsStr[j] = dfsStr[j], dfsStr[i]
	}
	h2 := NewHashing(string(dfsStr), base, mod)
	for i := 0; i < n; i++ {
		l, r := pos[i][0], pos[i][1]
		k := r - l + 1
		v1 := h1.query(l, l+k/2-1)
		v2 := h2.query(n-r+1, n-r+1+k/2-1)
		ans = append(ans, v1 == v2)
	}
	return
}
