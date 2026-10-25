func countMagicalPairs(s string) int {
	n := len(s)
	ans := 0
	for j := 0; j < n-1; j++ {
		cnt1 := map[byte]int{}
		cnt := [27]int{}
		for i := j; i >= 0; i-- {
			cnt1[s[i]]++
			cnt[len(cnt1)]++
		}
		cnt2 := map[byte]int{}
		for k := j + 1; k < n; k++ {
			cnt2[s[k]]++
			ans += cnt[len(cnt2)]
		}
	}
	return ans
}
