func countVowelStrings(n int) int {
	cnt := make([]int, 5)
	for i := range cnt {
		cnt[i] = 1
	}
	for i := 2; i <= n; i++ {
		for j := 3; j >= 0; j-- {
			cnt[j] += cnt[j+1]
		}
	}
	ans := 0
	for _, v := range cnt {
		ans += v
	}
	return ans
}