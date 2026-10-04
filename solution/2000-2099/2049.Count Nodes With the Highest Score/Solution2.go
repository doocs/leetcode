func countHighestScoreNodes(parents []int) (ans int) {
	n := len(parents)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parents[i]] = append(g[parents[i]], i)
	}
	mx := 0
	sz := make([]int, n)
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa, state := cur[0], cur[1], cur[2]
		if state == 0 {
			stk = append(stk, [3]int{i, fa, 1})
			for _, j := range g[i] {
				if j != fa {
					stk = append(stk, [3]int{j, i, 0})
				}
			}
		} else {
			cnt, score := 1, 1
			for _, j := range g[i] {
				if j != fa {
					t := sz[j]
					cnt += t
					score *= t
				}
			}
			if n-cnt > 0 {
				score *= n - cnt
			}
			if mx < score {
				mx = score
				ans = 1
			} else if mx == score {
				ans++
			}
			sz[i] = cnt
		}
	}
	return
}
