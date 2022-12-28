func compareVersion(version1 string, version2 string) int {
	m, n := len(version1), len(version2)
	for i, j := 0, 0; i < m || j < n; i, j = i+1, j+1 {
		var a, b int
		for i < m && version1[i] != '.' {
			a = a*10 + int(version1[i]-'0')
			i++
		}
		for j < n && version2[j] != '.' {
			b = b*10 + int(version2[j]-'0')
			j++
		}
		if a < b {
			return -1
		}
		if a > b {
			return 1
		}
	}
	return 0
}