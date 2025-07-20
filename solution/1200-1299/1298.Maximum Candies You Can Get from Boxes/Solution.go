func maxCandies(status []int, candies []int, keys [][]int, containedBoxes [][]int, initialBoxes []int) (ans int) {
	q := []int{}
	has := make(map[int]bool)
	took := make(map[int]bool)
	for _, box := range initialBoxes {
		has[box] = true
		if status[box] == 1 {
			q = append(q, box)
			took[box] = true
			ans += candies[box]
		}
	}
	for len(q) > 0 {
		box := q[0]
		q = q[1:]
		for _, k := range keys[box] {
			if status[k] == 0 {
				status[k] = 1
				if has[k] && !took[k] {
					q = append(q, k)
					took[k] = true
					ans += candies[k]
				}
			}
		}
		for _, b := range containedBoxes[box] {
			has[b] = true
			if status[b] == 1 && !took[b] {
				q = append(q, b)
				took[b] = true
				ans += candies[b]
			}
		}
	}
	return
}
