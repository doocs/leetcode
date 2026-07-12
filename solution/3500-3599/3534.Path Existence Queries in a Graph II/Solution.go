func pathExistenceQueries(n int, nums []int, maxDiff int, queries [][]int) []int {
	pairs := make([][2]int, n)
	for i, x := range nums {
		pairs[i] = [2]int{x, i}
	}
	sort.Slice(pairs, func(i, j int) bool {
		return pairs[i][0] < pairs[j][0]
	})

	m := 20
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, m)
	}

	r := n - 1
	for l := n - 1; l >= 0; l-- {
		for pairs[r][0]-pairs[l][0] > maxDiff {
			r--
		}
		i, j := pairs[l][1], pairs[r][1]
		f[i][0] = j
		for k := 1; k < m; k++ {
			f[i][k] = f[f[i][k-1]][k-1]
		}
	}

	ans := make([]int, 0, len(queries))
	for _, q := range queries {
		i, j := q[0], q[1]
		if nums[i] > nums[j] {
			i, j = j, i
		}
		if i == j {
			ans = append(ans, 0)
			continue
		}
		if nums[i] == nums[j] {
			ans = append(ans, 1)
			continue
		}
		d := 0
		for k := m - 1; k >= 0; k-- {
			if nums[f[i][k]] < nums[j] {
				d |= 1 << k
				i = f[i][k]
			}
		}
		if nums[f[i][0]] < nums[j] {
			ans = append(ans, -1)
		} else {
			ans = append(ans, d+1)
		}
	}
	return ans
}
