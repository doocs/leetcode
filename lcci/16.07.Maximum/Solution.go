func maximum(a int, b int) int {
	k := (a - b) >> 63 & 1
	return a*(k^1) + b*k
}