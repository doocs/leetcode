func minCost(nums []int, cost []int) int64 {
	n := len(nums)
	type pair struct{ a, b int }
	arr := make([]pair, n)
	for i, a := range nums {
		b := cost[i]
		arr[i] = pair{a, b}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i].a < arr[j].a })
	f := make([]int, n+1)
	g := make([]int, n+1)
	for i := 1; i <= n; i++ {
		a, b := arr[i-1].a, arr[i-1].b
		f[i] = f[i-1] + a*b
		g[i] = g[i-1] + b
	}
	var ans int64 = 1e18
	for i := 1; i <= n; i++ {
		a := arr[i-1].a
		l := a*g[i-1] - f[i-1]
		r := f[n] - f[i] - a*(g[n]-g[i])
		ans = min(ans, int64(l+r))
	}
	return ans
}

func min(a, b int64) int64 {
	if a < b {
		return a
	}
	return b
}