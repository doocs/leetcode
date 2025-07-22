func longestCommonSubsequence(arrays [][]int) (ans []int) {
	cnt := [101]int{}
	for _, row := range arrays {
		for _, x := range row {
			cnt[x]++
		}
	}
	for x, v := range cnt {
		if v == len(arrays) {
			ans = append(ans, x)
		}
	}
	return
}
