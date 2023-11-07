func closestRoom(rooms [][]int, queries [][]int) []int {
	n, k := len(rooms), len(queries)
	sort.Slice(rooms, func(i, j int) bool { return rooms[i][1] < rooms[j][1] })
	idx := make([]int, k)
	ans := make([]int, k)
	for i := range idx {
		idx[i] = i
		ans[i] = -1
	}
	sort.Slice(idx, func(i, j int) bool { return queries[idx[i]][1] < queries[idx[j]][1] })
	rbt := redblacktree.NewWithIntComparator()
	merge := func(rbt *redblacktree.Tree, key, value int) {
		if v, ok := rbt.Get(key); ok {
			nxt := v.(int) + value
			if nxt == 0 {
				rbt.Remove(key)
			} else {
				rbt.Put(key, nxt)
			}
		} else {
			rbt.Put(key, value)
		}
	}
	for _, room := range rooms {
		merge(rbt, room[0], 1)
	}
	i := 0

	for _, j := range idx {
		prefer, minSize := queries[j][0], queries[j][1]
		for i < n && rooms[i][1] < minSize {
			merge(rbt, rooms[i][0], -1)
			i++
		}
		if i == n {
			break
		}
		c, _ := rbt.Ceiling(prefer)
		f, _ := rbt.Floor(prefer)
		if c != nil {
			ans[j] = c.Key.(int)
		}
		if f != nil && (ans[j] == -1 || ans[j]-prefer >= prefer-f.Key.(int)) {
			ans[j] = f.Key.(int)
		}
	}
	return ans
}