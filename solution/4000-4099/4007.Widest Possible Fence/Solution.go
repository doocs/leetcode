func maximumWidth(planks []int) int {
	cnt := make(map[int]int)
	for _, x := range planks {
		cnt[x]++
	}

	t := make(map[int]int)
	ans := 0

	for x, v1 := range cnt {
		t[x] += v1
		if t[x] > ans {
			ans = t[x]
		}

		t[x*2] += v1 / 2
		if t[x*2] > ans {
			ans = t[x*2]
		}

		for y, v2 := range cnt {
			if y > x {
				key := x + y
				if v1 < v2 {
					t[key] += v1
				} else {
					t[key] += v2
				}
				if t[key] > ans {
					ans = t[key]
				}
			}
		}
	}

	return ans
}
