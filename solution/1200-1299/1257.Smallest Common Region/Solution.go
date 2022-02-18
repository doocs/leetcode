func findSmallestRegion(regions [][]string, region1 string, region2 string) string {
	m := make(map[string]string)
	for _, region := range regions {
		for i := 1; i < len(region); i++ {
			m[region[i]] = region[0]
		}
	}
	s := make(map[string]bool)
	for region1 != "" {
		s[region1] = true
		region1 = m[region1]
	}
	for region2 != "" {
		if s[region2] {
			return region2
		}
		region2 = m[region2]
	}
	return region1
}