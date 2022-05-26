func minJumps(arr []int) int {
	idx := map[int][]int{}
	for i, v := range arr {
		idx[v] = append(idx[v], i)
	}
	vis := map[int]bool{0: true}
	type pair struct{ idx, step int }
	q := []pair{{0, 0}}
	for len(q) > 0 {
		e := q[0]
		q = q[1:]
		i, step := e.idx, e.step
		if i == len(arr)-1 {
			return step
		}
		step++
		for _, j := range idx[arr[i]] {
			if !vis[j] {
				vis[j] = true
				q = append(q, pair{j, step})
			}
		}
		delete(idx, arr[i])
		if i+1 < len(arr) && !vis[i+1] {
			vis[i+1] = true
			q = append(q, pair{i + 1, step})
		}
		if i-1 >= 0 && !vis[i-1] {
			vis[i-1] = true
			q = append(q, pair{i - 1, step})
		}
	}
	return -1
}