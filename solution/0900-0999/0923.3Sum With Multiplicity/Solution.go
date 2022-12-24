func threeSumMulti(arr []int, target int) int {
	const mod int = 1e9 + 7
	cnt := [101]int{}
	for _, v := range arr {
		cnt[v]++
	}
	ans := 0
	for j, b := range arr {
		cnt[b]--
		for i := 0; i < j; i++ {
			a := arr[i]
			c := target - a - b
			if c >= 0 && c <= 100 {
				ans += cnt[c]
				ans %= mod
			}
		}
	}
	return ans
}