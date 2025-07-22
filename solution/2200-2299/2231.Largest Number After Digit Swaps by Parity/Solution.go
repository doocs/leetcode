func largestInteger(num int) int {
	s := []byte(fmt.Sprint(num))
	cnt := [10]int{}

	for _, c := range s {
		cnt[c-'0']++
	}

	idx := [2]int{8, 9}
	ans := 0

	for _, c := range s {
		x := int(c - '0')
		for cnt[idx[x&1]] == 0 {
			idx[x&1] -= 2
		}
		ans = ans*10 + idx[x&1]
		cnt[idx[x&1]]--
	}

	return ans
}
