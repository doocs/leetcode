func minPartitions(n string) (ans int) {
	for _, c := range n {
		ans = max(ans, int(c-'0'))
	}
	return
}
