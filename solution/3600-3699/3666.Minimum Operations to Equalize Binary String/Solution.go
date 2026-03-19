func minOperations(s string, k int) int {
	n := len(s)

	ts := [2]*redblacktree.Tree{
		redblacktree.NewWithIntComparator(),
		redblacktree.NewWithIntComparator(),
	}

	for i := 0; i <= n; i++ {
		ts[i%2].Put(i, struct{}{})
	}

	cnt0 := strings.Count(s, "0")
	ts[cnt0%2].Remove(cnt0)

	q := []int{cnt0}
	ans := 0

	for len(q) > 0 {
		nq := []int{}

		for _, cur := range q {
			if cur == 0 {
				return ans
			}

			l := cur + k - 2*min(cur, k)
			r := cur + k - 2*max(k-n+cur, 0)
			t := ts[l%2]

			node, found := t.Ceiling(l)
			for found && node.Key.(int) <= r {
				val := node.Key.(int)
				nq = append(nq, val)
				t.Remove(val)
				node, found = t.Ceiling(l)
			}
		}

		q = nq
		ans++
	}

	return -1
}
