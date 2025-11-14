func maxFreq(s string, maxLetters int, minSize int, maxSize int) int {
	n := len(s)
	hashing := NewHashing(s)
	freq := make([]int, 256)
	k := 0
	ans := 0
	cnt := make(map[uint64]int)

	for i := 1; i <= n; i++ {
		c := s[i-1]
		freq[c]++
		if freq[c] == 1 {
			k++
		}

		if i >= minSize {
			if k <= maxLetters {
				x := hashing.Query(i-minSize+1, i)
				cnt[x]++
				if cnt[x] > ans {
					ans = cnt[x]
				}
			}
			j := i - minSize
			c2 := s[j]
			freq[c2]--
			if freq[c2] == 0 {
				k--
			}
		}
	}

	return ans
}

type Hashing struct {
	p, h []uint64
	mod  uint64
	base uint64
}

func NewHashing(word string) *Hashing {
	return NewHashingWithBase(word, 13331, 998244353)
}

func NewHashingWithBase(word string, base uint64, mod uint64) *Hashing {
	n := len(word)
	p := make([]uint64, n+1)
	h := make([]uint64, n+1)
	p[0] = 1
	for i := 1; i <= n; i++ {
		p[i] = p[i-1] * base % mod
		h[i] = (h[i-1]*base + uint64(word[i-1])) % mod
	}
	return &Hashing{p, h, mod, base}
}

func (hs *Hashing) Query(l, r int) uint64 {
	return (hs.h[r] + hs.mod - hs.h[l-1]*hs.p[r-l+1]%hs.mod) % hs.mod
}
