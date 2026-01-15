func minimumPairRemoval(nums []int) (ans int) {
	type pair struct{ s, i int }

	n := len(nums)
	inv := 0

	sl := redblacktree.NewWith[pair, struct{}](func(a, b pair) int { return cmp.Or(a.s-b.s, a.i-b.i) })
	idx := redblacktree.New[int, struct{}]()
	for i := 0; i < n; i++ {
		idx.Put(i, struct{}{})
	}

	for i := 0; i < n-1; i++ {
		if nums[i] > nums[i+1] {
			inv++
		}
		sl.Put(pair{nums[i] + nums[i+1], i}, struct{}{})
	}

	for inv > 0 {
		ans++

		it := sl.Iterator()
		it.First()
		p := it.Key()
		sl.Remove(p)

		s, i := p.s, p.i

		jNode, _ := idx.Ceiling(i + 1)
		j := jNode.Key

		if nums[i] > nums[j] {
			inv--
		}

		if hNode, ok := idx.Floor(i - 1); ok {
			h := hNode.Key

			if nums[h] > nums[i] {
				inv--
			}
			sl.Remove(pair{nums[h] + nums[i], h})

			if nums[h] > s {
				inv++
			}
			sl.Put(pair{nums[h] + s, h}, struct{}{})
		}

		if kNode, ok := idx.Ceiling(j + 1); ok {
			k := kNode.Key

			if nums[j] > nums[k] {
				inv--
			}
			sl.Remove(pair{nums[j] + nums[k], j})

			if s > nums[k] {
				inv++
			}
			sl.Put(pair{s + nums[k], i}, struct{}{})
		}

		nums[i] = s
		idx.Remove(j)
	}

	return
}
