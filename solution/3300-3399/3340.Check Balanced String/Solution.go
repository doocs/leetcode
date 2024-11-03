func isBalanced(num string) bool {
	f := [2]int{}
	for i, c := range num {
		f[i&1] += int(c - '0')
	}
	return f[0] == f[1]
}
