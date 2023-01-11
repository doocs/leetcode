func smallestTrimmedNumbers(nums []string, queries [][]int) []int {
	type pair struct {
		s string
		i int
	}
	ans := make([]int, len(queries))
	t := make([]pair, len(nums))
	for i, q := range queries {
		for j, s := range nums {
			t[j] = pair{s[len(s)-q[1]:], j}
		}
		sort.Slice(t, func(i, j int) bool { a, b := t[i], t[j]; return a.s < b.s || a.s == b.s && a.i < b.i })
		ans[i] = t[q[0]-1].i
	}
	return ans
}