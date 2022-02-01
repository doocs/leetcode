func minArea(image [][]byte, x int, y int) int {
	m, n := len(image), len(image[0])
	left, right := 0, x
	for left < right {
		mid := (left + right) >> 1
		c := 0
		for c < n && image[mid][c] == '0' {
			c++
		}
		if c < n {
			right = mid
		} else {
			left = mid + 1
		}
	}
	u := left
	left, right = x, m-1
	for left < right {
		mid := (left + right + 1) >> 1
		c := 0
		for c < n && image[mid][c] == '0' {
			c++
		}
		if c < n {
			left = mid
		} else {
			right = mid - 1
		}
	}
	d := left
	left, right = 0, y
	for left < right {
		mid := (left + right) >> 1
		r := 0
		for r < m && image[r][mid] == '0' {
			r++
		}
		if r < m {
			right = mid
		} else {
			left = mid + 1
		}
	}
	l := left
	left, right = y, n-1
	for left < right {
		mid := (left + right + 1) >> 1
		r := 0
		for r < m && image[r][mid] == '0' {
			r++
		}
		if r < m {
			left = mid
		} else {
			right = mid - 1
		}
	}
	r := left
	return (d - u + 1) * (r - l + 1)
}