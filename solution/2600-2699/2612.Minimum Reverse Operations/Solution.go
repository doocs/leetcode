func minReverseOperations(n int, p int, banned []int, k int) []int {
	ans := make([]int, n)
	ts := [2]*redblacktree.Tree{redblacktree.NewWithIntComparator(), redblacktree.NewWithIntComparator()}
	for i := 0; i < n; i++ {
		ts[i%2].Put(i, struct{}{})
		ans[i] = -1
	}
	ans[p] = 0
	ts[p%2].Remove(p)
	for _, i := range banned {
		ts[i%2].Remove(i)
	}
	ts[0].Put(n, struct{}{})
	ts[1].Put(n, struct{}{})
	q := []int{p}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		mi := max(i-k+1, k-i-1)
		mx := min(i+k-1, n*2-k-i-1)
		s := ts[mi%2]
		for x, _ := s.Ceiling(mi); x.Key.(int) <= mx; x, _ = s.Ceiling(mi) {
			j := x.Key.(int)
			s.Remove(j)
			ans[j] = ans[i] + 1
			q = append(q, j)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}