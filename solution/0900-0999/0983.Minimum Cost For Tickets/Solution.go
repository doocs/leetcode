func mincostTickets(days []int, costs []int) int {
	t := []int{1, 7, 30}
	n := len(days)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(i int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		res := 0x3f3f3f3f
		for k, c := range costs {
			j := lowerBound(days, days[i]+t[k])
			res = min(res, c+dfs(j))
		}
		f[i] = res
		return res
	}
	return dfs(0)
}

func lowerBound(arr []int, x int) int {
	left, right := 0, len(arr)
	for left < right {
		mid := (left + right) >> 1
		if arr[mid] >= x {
			right = mid
		} else {
			left = mid + 1
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