func openLock(deadends []string, target string) int {
	dead := map[string]bool{}
	for _, s := range deadends {
		dead[s] = true
	}
	if dead["0000"] {
		return -1
	}
	if target == "0000" {
		return 0
	}
	q := []string{"0000"}
	visited := map[string]bool{"0000": true}
	step := 0
	for len(q) > 0 {
		step++
		size := len(q)
		for i := 0; i < size; i++ {
			cur := q[0]
			q = q[1:]
			for j := 0; j < 4; j++ {
				for k := -1; k <= 1; k += 2 {
					next := cur[:j] + string((cur[j]-'0'+byte(k)+10)%10+'0') + cur[j+1:]
					if next == target {
						return step
					}
					if !dead[next] && !visited[next] {
						q = append(q, next)
						visited[next] = true
					}
				}
			}
		}
	}
	return -1
}