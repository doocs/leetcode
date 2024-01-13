func garbageCollection(garbage []string, travel []int) (ans int) {
	f := func(x rune) int {
		ans, st := 0, 0
		for i, s := range garbage {
			cnt := strings.Count(s, string(x))
			if cnt > 0 {
				ans += cnt + st
				st = 0
			}
			if i < len(travel) {
				st += travel[i]
			}
		}
		return ans
	}
	return f('M') + f('P') + f('G')
}