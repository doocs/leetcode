func findLeastNumOfUniqueInts(arr []int, k int) int {
	counter := make(map[int]int)
	for _, v := range arr {
		counter[v]++
	}
	var t [][]int
	for v, cnt := range counter {
		t = append(t, []int{v, cnt})
	}
	sort.Slice(t, func(i, j int) bool {
		return t[i][1] < t[j][1]
	})
	for _, e := range t {
		v, cnt := e[0], e[1]
		if k >= cnt {
			k -= cnt
			delete(counter, v)
		} else {
			break
		}
	}
	return len(counter)
}