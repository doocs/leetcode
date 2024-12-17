type Hashing struct {
	p   []int64
	h   []int64
	mod int64
}

func NewHashing(word string, base int64, mod int64) *Hashing {
	n := len(word)
	p := make([]int64, n+1)
	h := make([]int64, n+1)
	p[0] = 1
	for i := 1; i <= n; i++ {
		p[i] = (p[i-1] * base) % mod
		h[i] = (h[i-1]*base + int64(word[i-1])) % mod
	}
	return &Hashing{p, h, mod}
}

func (hashing *Hashing) Query(l, r int) int64 {
	return (hashing.h[r] - hashing.h[l-1]*hashing.p[r-l+1]%hashing.mod + hashing.mod) % hashing.mod
}

func minValidStrings(words []string, target string) (ans int) {
	base, mod := int64(13331), int64(998244353)
	hashing := NewHashing(target, base, mod)

	m, n := 0, len(target)
	for _, w := range words {
		m = max(m, len(w))
	}

	s := make([]map[int64]bool, m+1)

	f := func(i int) int {
		l, r := 0, int(math.Min(float64(n-i), float64(m)))
		for l < r {
			mid := (l + r + 1) >> 1
			sub := hashing.Query(i+1, i+mid)
			if s[mid][sub] {
				l = mid
			} else {
				r = mid - 1
			}
		}
		return l
	}

	for _, w := range words {
		h := int64(0)
		for j := 0; j < len(w); j++ {
			h = (h*base + int64(w[j])) % mod
			if s[j+1] == nil {
				s[j+1] = make(map[int64]bool)
			}
			s[j+1][h] = true
		}
	}

	var last, mx int

	for i := 0; i < n; i++ {
		dist := f(i)
		mx = max(mx, i+dist)
		if i == last {
			if i == mx {
				return -1
			}
			last = mx
			ans++
		}
	}
	return ans
}
