func largestValsFromLabels(values []int, labels []int, numWanted int, useLimit int) int {
	var p [][]int
	for i, v := range values {
		p = append(p, []int{v, labels[i]})
	}
	sort.Slice(p, func(i, j int) bool {
		return p[i][0] > p[j][0]
	})
	counter := make(map[int]int)
	ans, num := 0, 0
	for _, t := range p {
		if num >= numWanted {
			break
		}
		v, l := t[0], t[1]
		if counter[l] < useLimit {
			counter[l]++
			num++
			ans += v
		}
	}
	return ans
}