type xorTrieNode struct {
	children [2]*xorTrieNode
	count    int
}

func updateTrie(root *xorTrieNode, value, delta int) {
	cur := root
	for bit := 14; bit >= 0; bit-- {
		b := (value >> bit) & 1
		if cur.children[b] == nil {
			cur.children[b] = &xorTrieNode{}
		}
		cur = cur.children[b]
		cur.count += delta
	}
}

func getMaxXor(root *xorTrieNode, value int) int {
	cur := root
	ans := 0
	for bit := 14; bit >= 0; bit-- {
		b := (value >> bit) & 1
		opp := 1 - b
		if cur.children[opp] != nil && cur.children[opp].count > 0 {
			ans |= 1 << bit
			cur = cur.children[opp]
		} else {
			cur = cur.children[b]
		}
	}
	return ans
}

func maxXor(nums []int, k int) int {
	n := len(nums)
	prefix := make([]int, n+1)
	for i, x := range nums {
		prefix[i+1] = prefix[i] ^ x
	}
	root := &xorTrieNode{}
	maxq, minq := []int{}, []int{}
	left, ans := 0, 0
	updateTrie(root, prefix[0], 1)
	for right, x := range nums {
		for len(maxq) > 0 && nums[maxq[len(maxq)-1]] <= x {
			maxq = maxq[:len(maxq)-1]
		}
		for len(minq) > 0 && nums[minq[len(minq)-1]] >= x {
			minq = minq[:len(minq)-1]
		}
		maxq = append(maxq, right)
		minq = append(minq, right)
		for nums[maxq[0]]-nums[minq[0]] > k {
			if maxq[0] == left {
				maxq = maxq[1:]
			}
			if minq[0] == left {
				minq = minq[1:]
			}
			updateTrie(root, prefix[left], -1)
			left++
		}
		ans = max(ans, getMaxXor(root, prefix[right+1]))
		updateTrie(root, prefix[right+1], 1)
	}
	return ans
}
