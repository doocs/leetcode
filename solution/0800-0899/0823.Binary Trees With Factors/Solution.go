func numFactoredBinaryTrees(arr []int) int {
	const mod int = 1e9 + 7
	sort.Ints(arr)
	f := make([]int, len(arr))
	idx := map[int]int{}
	for i, v := range arr {
		f[i] = 1
		idx[v] = i
	}
	for i, a := range arr {
		for j := 0; j < i; j++ {
			b := arr[j]
			if c := a / b; a%b == 0 {
				if k, ok := idx[c]; ok {
					f[i] = (f[i] + f[j]*f[k]) % mod
				}
			}
		}
	}
	ans := 0
	for _, v := range f {
		ans = (ans + v) % mod
	}
	return ans
}