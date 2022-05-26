func destCity(paths [][]string) string {
	mp := make(map[string]string)
	for _, path := range paths {
		mp[path[0]] = path[1]
	}
	a := paths[0][0]
	for true {
		if _, ok := mp[a]; !ok {
			return a
		}
		a = mp[a]
	}
	return ""
}