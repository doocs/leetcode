func minimumRecolors(blocks string, k int) int {
	cnt, n := 0, len(blocks)
	i := 0
	for ; i < k; i++ {
		if blocks[i] == 'W' {
			cnt++
		}
	}
	ans := cnt
	for ; i < n; i++ {
		if blocks[i] == 'W' {
			cnt++
		}
		if blocks[i-k] == 'W' {
			cnt--
		}
		ans = min(ans, cnt)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}