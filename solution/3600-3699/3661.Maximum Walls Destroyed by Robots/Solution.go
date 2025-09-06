func maxWalls(robots []int, distance []int, walls []int) int {
	type pair struct {
		x, d int
	}
	n := len(robots)
	arr := make([]pair, n)
	for i := 0; i < n; i++ {
		arr[i] = pair{robots[i], distance[i]}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i].x < arr[j].x
	})
	sort.Ints(walls)

	f := make(map[[2]int]int)

	var dfs func(int, int) int
	dfs = func(i, j int) int {
		if i < 0 {
			return 0
		}
		key := [2]int{i, j}
		if v, ok := f[key]; ok {
			return v
		}

		left := arr[i].x - arr[i].d
		if i > 0 {
			left = max(left, arr[i-1].x+1)
		}
		l := sort.SearchInts(walls, left)
		r := sort.SearchInts(walls, arr[i].x+1)
		ans := dfs(i-1, 0) + (r - l)

		right := arr[i].x + arr[i].d
		if i+1 < n {
			if j == 0 {
				right = min(right, arr[i+1].x-arr[i+1].d-1)
			} else {
				right = min(right, arr[i+1].x-1)
			}
		}
		l = sort.SearchInts(walls, arr[i].x)
		r = sort.SearchInts(walls, right+1)
		ans = max(ans, dfs(i-1, 1)+(r-l))

		f[key] = ans
		return ans
	}

	return dfs(n-1, 1)
}
