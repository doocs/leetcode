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
	type frame struct{ i, fa, depth, k int }
	stk := []frame{{0, -1, 0, 0}}
	for len(stk) > 0 {
		cur := &stk[len(stk)-1]
		i, fa, depth, k := cur.i, cur.fa, cur.depth, cur.k
		if k == 0 {
			t, mx := -1, -1
			for _, v := range f[nums[i]] {
				s := stks[v]
				if len(s) > 0 && s[len(s)-1].second > mx {
					t, mx = s[len(s)-1].first, s[len(s)-1].second
				}
			}
			ans[i] = t
		} else {
			jprev := g[i][k-1]
			if jprev != fa {
				stks[nums[i]] = stks[nums[i]][:len(stks[nums[i]])-1]
			}
		}
		for k < len(g[i]) && g[i][k] == fa {
			k++
		}
		if k == len(g[i]) {
			stk = stk[:len(stk)-1]
			continue
		}
		j := g[i][k]
		cur.k = k + 1
		stks[nums[i]] = append(stks[nums[i]], pair{i, depth})
		stk = append(stk, frame{j, i, depth + 1, 0})
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
