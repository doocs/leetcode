func maxFrequency(nums []int, k int, numOperations int) (ans int) {
	cnt := make(map[int]int)
	d := make(map[int]int)
	for _, x := range nums {
		cnt[x]++
		d[x] = d[x]
		d[x-k]++
		d[x+k+1]--
	}

	s := 0
	keys := make([]int, 0, len(d))
	for key := range d {
		keys = append(keys, key)
	}
	sort.Ints(keys)
	for _, x := range keys {
		s += d[x]
		ans = max(ans, min(s, cnt[x]+numOperations))
	}

	return
}
