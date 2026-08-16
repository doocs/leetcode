func fft(a []complex128, inv bool) {
	n := len(a)

	for i, j := 1, 0; i < n; i++ {
		bit := n >> 1

		for j&bit != 0 {
			j ^= bit
			bit >>= 1
		}

		j ^= bit

		if i < j {
			a[i], a[j] = a[j], a[i]
		}
	}

	for length := 2; length <= n; length <<= 1 {
		ang := 2 * math.Pi / float64(length)

		if inv {
			ang = -ang
		}

		wlen := complex(
			math.Cos(ang),
			math.Sin(ang),
		)

		half := length >> 1

		for i := 0; i < n; i += length {
			w := complex(1.0, 0.0)

			for j := 0; j < half; j++ {
				x := i + j
				y := x + half

				u := a[x]
				v := a[y] * w

				a[x] = u + v
				a[y] = u - v

				w *= wlen
			}
		}
	}

	if inv {
		for i := range a {
			a[i] /= complex(float64(n), 0)
		}
	}
}

func minOperations(s string) int {
	n := len(s)

	size := 1
	for size < 2*n {
		size <<= 1
	}

	nums := make([]int, n)
	for i := 0; i < n; i++ {
		nums[i] = int(s[i] - 'a')
	}

	cost := make([]float64, 26)

	for t := 0; t < 26; t++ {
		for z := 0; z < 26; z++ {
			d := min(z, 26-z)

			cost[t] += float64(d) * math.Cos(
				-2*math.Pi*float64(t*z)/26,
			)
		}
	}

	dp := make([]float64, n)

	a := make([]complex128, size)
	b := make([]complex128, size)

	for t := 0; t < 14; t++ {
		theta := 2 * math.Pi * float64(t) / 26

		for i := 0; i < n; i++ {
			angle := theta * float64(nums[i])

			a[i] = complex(
				math.Cos(angle),
				math.Sin(angle),
			)
		}

		for i := n; i < size; i++ {
			a[i] = 0
		}

		fft(a, false)

		for i := 0; i < size; i++ {
			x := a[i]
			y := complex(
				real(a[(size-i)&(size-1)]),
				-imag(a[(size-i)&(size-1)]),
			)

			b[i] = x * y
			b[i] = complex(real(b[i]), -imag(b[i]))
		}

		fft(b, false)

		mult := 2.0
		if t == 0 || t == 13 {
			mult = 1.0
		}

		factor := mult * cost[t] / float64(size)

		for c := 0; c < n; c++ {
			dp[c] += factor *
				(real(b[c]) + real(b[c+n]))
		}
	}

	ans := int64(1 << 60)

	for k := 0; k < n; k++ {
		c := (2*k + n - 1) % n
		d := int64(math.Round(dp[c] / 52.0))

		if int64(k)+d < ans {
			ans = int64(k) + d
		}
	}

	return int(ans)
}
