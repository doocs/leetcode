func circularGameLosers(n int, k int) (ans []int) {
	vis := make([]bool, n)
	for i, p := 0, 1; !vis[i]; p++ {
		vis[i] = true
		i = (i + p*k) % n
	}
	for i, x := range vis {
		if !x {
			ans = append(ans, i+1)
		}
	}
	return
}