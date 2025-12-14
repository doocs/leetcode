func minMoves(balance []int) int64 {
	var sum int64
	for _, b := range balance {
		sum += int64(b)
	}
	if sum < 0 {
		return -1
	}

	n := len(balance)
	mn := balance[0]
	idx := 0
	for i := 1; i < n; i++ {
		if balance[i] < mn {
			mn = balance[i]
			idx = i
		}
	}

	if mn >= 0 {
		return 0
	}

	need := -mn
	var ans int64

	for j := 1; j < n; j++ {
		a := balance[(idx-j+n)%n]
		b := balance[(idx+j)%n]

		c1 := min(a, need)
		need -= c1
		ans += int64(c1) * int64(j)

		c2 := min(b, need)
		need -= c2
		ans += int64(c2) * int64(j)
	}

	return ans
}
