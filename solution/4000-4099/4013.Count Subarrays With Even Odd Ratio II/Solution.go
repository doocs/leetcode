type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{
		n: n,
		c: make([]int, n+1),
	}
}

func (bit *BinaryIndexedTree) update(x int, delta int) {
	for x <= bit.n {
		bit.c[x] += delta
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	sum := 0
	for x > 0 {
		sum += bit.c[x]
		x -= x & -x
	}
	return sum
}

func countRatioSubarrays(nums []int, a int, b int) int64 {
	n := len(nums)

	s := make([]int64, n+1)

	for i, x := range nums {
		if x%2 == 1 {
			s[i+1] = s[i] + int64(a)
		} else {
			s[i+1] = s[i] - int64(b)
		}
	}

	st := append([]int64{}, s...)
	sort.Slice(st, func(i, j int) bool {
		return st[i] < st[j]
	})

	uniq := make([]int64, 0, len(st))
	for _, x := range st {
		if len(uniq) == 0 || uniq[len(uniq)-1] != x {
			uniq = append(uniq, x)
		}
	}

	bit := NewBinaryIndexedTree(len(uniq) + 1)

	var ans int64

	for _, v := range s {
		x := sort.Search(len(uniq), func(i int) bool {
			return uniq[i] >= v
		}) + 1

		ans += int64(bit.query(x))
		bit.update(x, 1)
	}

	return ans
}
