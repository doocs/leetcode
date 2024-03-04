type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n: n, c: make([]int, n+1)}
}

func (bit *BinaryIndexedTree) update(x, delta int) {
	for ; x <= bit.n; x += x & -x {
		bit.c[x] += delta
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	s := 0
	for ; x > 0; x -= x & -x {
		s += bit.c[x]
	}
	return s
}

func resultArray(nums []int) []int {
	st := make([]int, len(nums))
	copy(st, nums)
	sort.Ints(st)
	n := len(st)
	tree1 := NewBinaryIndexedTree(n + 1)
	tree2 := NewBinaryIndexedTree(n + 1)
	tree1.update(sort.SearchInts(st, nums[0])+1, 1)
	tree2.update(sort.SearchInts(st, nums[1])+1, 1)
	arr1 := []int{nums[0]}
	arr2 := []int{nums[1]}
	for _, x := range nums[2:] {
		i := sort.SearchInts(st, x) + 1
		a := len(arr1) - tree1.query(i)
		b := len(arr2) - tree2.query(i)
		if a > b {
			arr1 = append(arr1, x)
			tree1.update(i, 1)
		} else if a < b {
			arr2 = append(arr2, x)
			tree2.update(i, 1)
		} else if len(arr1) <= len(arr2) {
			arr1 = append(arr1, x)
			tree1.update(i, 1)
		} else {
			arr2 = append(arr2, x)
			tree2.update(i, 1)
		}
	}
	arr1 = append(arr1, arr2...)
	return arr1
}