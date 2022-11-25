func alertNames(keyName []string, keyTime []string) (ans []string) {
	d := map[string][]int{}
	for i, name := range keyName {
		var a, b int
		fmt.Sscanf(keyTime[i], "%d:%d", &a, &b)
		t := a*60 + b
		d[name] = append(d[name], t)
	}
	for name, ts := range d {
		n := len(ts)
		if n > 2 {
			sort.Ints(ts)
			for i := 0; i < n-2; i++ {
				if ts[i+2]-ts[i] <= 60 {
					ans = append(ans, name)
					break
				}
			}
		}
	}
	sort.Strings(ans)
	return
}