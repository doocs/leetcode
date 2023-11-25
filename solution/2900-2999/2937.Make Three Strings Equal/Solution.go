func findMinimumOperations(s1 string, s2 string, s3 string) int {
	s := len(s1) + len(s2) + len(s3)
	n := min(len(s1), len(s2), len(s3))
	for i := range s1[:n] {
		if !(s1[i] == s2[i] && s2[i] == s3[i]) {
			if i == 0 {
				return -1
			}
			return s - 3*i
		}
	}
	return s - 3*n
}