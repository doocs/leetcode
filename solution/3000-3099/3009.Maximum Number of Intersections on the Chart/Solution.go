func maxIntersectionCount(y []int) (ans int) {
	n := len(y)
	line := map[int]int{}
	for i := 1; i < n; i++ {
		start := 2 * y[i-1]
		end := 2 * y[i]
		if i != n-1 {
			if y[i] > y[i-1] {
				end--
			} else {
				end++
			}
		}
		a, b := min(start, end), max(start, end)
		line[a]++
		line[b+1]--
	}
	keys := make([]int, 0, len(line))
	for k := range line {
		keys = append(keys, k)
	}
	sort.Ints(keys)
	intersection := 0
	for _, k := range keys {
		intersection += line[k]
		if ans < intersection {
			ans = intersection
		}
	}
	return
}
