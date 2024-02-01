/**
 * Definition for an infinite stream.
 * type InfiniteStream interface {
 *     Next() int
 * }
 */
 func findPattern(stream InfiniteStream, pattern []int) int {
	a, b := 0, 0
	m := len(pattern)
	half := m >> 1
	mask1 := (1 << half) - 1
	mask2 := (1 << (m - half)) - 1
	for i := 0; i < half; i++ {
		a |= pattern[i] << (half - 1 - i)
	}
	for i := half; i < m; i++ {
		b |= pattern[i] << (m - 1 - i)
	}
	x, y := 0, 0
	for i := 1; ; i++ {
		v := stream.Next()
		y = y<<1 | v
		v = (y >> (m - half)) & 1
		y &= mask2
		x = x<<1 | v
		x &= mask1
		if i >= m && a == x && b == y {
			return i - m
		}
	}
}