const highest = 30

type trie struct {
	left, right *trie
}

func (root *trie) add(num int) {
	node := root
	for i := highest; i >= 0; i-- {
		bit := (num >> i) & 1
		if bit == 0 {
			if node.left == nil {
				node.left = &trie{}
			}
			node = node.left
		} else {
			if node.right == nil {
				node.right = &trie{}
			}
			node = node.right
		}
	}
}

func (root *trie) cal(num int) int {
	node := root
	res := 0
	for i := highest; i >= 0; i-- {
		bit := (num >> i) & 1
		if bit == 0 {
			if node.right != nil {
				res = res*2 + 1
				node = node.right
			} else {
				res = res * 2
				node = node.left
			}
		} else {
			if node.left != nil {
				res = res*2 + 1
				node = node.left
			} else {
				res = res * 2
				node = node.right
			}
		}
	}
	return res
}

func findMaximumXOR(nums []int) int {
	root := &trie{}
	res := 0
	for i := 1; i < len(nums); i++ {
		root.add(nums[i-1])
		res = max(res, root.cal(nums[i]))
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}