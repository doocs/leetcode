func canEat(candiesCount []int, queries [][]int) (ans []bool) {
	n := len(candiesCount)
	s := make([]int, n+1)
	for i, v := range candiesCount {
		s[i+1] = s[i] + v
	}
	for _, q := range queries {
		t, day, mx := q[0], q[1], q[2]
		least, most := day, (day+1)*mx
		ans = append(ans, least < s[t+1] && most > s[t])
	}
	return
}