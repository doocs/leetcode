func aggregateTimeSeries(series1 [][]int, series2 [][]int) [][]int {
	m, n := len(series1), len(series2)
	i, j := 0, 0
	ans := make([][]int, 0)

	for i < m && j < n {
		t1, v1 := series1[i][0], series1[i][1]
		t2, v2 := series2[j][0], series2[j][1]

		if t1 == t2 {
			ans = append(ans, []int{t1, v1 + v2})
			i++
			j++
		} else if t1 < t2 {
			ans = append(ans, []int{t1, v1 + v2})
			i++
		} else {
			ans = append(ans, []int{t2, v1 + v2})
			j++
		}
	}

	for i < m {
		ans = append(ans, series1[i])
		i++
	}

	for j < n {
		ans = append(ans, series2[j])
		j++
	}

	return ans
}
