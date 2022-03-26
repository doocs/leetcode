type segmentTree struct {
	str []byte
	mx  []int
	lmx []int
	rmx []int
}

func newSegmentTree(s string) *segmentTree {
	n := len(s)
	t := &segmentTree{
		str: []byte(s),
		mx:  make([]int, n<<2),
		lmx: make([]int, n<<2),
		rmx: make([]int, n<<2),
	}
	t.build(0, 0, n-1)
	return t
}

func (t *segmentTree) build(x, l, r int) {
	if l == r {
		t.lmx[x] = 1
		t.rmx[x] = 1
		t.mx[x] = 1
		return
	}
	m := int(uint(l+r) >> 1)
	t.build(x*2+1, l, m)
	t.build(x*2+2, m+1, r)
	t.pushup(x, l, m, r)
}

func (t *segmentTree) pushup(x, l, m, r int) {
	lch, rch := x*2+1, x*2+2
	t.lmx[x] = t.lmx[lch]
	t.rmx[x] = t.rmx[rch]
	t.mx[x] = max(t.mx[lch], t.mx[rch])
	// can be merged
	if t.str[m] == t.str[m+1] {
		if t.lmx[lch] == m-l+1 {
			t.lmx[x] += t.lmx[rch]
		}
		if t.rmx[rch] == r-m {
			t.rmx[x] += t.rmx[lch]
		}
		t.mx[x] = max(t.mx[x], t.rmx[lch]+t.lmx[rch])
	}
}

func (t *segmentTree) update(x, l, r, pos int, val byte) {
	if l == r {
		t.str[pos] = val
		return
	}
	m := int(uint(l+r) >> 1)
	if pos <= m {
		t.update(x*2+1, l, m, pos, val)
	} else {
		t.update(x*2+2, m+1, r, pos, val)
	}
	t.pushup(x, l, m, r)
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}

func longestRepeating(s string, queryCharacters string, queryIndices []int) []int {
	ans := make([]int, len(queryCharacters))
	t := newSegmentTree(s)
	n := len(s)
	for i, c := range queryCharacters {
		t.update(0, 0, n-1, queryIndices[i], byte(c))
		ans[i] = t.mx[0]
	}
	return ans
}
