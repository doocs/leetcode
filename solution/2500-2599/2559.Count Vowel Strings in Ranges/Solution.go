func vowelStrings(words []string, queries [][]int) []int {
	vowels := map[byte]bool{'a': true, 'e': true, 'i': true, 'o': true, 'u': true}
	nums := []int{}
	for i, w := range words {
		if vowels[w[0]] && vowels[w[len(w)-1]] {
			nums = append(nums, i)
		}
	}
	ans := make([]int, len(queries))
	for i, q := range queries {
		l, r := q[0], q[1]
		ans[i] = sort.SearchInts(nums, r+1) - sort.SearchInts(nums, l)
	}
	return ans
}