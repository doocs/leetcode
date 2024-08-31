func minJumps(arr []int) int {
	g := map[int][]int{}
	for i, x := range arr {
		g[x] = append(g[x], i)
	}
	n := len(arr)
	q := []int{0}
	vis := make([]bool, n)
	vis[0] = true
	for ans := 0; ; ans++ {
		for k := len(q); k > 0; k-- {
			i := q[0]
			q = q[1:]
			if i == n-1 {
				return ans
			}
			for _, j := range g[arr[i]] {
				if !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
			g[arr[i]] = nil
			for _, j := range []int{i - 1, i + 1} {
				if 0 <= j && j < n && !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
		}
	}
}
