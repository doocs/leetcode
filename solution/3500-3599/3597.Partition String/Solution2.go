type Hashing struct {
	p, h []int64
	mod  int64
}

func NewHashing(s string, base, mod int64) *Hashing {
	n := len(s)
	p := make([]int64, n+1)
	h := make([]int64, n+1)
	p[0] = 1
	for i := 1; i <= n; i++ {
		p[i] = p[i-1] * base % mod
		h[i] = (h[i-1]*base + int64(s[i-1])) % mod
	}
	return &Hashing{p, h, mod}
}

func (hs *Hashing) Query(l, r int) int64 {
	return (hs.h[r] - hs.h[l-1]*hs.p[r-l+1]%hs.mod + hs.mod) % hs.mod
}

func partitionString(s string) (ans []string) {
	n := len(s)
	hashing := NewHashing(s, 13331, 998244353)
	vis := make(map[int64]bool)
	l := 1
	for r := 1; r <= n; r++ {
		x := hashing.Query(l, r)
		if !vis[x] {
			vis[x] = true
			ans = append(ans, s[l-1:r])
			l = r + 1
		}
	}
	return
}