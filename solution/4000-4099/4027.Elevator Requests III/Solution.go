func elevatorRequests(n int, start int, requests [][]int) int64 {
	m := len(requests)
	f := make([][]int64, 1<<m)

	for i := range f {
		f[i] = make([]int64, m)
	}

	const INF int64 = 1 << 60

	for i := 0; i < 1<<m; i++ {
		for j := 0; j < m; j++ {
			if (i>>j)&1 == 1 {
				f[i][j] = INF
				i0 := i ^ (1 << j)

				if i0 == 0 {
					d := int64(abs(start - requests[j][1]))
					f[i][j] = min(
						f[i][j],
						max(d, int64(requests[j][0])),
					)
				} else {
					for j0 := 0; j0 < m; j0++ {
						if j0 != j && (i>>j0)&1 == 1 {
							d := int64(abs(
								requests[j0][1] - requests[j][1],
							))

							f[i][j] = min(
								f[i][j],
								max(
									f[i0][j0]+d,
									int64(requests[j][0]),
								),
							)
						}
					}
				}
			}
		}
	}

	full := (1 << m) - 1
	ans := INF

	for j := 0; j < m; j++ {
		ans = min(ans, f[full][j])
	}

	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
