type MedianCost struct {
	l, r   *redblacktree.Tree
	n1, n2 int
	s1, s2 int64
}

func NewMedianCost() *MedianCost {
	return &MedianCost{
		l: redblacktree.NewWithIntComparator(),
		r: redblacktree.NewWithIntComparator(),
	}
}

func addTo(tree *redblacktree.Tree, x int) {
	v, ok := tree.Get(x)
	if ok {
		tree.Put(x, v.(int)+1)
	} else {
		tree.Put(x, 1)
	}
}

func removeFrom(tree *redblacktree.Tree, x int) {
	v, _ := tree.Get(x)
	if v.(int) == 1 {
		tree.Remove(x)
	} else {
		tree.Put(x, v.(int)-1)
	}
}

func (mc *MedianCost) adjust() {
	for mc.n1-mc.n2 > 1 {
		x := mc.l.Right().Key.(int)
		removeFrom(mc.l, x)
		mc.n1--
		mc.s1 -= int64(x)

		addTo(mc.r, x)
		mc.n2++
		mc.s2 += int64(x)
	}

	for mc.n2 > mc.n1 {
		x := mc.r.Left().Key.(int)
		removeFrom(mc.r, x)
		mc.n2--
		mc.s2 -= int64(x)

		addTo(mc.l, x)
		mc.n1++
		mc.s1 += int64(x)
	}
}

func (mc *MedianCost) add(x int) {
	addTo(mc.r, x)
	mc.n2++
	mc.s2 += int64(x)

	y := mc.r.Left().Key.(int)
	removeFrom(mc.r, y)
	mc.n2--
	mc.s2 -= int64(y)

	addTo(mc.l, y)
	mc.n1++
	mc.s1 += int64(y)

	mc.adjust()
}

func (mc *MedianCost) remove(x int) {
	if _, ok := mc.l.Get(x); ok {
		removeFrom(mc.l, x)
		mc.n1--
		mc.s1 -= int64(x)
	} else {
		removeFrom(mc.r, x)
		mc.n2--
		mc.s2 -= int64(x)
	}
	mc.adjust()
}

func (mc *MedianCost) query() int64 {
	mid := int64(mc.l.Right().Key.(int))
	return int64(mc.n1)*mid - mc.s1 + mc.s2 - int64(mc.n2)*mid
}

func minOperations(nums []int, x int, k int) int64 {
	n := len(nums)
	const INF int64 = math.MaxInt64 / 4

	f := make([][]int64, n+1)
	for i := range f {
		f[i] = make([]int64, k+1)
		for j := range f[i] {
			f[i][j] = INF
		}
	}
	f[0][0] = 0

	mc := NewMedianCost()
	for i := 1; i <= n; i++ {
		mc.add(nums[i-1])
		w := mc.query()

		for j := 0; j <= k; j++ {
			f[i][j] = f[i-1][j]
			if j > 0 && i >= x {
				f[i][j] = min(f[i][j], f[i-x][j-1]+w)
			}
		}

		if i >= x {
			mc.remove(nums[i-x])
		}
	}

	return f[n][k]
}
