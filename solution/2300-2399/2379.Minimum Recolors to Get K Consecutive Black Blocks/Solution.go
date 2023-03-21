func minimumRecolors(blocks string, k int) int {
	cnt := strings.Count(blocks[:k], "W")
	ans := cnt
	for i := k; i < len(blocks); i++ {
		if blocks[i] == 'W' {
			cnt++
		}
		if blocks[i-k] == 'W' {
			cnt--
		}
		if ans > cnt {
			ans = cnt
		}
	}
	return ans
}