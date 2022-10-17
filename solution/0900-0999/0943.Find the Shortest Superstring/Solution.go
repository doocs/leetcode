func shortestSuperstring(words []string) string {
	n := len(words)
	g := make([][]int, n)
	for i, a := range words {
		g[i] = make([]int, n)
		for j, b := range words {
			if i != j {
				for k := min(len(a), len(b)); k > 0; k-- {
					if a[len(a)-k:] == b[:k] {
						g[i][j] = k
						break
					}
				}
			}
		}
	}
	dp := make([][]int, 1<<n)
	p := make([][]int, 1<<n)
	for i := 0; i < 1<<n; i++ {
		dp[i] = make([]int, n)
		p[i] = make([]int, n)
		for j := 0; j < n; j++ {
			p[i][j] = -1
			if ((i >> j) & 1) == 1 {
				pi := i ^ (1 << j)
				for k := 0; k < n; k++ {
					if ((pi >> k) & 1) == 1 {
						v := dp[pi][k] + g[k][j]
						if v > dp[i][j] {
							dp[i][j] = v
							p[i][j] = k
						}
					}
				}
			}
		}
	}
	j := 0
	for i := 0; i < n; i++ {
		if dp[(1<<n)-1][i] > dp[(1<<n)-1][j] {
			j = i
		}
	}
	arr := []int{j}
	vis := make([]bool, n)
	vis[j] = true
	for i := (1 << n) - 1; p[i][j] != -1; {
		k := i
		i ^= (1 << j)
		j = p[k][j]
		arr = append(arr, j)
		vis[j] = true
	}
	for i := 0; i < n; i++ {
		if !vis[i] {
			arr = append(arr, i)
		}
	}
	ans := &strings.Builder{}
	ans.WriteString(words[arr[n-1]])
	for i := n - 2; i >= 0; i-- {
		k := g[arr[i+1]][arr[i]]
		ans.WriteString(words[arr[i]][k:])
	}
	return ans.String()
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}