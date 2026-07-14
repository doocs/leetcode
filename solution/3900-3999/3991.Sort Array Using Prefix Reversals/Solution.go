func sortArray(nums []int, pre []int) int {
	n := len(nums)

	target := 0
	for i := 0; i < n; i++ {
		target = target*8 + i
	}

	start := 0
	for _, x := range nums {
		start = start*8 + x
	}

	if start == target {
		return 0
	}

	vis := map[int]bool{start: true}

	type pair struct {
		state []int
		dist  int
	}
	q := []pair{{append([]int(nil), nums...), 0}}

	for len(q) > 0 {
		p := q[0]
		q = q[1:]

		state := p.state
		dist := p.dist
		nd := dist + 1

		for _, x := range pre {
			nxt := append([]int(nil), state...)

			for l, r := 0, x-1; l < r; l, r = l+1, r-1 {
				nxt[l], nxt[r] = nxt[r], nxt[l]
			}

			key := 0
			for _, v := range nxt {
				key = key*8 + v
			}

			if key == target {
				return nd
			}

			if !vis[key] {
				vis[key] = true
				q = append(q, pair{nxt, nd})
			}
		}
	}

	return -1
}
