func splitNum(num int) int {
	cnt := [10]int{}
	n := 0
	for ; num > 0; num /= 10 {
		cnt[num%10]++
		n++
	}
	ans := [2]int{}
	for i, j := 0, 0; i < n; i++ {
		for cnt[j] == 0 {
			j++
		}
		cnt[j]--
		ans[i&1] = ans[i&1]*10 + j
	}
	return ans[0] + ans[1]
}