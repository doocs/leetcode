func minPartitions(n string) int {
	res := 0
	for _, c := range n {
		t := int(c - '0')
		if t > res {
			res = t
		}
	}
	return res
}