func oddEvenJumps(arr []int) (ans int) {
	n := len(arr)
	rbt := redblacktree.NewWithIntComparator()
	f := make([][2]int, n)
	g := make([][2]int, n)
	for i := n - 1; i >= 0; i-- {
		if v, ok := rbt.Ceiling(arr[i]); ok {
			g[i][1] = v.Value.(int)
		} else {
			g[i][1] = -1
		}
		if v, ok := rbt.Floor(arr[i]); ok {
			g[i][0] = v.Value.(int)
		} else {
			g[i][0] = -1
		}
		rbt.Put(arr[i], i)
	}
	var dfs func(int, int) int
	dfs = func(i, k int) int {
		if i == n-1 {
			return 1
		}
		if g[i][k] == -1 {
			return 0
		}
		if f[i][k] != 0 {
			return f[i][k]
		}
		f[i][k] = dfs(g[i][k], k^1)
		return f[i][k]
	}
	for i := 0; i < n; i++ {
		if dfs(i, 1) == 1 {
			ans++
		}
	}
	return
}