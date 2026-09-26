func minOperationsToFlip(expression string) int {
	type pair struct{ val, cost int }
	nums := make([]pair, 0)
	ops := make([]byte, 0)
	merge := func(op byte) {
		b := nums[len(nums)-1]
		a := nums[len(nums)-2]
		nums = nums[:len(nums)-2]
		v1, c1, v2, c2 := a.val, a.cost, b.val, b.cost
		val, cost := 0, 0
		if op == '&' {
			val = v1 & v2
			if v1 == 1 && v2 == 1 {
				cost = min(c1, c2)
			} else if v1 == 0 && v2 == 0 {
				cost = min(c1+c2, 1+min(c1, c2))
			} else if v1 == 0 {
				cost = min(c1, 1)
			} else {
				cost = min(c2, 1)
			}
		} else {
			val = v1 | v2
			if v1 == 0 && v2 == 0 {
				cost = min(c1, c2)
			} else if v1 == 1 && v2 == 1 {
				cost = min(c1+c2, 1+min(c1, c2))
			} else if v1 == 1 {
				cost = min(c1, 1)
			} else {
				cost = min(c2, 1)
			}
		}
		nums = append(nums, pair{val, cost})
	}
	for i := 0; i < len(expression); i++ {
		c := expression[i]
		if c == '(' {
			ops = append(ops, c)
		} else if c == '0' || c == '1' {
			nums = append(nums, pair{int(c - '0'), 1})
		} else if c == '&' || c == '|' {
			for len(ops) > 0 && (ops[len(ops)-1] == '&' || ops[len(ops)-1] == '|') {
				merge(ops[len(ops)-1])
				ops = ops[:len(ops)-1]
			}
			ops = append(ops, c)
		} else {
			for ops[len(ops)-1] != '(' {
				merge(ops[len(ops)-1])
				ops = ops[:len(ops)-1]
			}
			ops = ops[:len(ops)-1]
		}
	}
	for len(ops) > 0 {
		merge(ops[len(ops)-1])
		ops = ops[:len(ops)-1]
	}
	return nums[0].cost
}
