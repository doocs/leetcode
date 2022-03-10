func minSetSize(arr []int) int {
	counter := make(map[int]int)
	for _, v := range arr {
		counter[v]++
	}
	var t []int
	for _, v := range counter {
		t = append(t, v)
	}
	sort.Slice(t, func(i, j int) bool {
		return t[i] > t[j]
	})
	ans, n := 0, 0
	for _, cnt := range t {
		n += cnt
		ans++
		if n*2 >= len(arr) {
			break
		}
	}
	return ans
}