type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) BinaryIndexedTree {
	c := make([]int, n+1)
	for i := range c {
		c[i] = -1
	}
	return BinaryIndexedTree{n: n, c: c}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for x <= bit.n {
		bit.c[x] = max(bit.c[x], v)
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	mx := -1
	for x > 0 {
		mx = max(mx, bit.c[x])
		x -= x & -x
	}
	return mx
}

func maximumSumQueries(nums1 []int, nums2 []int, queries [][]int) []int {
	n, m := len(nums1), len(queries)
	nums := make([][2]int, n)
	for i := range nums {
		nums[i] = [2]int{nums1[i], nums2[i]}
	}
	sort.Slice(nums, func(i, j int) bool { return nums[j][0] < nums[i][0] })
	sort.Ints(nums2)
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return queries[idx[j]][0] < queries[idx[i]][0] })
	tree := NewBinaryIndexedTree(n)
	ans := make([]int, m)
	j := 0
	for _, i := range idx {
		x, y := queries[i][0], queries[i][1]
		for ; j < n && nums[j][0] >= x; j++ {
			k := n - sort.SearchInts(nums2, nums[j][1])
			tree.update(k, nums[j][0]+nums[j][1])
		}
		k := n - sort.SearchInts(nums2, y)
		ans[i] = tree.query(k)
	}
	return ans
}