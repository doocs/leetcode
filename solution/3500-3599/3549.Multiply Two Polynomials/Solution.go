
func multiply(poly1 []int, poly2 []int) []int64 {
	if len(poly1) == 0 || len(poly2) == 0 {
		return []int64{}
	}

	m := len(poly1) + len(poly2) - 1
	n := 1
	for n < m {
		n <<= 1
	}

	fa := make([]complex128, n)
	fb := make([]complex128, n)
	for i := 0; i < len(poly1); i++ {
		fa[i] = complex(float64(poly1[i]), 0)
	}
	for i := 0; i < len(poly2); i++ {
		fb[i] = complex(float64(poly2[i]), 0)
	}

	fft(fa, false)
	fft(fb, false)
	for i := 0; i < n; i++ {
		fa[i] *= fb[i]
	}
	fft(fa, true)

	res := make([]int64, m)
	for i := 0; i < m; i++ {
		res[i] = int64(math.Round(real(fa[i])))
	}
	return res
}

func fft(a []complex128, invert bool) {
	n := len(a)
	for i, j := 1, 0; i < n; i++ {
		bit := n >> 1
		for ; j&bit != 0; bit >>= 1 {
			j ^= bit
		}
		j ^= bit
		if i < j {
			a[i], a[j] = a[j], a[i]
		}
	}

	for length := 2; length <= n; length <<= 1 {
		angle := 2 * math.Pi / float64(length)
		if invert {
			angle = -angle
		}
		wlen := cmplx.Rect(1, angle)
		for i := 0; i < n; i += length {
			w := complex(1, 0)
			half := length >> 1
			for j := 0; j < half; j++ {
				u := a[i+j]
				v := a[i+j+half] * w
				a[i+j] = u + v
				a[i+j+half] = u - v
				w *= wlen
			}
		}
	}

	if invert {
		for i := range a {
			a[i] /= complex(float64(n), 0)
		}
	}
}