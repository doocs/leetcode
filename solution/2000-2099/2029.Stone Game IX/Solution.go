func stoneGameIX(stones []int) bool {
	c1 := [3]int{}
	for _, x := range stones {
		c1[x%3]++
	}
	c2 := [3]int{c1[0], c1[2], c1[1]}
	check := func(cnt [3]int) bool {
		if cnt[1] == 0 {
			return false
		}
		cnt[1]--
		r := 1 + min(cnt[1], cnt[2])*2 + cnt[0]
		if cnt[1] > cnt[2] {
			cnt[1]--
			r++
		}
		return r%2 == 1 && cnt[1] != cnt[2]
	}
	return check(c1) || check(c2)
}