func multiply(mat1 [][]int, mat2 [][]int) [][]int {
	r1, c1, c2 := len(mat1), len(mat1[0]), len(mat2[0])
	res := make([][]int, r1)
	for i := range res {
		res[i] = make([]int, c2)
	}
	mp := make(map[int][]int)
	for i := 0; i < r1; i++ {
		for j := 0; j < c1; j++ {
			if mat1[i][j] != 0 {
				mp[i] = append(mp[i], j)
			}
		}
	}
	for i := 0; i < r1; i++ {
		for j := 0; j < c2; j++ {
			for _, k := range mp[i] {
				res[i][j] += mat1[i][k] * mat2[k][j]
			}
		}
	}
	return res
}