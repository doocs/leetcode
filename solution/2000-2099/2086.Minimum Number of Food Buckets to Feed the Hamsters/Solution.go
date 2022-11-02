func minimumBuckets(street string) int {
	ans, n := 0, len(street)
	for i := 0; i < n; i++ {
		if street[i] == 'H' {
			if i+1 < n && street[i+1] == '.' {
				ans++
				i += 2
			} else if i > 0 && street[i-1] == '.' {
				ans++
			} else {
				return -1
			}
		}
	}
	return ans
}