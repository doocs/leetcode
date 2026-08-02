func countTasks(tasks []int, shifts []int) []int {
	m := len(tasks)
	n := len(shifts)

	s := make([]int64, m+1)
	for i := 0; i < m; i++ {
		s[i+1] = s[i] + int64(tasks[i])
	}

	ans := make([]int, n)

	i := 0
	var cur int64 = 0

	for j := 0; j < n; j++ {
		if int64(shifts[j]) < int64(tasks[i])-cur {
			cur += int64(shifts[j])
			ans[j] = m - i
		} else {
			t := int64(shifts[j]) - (int64(tasks[i]) - cur)

			if t >= s[m]-s[i+1] {
				i = 0
				cur = 0
			} else {
				l, r := i+1, m

				for l < r {
					mid := (l + r) >> 1
					if t < s[mid+1]-s[i+1] {
						r = mid
					} else {
						l = mid + 1
					}
				}

				cur = t - (s[l] - s[i+1])
				i = l
				ans[j] = m - i
			}
		}
	}

	return ans
}
