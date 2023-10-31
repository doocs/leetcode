func findMaximumElegance(items [][]int, k int) int64 {
	sort.Slice(items, func(i, j int) bool { return items[i][0] > items[j][0] })
	tot := 0
	vis := map[int]bool{}
	dup := []int{}
	for _, item := range items[:k] {
		p, c := item[0], item[1]
		tot += p
		if vis[c] {
			dup = append(dup, p)
		} else {
			vis[c] = true
		}
	}
	ans := tot + len(vis)*len(vis)
	for _, item := range items[k:] {
		p, c := item[0], item[1]
		if vis[c] || len(dup) == 0 {
			continue
		}
		vis[c] = true
		tot += p - dup[len(dup)-1]
		dup = dup[:len(dup)-1]
		ans = max(ans, tot+len(vis)*len(vis))
	}
	return int64(ans)
}