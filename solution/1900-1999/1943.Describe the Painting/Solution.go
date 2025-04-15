func splitPainting(segments [][]int) [][]int64 {
	d := make(map[int]int64)
	for _, seg := range segments {
		d[seg[0]] += int64(seg[2])
		d[seg[1]] -= int64(seg[2])
	}
	dList := make([]int, 0, len(d))
	for k := range d {
		dList = append(dList, k)
	}
	sort.Ints(dList)

	var ans [][]int64

	i := dList[0]
	cur := d[i]
	for j := 1; j < len(dList); j++ {
		it := d[dList[j]]
		if cur > 0 {
			ans = append(ans, []int64{int64(i), int64(dList[j]), cur})
		}
		cur += it
		i = dList[j]
	}

	return ans
}
