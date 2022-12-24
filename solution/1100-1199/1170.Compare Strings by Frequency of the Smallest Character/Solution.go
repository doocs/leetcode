func numSmallerByFrequency(queries []string, words []string) (ans []int) {
	f := func(s string) int {
		cnt := [26]int{}
		for _, c := range s {
			cnt[c-'a']++
		}
		for _, v := range cnt {
			if v > 0 {
				return v
			}
		}
		return 0
	}
	arr := []int{}
	for _, s := range words {
		arr = append(arr, f(s))
	}
	sort.Ints(arr)
	n := len(arr)
	for _, q := range queries {
		x := f(q)
		ans = append(ans, n-sort.Search(n, func(i int) bool { return arr[i] > x }))
	}
	return
}