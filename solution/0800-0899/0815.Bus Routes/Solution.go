func numBusesToDestination(routes [][]int, source int, target int) int {
	if source == target {
		return 0
	}

	g := make(map[int][]int)
	for i, route := range routes {
		for _, stop := range route {
			g[stop] = append(g[stop], i)
		}
	}

	if g[source] == nil || g[target] == nil {
		return -1
	}

	q := list.New()
	q.PushBack([2]int{source, 0})
	visBus := make(map[int]bool)
	visStop := make(map[int]bool)
	visStop[source] = true

	for q.Len() > 0 {
		front := q.Front()
		q.Remove(front)
		stop, busCount := front.Value.([2]int)[0], front.Value.([2]int)[1]

		if stop == target {
			return busCount
		}

		for _, bus := range g[stop] {
			if !visBus[bus] {
				visBus[bus] = true
				for _, nextStop := range routes[bus] {
					if !visStop[nextStop] {
						visStop[nextStop] = true
						q.PushBack([2]int{nextStop, busCount + 1})
					}
				}
			}
		}
	}

	return -1
}
