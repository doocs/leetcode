type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	return x & -x
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= this.lowbit(x)
	}
	return s
}

func minInteger(num string, k int) string {
	pos := make([][]int, 10)
	for i, c := range num {
		pos[c-'0'] = append(pos[c-'0'], i+1)
	}
	n := len(num)
	tree := newBinaryIndexedTree(n)
	var ans strings.Builder
	for i := 1; i <= n; i++ {
		for v := 0; v < 10; v++ {
			if len(pos[v]) > 0 {
				j := pos[v][0]
				dist := tree.query(n) - tree.query(j) + j - i
				if dist <= k {
					k -= dist
					pos[v] = pos[v][1:]
					ans.WriteByte(byte(v + '0'))
					tree.update(j, 1)
					break
				}
			}
		}
	}
	return ans.String()
}