func timeTaken(arrival []int, state []int) []int {
	n := len(arrival)
	q := [2][]int{}
	t, i, st := 0, 0, 1
	ans := make([]int, n)

	for i < n || len(q[0]) > 0 || len(q[1]) > 0 {
		for i < n && arrival[i] <= t {
			q[state[i]] = append(q[state[i]], i)
			i++
		}

		if len(q[0]) > 0 && len(q[1]) > 0 {
			ans[q[st][0]] = t
			q[st] = q[st][1:]
		} else if len(q[0]) > 0 || len(q[1]) > 0 {
			if len(q[0]) == 0 {
				st = 1
			} else {
				st = 0
			}
			ans[q[st][0]] = t
			q[st] = q[st][1:]
		} else {
			st = 1
		}

		t++
	}

	return ans
}
