type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) BinaryIndexedTree {
	return BinaryIndexedTree{n: n, c: make([]int, n+1)}
}

func (bit *BinaryIndexedTree) Update(x, v int) {
	for ; x <= bit.n; x += x & -x {
		if v > bit.c[x] {
			bit.c[x] = v
		}
	}
}

func (bit *BinaryIndexedTree) Query(x int) int {
	ans := 0
	for ; x > 0; x -= x & -x {
		if bit.c[x] > ans {
			ans = bit.c[x]
		}
	}
	return ans
}

func minOperations(target []int, arr []int) int {
	m := len(target)
	d := make(map[int]int)
	for i, x := range target {
		d[x] = i + 1
	}
	var nums []int
	for _, x := range arr {
		if pos, exists := d[x]; exists {
			nums = append(nums, pos)
		}
	}
	tree := NewBinaryIndexedTree(m)
	ans := 0
	for _, x := range nums {
		v := tree.Query(x-1) + 1
		if v > ans {
			ans = v
		}
		tree.Update(x, v)
	}
	return m - ans
}