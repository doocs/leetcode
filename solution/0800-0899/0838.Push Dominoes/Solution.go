func pushDominoes(dominoes string) string {
	n := len(dominoes)
	q := []int{}
	time := make([]int, n)
	for i := range time {
		time[i] = -1
	}
	force := make([][]byte, n)
	for i, c := range dominoes {
		if c != '.' {
			q = append(q, i)
			time[i] = 0
			force[i] = append(force[i], byte(c))
		}
	}

	ans := bytes.Repeat([]byte{'.'}, n)
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		if len(force[i]) > 1 {
			continue
		}
		f := force[i][0]
		ans[i] = f
		j := i - 1
		if f == 'R' {
			j = i + 1
		}
		if 0 <= j && j < n {
			t := time[i]
			if time[j] == -1 {
				q = append(q, j)
				time[j] = t + 1
				force[j] = append(force[j], f)
			} else if time[j] == t+1 {
				force[j] = append(force[j], f)
			}
		}
	}
	return string(ans)
}