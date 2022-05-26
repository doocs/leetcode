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

type NumMatrix struct {
	trees []*BinaryIndexedTree
}

func Constructor(matrix [][]int) NumMatrix {
	n := len(matrix[0])
	var trees []*BinaryIndexedTree
	for _, row := range matrix {
		tree := newBinaryIndexedTree(n)
		for j, v := range row {
			tree.update(j+1, v)
		}
		trees = append(trees, tree)
	}
	return NumMatrix{trees}
}

func (this *NumMatrix) Update(row int, col int, val int) {
	tree := this.trees[row]
	prev := tree.query(col+1) - tree.query(col)
	tree.update(col+1, val-prev)
}

func (this *NumMatrix) SumRegion(row1 int, col1 int, row2 int, col2 int) int {
	s := 0
	for i := row1; i <= row2; i++ {
		tree := this.trees[i]
		s += tree.query(col2+1) - tree.query(col1)
	}
	return s
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * obj := Constructor(matrix);
 * obj.Update(row,col,val);
 * param_2 := obj.SumRegion(row1,col1,row2,col2);
 */