func getCoprimes(nums []int, edges [][]int) []int {
	n := len(nums)
	g := make([][]int, n)
	f := [51][]int{}
	type pair struct{ first, second int }
	stks := [51][]pair{}
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	for i := 1; i < 51; i++ {
		for j := 1; j < 51; j++ {
			if gcd(i, j) == 1 {
				f[i] = append(f[i], j)
			}
		}
	}
	ans := make([]int, n)
	var dfs func(i, fa, depth int)
	dfs = func(i, fa, depth int) {
		t, k := -1, -1
		for _, v := range f[nums[i]] {
			stk := stks[v]
			if len(stk) > 0 && stk[len(stk)-1].second > k {
				t, k = stk[len(stk)-1].first, stk[len(stk)-1].second
			}
		}
		ans[i] = t
		for _, j := range g[i] {
			if j != fa {
				stks[nums[i]] = append(stks[nums[i]], pair{i, depth})
				dfs(j, i, depth+1)
				stks[nums[i]] = stks[nums[i]][:len(stks[nums[i]])-1]
			}
		}
	}
	dfs(0, -1, 0)
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}