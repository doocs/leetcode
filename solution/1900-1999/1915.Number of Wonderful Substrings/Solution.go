func wonderfulSubstrings(word string) int64 {
	counter := make([]int, 1024)
	counter[0] = 1
	state := 0
	var ans int64
	for _, c := range word {
		state ^= (1 << (c - 'a'))
		ans += int64(counter[state])
		for i := 0; i < 10; i++ {
			ans += int64(counter[state^(1<<i)])
		}
		counter[state]++
	}
	return ans
}