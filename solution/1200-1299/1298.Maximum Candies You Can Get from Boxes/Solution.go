func maxCandies(status []int, candies []int, keys [][]int, containedBoxes [][]int, initialBoxes []int) int {
	ans := 0
	n := len(status)
	has := make([]bool, n)
	took := make([]bool, n)
	var q []int
	for _, i := range initialBoxes {
		has[i] = true
		if status[i] == 1 {
			ans += candies[i]
			took[i] = true
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		for _, k := range keys[i] {
			status[k] = 1
			if has[k] && !took[k] {
				ans += candies[k]
				took[k] = true
				q = append(q, k)
			}
		}
		for _, j := range containedBoxes[i] {
			has[j] = true
			if status[j] == 1 && !took[j] {
				ans += candies[j]
				took[j] = true
				q = append(q, j)
			}
		}
	}
	return ans
}