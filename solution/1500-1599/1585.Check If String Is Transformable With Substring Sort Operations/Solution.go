func isTransformable(s string, t string) bool {
	pos := [10][]int{}
	for i, c := range s {
		pos[c-'0'] = append(pos[c-'0'], i)
	}
	for _, c := range t {
		x := int(c - '0')
		if len(pos[x]) == 0 {
			return false
		}
		for j := 0; j < x; j++ {
			if len(pos[j]) > 0 && pos[j][0] < pos[x][0] {
				return false
			}
		}
		pos[x] = pos[x][1:]
	}
	return true
}