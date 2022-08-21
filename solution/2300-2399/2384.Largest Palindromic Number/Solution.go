func largestPalindromic(num string) string {
	cnt := make([]int, 10)
	for _, c := range num {
		cnt[c-'0']++
	}
	ans := ""
	for i := 9; i >= 0; i-- {
		if cnt[i]%2 == 1 {
			ans = strconv.Itoa(i)
			cnt[i]--
			break
		}
	}
	for i := 0; i < 10; i++ {
		if cnt[i] > 0 {
			cnt[i] >>= 1
			s := strings.Repeat(strconv.Itoa(i), cnt[i])
			ans = s + ans + s
		}
	}
	ans = strings.Trim(ans, "0")
	if ans == "" {
		return "0"
	}
	return ans
}