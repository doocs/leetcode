type BinaryIndexedTree struct {
	n int
	c []int
	d []int
}

func newBinaryIndexedTree(n int) BinaryIndexedTree {
	return BinaryIndexedTree{
		n: n,
		c: make([]int, n+1),
		d: make([]int, n+1),
	}
}

func (bit *BinaryIndexedTree) update(x, v, cnt int) {
	for x <= bit.n {
		if bit.c[x] < v {
			bit.c[x] = v
			bit.d[x] = cnt
		} else if bit.c[x] == v {
			bit.d[x] += cnt
		}
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) (int, int) {
	v, cnt := 0, 0
	for x > 0 {
		if bit.c[x] > v {
			v = bit.c[x]
			cnt = bit.d[x]
		} else if bit.c[x] == v {
			cnt += bit.d[x]
		}
		x -= x & -x
	}
	return v, cnt
}

func findNumberOfLIS(nums []int) int {
	arr := append([]int(nil), nums...)
	sort.Ints(arr)
	k := 0
	for _, x := range arr {
		if k == 0 || arr[k-1] != x {
			arr[k] = x
			k++
		}
	}
	arr = arr[:k]
	m := len(arr)
	tree := newBinaryIndexedTree(m)
	for _, x := range nums {
		i := sort.SearchInts(arr, x) + 1
		v, cnt := tree.query(i - 1)
		tree.update(i, v+1, max(cnt, 1))
	}
	_, ans := tree.query(m)
	return ans
}
