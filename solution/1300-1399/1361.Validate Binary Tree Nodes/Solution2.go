func validateBinaryTreeNodes(n int, leftChild []int, rightChild []int) bool {
	indeg := make([]int, n)

	for _, c := range leftChild {
		if c != -1 {
			indeg[c]++
		}
	}
	for _, c := range rightChild {
		if c != -1 {
			indeg[c]++
		}
	}

	root := -1
	for i, x := range indeg {
		if x == 0 {
			root = i
			break
		}
	}
	if root == -1 {
		return false
	}

	q := []int{root}
	vis := map[int]bool{root: true}

	for len(q) > 0 {
		i := q[0]
		q = q[1:]

		j := leftChild[i]
		if j != -1 {
			if vis[j] {
				return false
			}
			vis[j] = true
			q = append(q, j)
		}

		j = rightChild[i]
		if j != -1 {
			if vis[j] {
				return false
			}
			vis[j] = true
			q = append(q, j)
		}
	}

	return len(vis) == n
}