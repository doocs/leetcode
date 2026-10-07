func oddEvenJumps(arr []int) (ans int) {
	n := len(arr)
	rbt := redblacktree.NewWithIntComparator()
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
	f := make([][2]bool, n)
	f[n-1][0], f[n-1][1] = true, true
	for i := n - 2; i >= 0; i-- {
		for k := 0; k < 2; k++ {
			j := g[i][k]
			if j != -1 {
				f[i][k] = f[j][k^1]
			}
		}
	}
	for i := 0; i < n; i++ {
		if f[i][1] {
			ans++
		}
	}
	return
}
