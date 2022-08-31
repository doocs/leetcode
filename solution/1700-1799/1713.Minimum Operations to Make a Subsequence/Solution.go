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

func (this *BinaryIndexedTree) update(x, val int) {
	for x <= this.n {
		if this.c[x] < val {
			this.c[x] = val
		}
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		if s < this.c[x] {
			s = this.c[x]
		}
		x -= this.lowbit(x)
	}
	return s
}

func minOperations(target []int, arr []int) int {
	d := map[int]int{}
	for i, v := range target {
		d[v] = i
	}
	nums := []int{}
	for _, v := range arr {
		if i, ok := d[v]; ok {
			nums = append(nums, i)
		}
	}
	return len(target) - lengthOfLIS(nums)
}

func lengthOfLIS(nums []int) int {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	t := []int{}
	for v := range s {
		t = append(t, v)
	}
	sort.Ints(t)
	d := map[int]int{}
	for i, v := range t {
		d[v] = i + 1
	}
	tree := newBinaryIndexedTree(len(d))
	ans := 0
	for _, v := range nums {
		x := d[v]
		t := tree.query(x-1) + 1
		ans = max(ans, t)
		tree.update(x, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}