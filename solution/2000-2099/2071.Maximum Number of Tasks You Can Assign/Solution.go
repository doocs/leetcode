func maxTaskAssign(tasks []int, workers []int, pills int, strength int) int {
	sort.Ints(tasks)
	sort.Ints(workers)
	n, m := len(tasks), len(workers)
	left, right := 0, min(m, n)
	check := func(x int) bool {
		p := pills
		q := []int{}
		i := 0
		for j := m - x; j < m; j++ {
			for i < x && tasks[i] <= workers[j]+strength {
				q = append(q, tasks[i])
				i++
			}
			if len(q) == 0 {
				return false
			}
			if q[0] <= workers[j] {
				q = q[1:]
			} else if p == 0 {
				return false
			} else {
				p--
				q = q[:len(q)-1]
			}
		}
		return true
	}
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}