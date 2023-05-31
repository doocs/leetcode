func sortItems(n int, m int, group []int, beforeItems [][]int) []int {
	idx := m
	groupItems := make([][]int, n+m)
	itemDegree := make([]int, n)
	groupDegree := make([]int, n+m)
	itemGraph := make([][]int, n)
	groupGraph := make([][]int, n+m)
	for i, g := range group {
		if g == -1 {
			group[i] = idx
			idx++
		}
		groupItems[group[i]] = append(groupItems[group[i]], i)
	}
	for i, gi := range group {
		for _, j := range beforeItems[i] {
			gj := group[j]
			if gi == gj {
				itemDegree[i]++
				itemGraph[j] = append(itemGraph[j], i)
			} else {
				groupDegree[gi]++
				groupGraph[gj] = append(groupGraph[gj], gi)
			}
		}
	}
	items := make([]int, n+m)
	for i := range items {
		items[i] = i
	}
	topoSort := func(degree []int, graph [][]int, items []int) []int {
		q := []int{}
		for _, i := range items {
			if degree[i] == 0 {
				q = append(q, i)
			}
		}
		ans := []int{}
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			ans = append(ans, i)
			for _, j := range graph[i] {
				degree[j]--
				if degree[j] == 0 {
					q = append(q, j)
				}
			}
		}
		return ans
	}
	groupOrder := topoSort(groupDegree, groupGraph, items)
	if len(groupOrder) != len(items) {
		return nil
	}
	ans := []int{}
	for _, gi := range groupOrder {
		items = groupItems[gi]
		itemOrder := topoSort(itemDegree, itemGraph, items)
		if len(items) != len(itemOrder) {
			return nil
		}
		ans = append(ans, itemOrder...)
	}
	return ans
}