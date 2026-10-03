func killProcess(pid []int, ppid []int, kill int) (ans []int) {
	g := map[int][]int{}
	for i, p := range ppid {
		g[p] = append(g[p], pid[i])
	}
	stk := []int{kill}
	for len(stk) > 0 {
		i := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		ans = append(ans, i)
		children := g[i]
		for k := len(children) - 1; k >= 0; k-- {
			stk = append(stk, children[k])
		}
	}
	return
}
