func dividePlayers(skill []int) (ans int64) {
	sort.Ints(skill)
	n := len(skill)
	t := skill[0] + skill[n-1]
	for i, j := 0, n-1; i < j; i, j = i+1, j-1 {
		if skill[i]+skill[j] != t {
			return -1
		}
		ans += int64(skill[i] * skill[j])
	}
	return
}