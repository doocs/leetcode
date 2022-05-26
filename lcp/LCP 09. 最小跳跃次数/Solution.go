func minJump(jump []int) int {
	n := len(jump)
	vis := make([]bool, n+1)
	q := []int{0}
	vis[0] = true
	ans, mx := 0, 1
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			i := q[0]
			q = q[1:]
			j := i + jump[i]
			if j >= n {
				return ans + 1
			}
			if !vis[j] {
				vis[j] = true
				q = append(q, j)
			}
			for j = mx; j < i; j++ {
				if !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
			if mx < i+1 {
				mx = i + 1
			}
		}
		ans++
	}
	return -1
}