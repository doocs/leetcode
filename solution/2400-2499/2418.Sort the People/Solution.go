func sortPeople(names []string, heights []int) []string {
	n := len(heights)
	type pair struct{ v, i int }
	arr := make([]pair, n)
	for i, v := range heights {
		arr[i] = pair{v, i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i].v > arr[j].v })
	ans := make([]string, n)
	for i, v := range arr {
		ans[i] = names[v.i]
	}
	return ans
}