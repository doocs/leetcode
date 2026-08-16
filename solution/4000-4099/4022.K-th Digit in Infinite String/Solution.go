import (
	"math"
	"strconv"
)

func kthDigit(k int64) int {
	if k <= 9 {
		return int(k)
	}

	k -= 9
	var d int64 = 2
	var start int64 = 1
	var size int64

	for {
		cnt := int64(9) * int64(math.Pow10(int(d-2)))
		size = 10 * d

		if k <= cnt*size {
			break
		}

		k -= cnt * size
		d++
		start *= 10
	}

	b := start + (k-1)/size
	pos := (k - 1) % size

	i := pos / d

	var num int64
	if b%2 == 0 {
		num = 10*b + i
	} else {
		num = 10*b + 9 - i
	}

	s := strconv.FormatInt(num, 10)

	return int(s[pos%d] - '0')
}
