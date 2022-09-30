func assignBikes(workers [][]int, bikes [][]int) []int {
	n, m := len(workers), len(bikes)
	type tuple struct{ d, i, j int }
	arr := make([]tuple, n*m)
	for i, k := 0, 0; i < n; i++ {
		for j := 0; j < m; j++ {
			d := abs(workers[i][0]-bikes[j][0]) + abs(workers[i][1]-bikes[j][1])
			arr[k] = tuple{d, i, j}
			k++
		}
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i].d != arr[j].d {
			return arr[i].d < arr[j].d
		}
		if arr[i].i != arr[j].i {
			return arr[i].i < arr[j].i
		}
		return arr[i].j < arr[j].j
	})
	vis1, vis2 := make([]bool, n), make([]bool, m)
	ans := make([]int, n)
	for _, e := range arr {
		i, j := e.i, e.j
		if !vis1[i] && !vis2[j] {
			vis1[i], vis2[j] = true, true
			ans[i] = j
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}