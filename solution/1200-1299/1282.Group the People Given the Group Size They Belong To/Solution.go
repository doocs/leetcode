func groupThePeople(groupSizes []int) [][]int {
	mp := make(map[int][]int)
	for i, x := range groupSizes {
		mp[x] = append(mp[x], i)
	}
	var res [][]int
	for x, indexes := range mp {
		for i := 0; i < len(indexes); i += x {
			res = append(res, indexes[i:i+x])
		}
	}
	return res
}