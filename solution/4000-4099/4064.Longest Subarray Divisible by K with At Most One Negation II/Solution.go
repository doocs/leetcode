func longestSubarray(nums []int, k int) int {
	n := len(nums)
	p := make([]int, n+1)
	for i := 0; i < n; i++ {
		p[i+1] = (p[i] + nums[i]) % k
		if p[i+1] < 0 {
			p[i+1] += k
		}
	}

	first := make([]int, k)
	for i := range first {
		first[i] = -1
	}
	for i := 0; i <= n; i++ {
		if first[p[i]] == -1 {
			first[p[i]] = i
		}
	}

	order := make([]int, 0, k)
	for q := 0; q < k; q++ {
		if first[q] != -1 {
			order = append(order, q)
		}
	}

	slices.SortFunc(order, func(a, b int) int {
		return first[a] - first[b]
	})

	pos := make([]int, k)
	best := make([]int, k)
	for q := 0; q < k; q++ {
		if first[q] == -1 {
			best[q] = int(^uint(0) >> 1)
		} else {
			best[q] = first[q]
		}
	}

	ans := 0
	for i, x := range nums {
		a := x % k
		if a < 0 {
			a += k
		}

		for pos[a] < len(order) && first[order[pos[a]]] <= i {
			q := order[pos[a]]
			pos[a]++
			t := (q + 2*a) % k
			best[t] = min(best[t], first[q])
		}

		s := p[i+1]
		if best[s] != int(^uint(0)>>1) {
			ans = max(ans, i+1-best[s])
		}
	}
	return ans
}
