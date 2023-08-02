func shortestSeq(big []int, small []int) []int {
	cnt := len(small)
	need := map[int]int{}
	window := map[int]int{}
	for _, x := range small {
		need[x] = 1
	}
	j, k, mi := 0, -1, 1<<30
	for i, x := range big {
		window[x]++
		if need[x] >= window[x] {
			cnt--
		}
		for cnt == 0 {
			if t := i - j + 1; t < mi {
				mi = t
				k = j
			}
			if need[big[j]] >= window[big[j]] {
				cnt++
			}
			window[big[j]]--
			j++
		}
	}
	if k < 0 {
		return []int{}
	}
	return []int{k, k + mi - 1}
}