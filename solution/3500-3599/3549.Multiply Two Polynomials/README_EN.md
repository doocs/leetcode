---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3549.Multiply%20Two%20Polynomials/README_EN.md
---

<!-- problem:start -->

# [3549. Multiply Two Polynomials 🔒](https://leetcode.com/problems/multiply-two-polynomials)

[中文文档](/solution/3500-3599/3549.Multiply%20Two%20Polynomials/README.md)

## Description

<!-- description:start -->

<p data-end="315" data-start="119">You are given two integer arrays <code>poly1</code> and <code>poly2</code>, where the element at index <code>i</code> in each array represents the coefficient of <code>x<sup>i</sup></code> in a polynomial.</p>

<p>Let <code>A(x)</code> and <code>B(x)</code> be the polynomials represented by <code>poly1</code> and <code>poly2</code>, respectively.</p>

<p>Return an integer array <code>result</code> representing the coefficients of the product polynomial <code>R(x) = A(x) * B(x)</code>, where <code>result[i]</code> denotes the coefficient of <code>x<sup>i</sup></code> in <code>R(x)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">poly1 = [3,2,5], poly2 = [1,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,14,13,20]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>A(x) = 3 + 2x + 5x<sup>2</sup></code> and <code>B(x) = 1 + 4x</code></li>
	<li><code>R(x) = (3 + 2x + 5x<sup>2</sup>) * (1 + 4x)</code></li>
	<li><code>R(x) = 3 * 1 + (3 * 4 + 2 * 1)x + (2 * 4 + 5 * 1)x<sup>2</sup> + (5 * 4)x<sup>3</sup></code></li>
	<li><code>R(x) = 3 + 14x + 13x<sup>2</sup> + 20x<sup>3</sup></code></li>
	<li>Thus, result = <code>[3, 14, 13, 20]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">poly1 = [1,0,-2], poly2 = [-1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,0,2]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>A(x) = 1 + 0x - 2x<sup>2</sup></code> and <code>B(x) = -1</code></li>
	<li><code>R(x) = (1 + 0x - 2x<sup>2</sup>) * (-1)</code></li>
	<li><code>R(x) = -1 + 0x + 2x<sup>2</sup></code></li>
	<li>Thus, result = <code>[-1, 0, 2]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">poly1 = [1,5,-3], poly2 = [-4,2,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-4,-18,22,-6,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>A(x) = 1 + 5x - 3x<sup>2</sup></code> and <code>B(x) = -4 + 2x + 0x<sup>2</sup></code></li>
	<li><code>R(x) = (1 + 5x - 3x<sup>2</sup>) * (-4 + 2x + 0x<sup>2</sup>)</code></li>
	<li><code>R(x) = 1 * -4 + (1 * 2 + 5 * -4)x + (5 * 2 + -3 * -4)x<sup>2</sup> + (-3 * 2)x<sup>3</sup> + 0x<sup>4</sup></code></li>
	<li><code>R(x) = -4 -18x + 22x<sup>2</sup> -6x<sup>3</sup> + 0x<sup>4</sup></code></li>
	<li>Thus, result = <code>[-4, -18, 22, -6, 0]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= poly1.length, poly2.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>3</sup> &lt;= poly1[i], poly2[i] &lt;= 10<sup>3</sup></code></li>
	<li><code>poly1</code> and <code>poly2</code> contain at least one non-zero coefficient.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: FFT

We can use the Fast Fourier Transform (FFT) to efficiently compute the product of two polynomials. FFT is an efficient algorithm that can compute the product of polynomials in $O(n \log n)$ time complexity.

The specific steps are as follows:

1. **Padding the length** Let the result length be $m = |A|+|B|-1$, and round it up to the nearest power of 2, $n$, to facilitate divide-and-conquer FFT.
2. **FFT transformation** Perform the forward FFT (with `invert=False`) on both coefficient sequences.
3. **Pointwise multiplication** Multiply the corresponding elements in the frequency domain.
4. **Inverse FFT** Perform the inverse FFT (with `invert=True`) on the product sequence, and round the real parts to the nearest integer to obtain the final coefficients.

The time complexity is $O(n \log n)$, and the space complexity is $O(n)$, where $n$ is the length of the polynomials.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def multiply(self, poly1: List[int], poly2: List[int]) -> List[int]:
        if not poly1 or not poly2:
            return []

        m = len(poly1) + len(poly2) - 1
        n = 1
        while n < m:
            n <<= 1

        fa = list(map(complex, poly1)) + [0j] * (n - len(poly1))
        fb = list(map(complex, poly2)) + [0j] * (n - len(poly2))

        self._fft(fa, invert=False)
        self._fft(fb, invert=False)

        for i in range(n):
            fa[i] *= fb[i]

        self._fft(fa, invert=True)
        return [int(round(fa[i].real)) for i in range(m)]

    def _fft(self, a: List[complex], invert: bool) -> None:
        n = len(a)

        j = 0
        for i in range(1, n):
            bit = n >> 1
            while j & bit:
                j ^= bit
                bit >>= 1
            j ^= bit
            if i < j:
                a[i], a[j] = a[j], a[i]

        len_ = 2
        while len_ <= n:
            ang = 2 * math.pi / len_ * (-1 if invert else 1)
            wlen = complex(math.cos(ang), math.sin(ang))
            for i in range(0, n, len_):
                w = 1 + 0j
                half = i + len_ // 2
                for j in range(i, half):
                    u = a[j]
                    v = a[j + len_ // 2] * w
                    a[j] = u + v
                    a[j + len_ // 2] = u - v
                    w *= wlen
            len_ <<= 1

        if invert:
            for i in range(n):
                a[i] /= n
```

#### Java

```java
class Solution {
    public long[] multiply(int[] poly1, int[] poly2) {
        if (poly1 == null || poly2 == null || poly1.length == 0 || poly2.length == 0) {
            return new long[0];
        }

        int m = poly1.length + poly2.length - 1;
        int n = 1;
        while (n < m) n <<= 1;

        Complex[] fa = new Complex[n];
        Complex[] fb = new Complex[n];
        for (int i = 0; i < n; i++) {
            fa[i] = new Complex(i < poly1.length ? poly1[i] : 0, 0);
            fb[i] = new Complex(i < poly2.length ? poly2[i] : 0, 0);
        }

        fft(fa, false);
        fft(fb, false);

        for (int i = 0; i < n; i++) {
            fa[i] = fa[i].mul(fb[i]);
        }

        fft(fa, true);

        long[] res = new long[m];
        for (int i = 0; i < m; i++) {
            res[i] = Math.round(fa[i].re);
        }
        return res;
    }

    private static void fft(Complex[] a, boolean invert) {
        int n = a.length;

        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >>> 1;
            while ((j & bit) != 0) {
                j ^= bit;
                bit >>>= 1;
            }
            j ^= bit;
            if (i < j) {
                Complex tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }

        for (int len = 2; len <= n; len <<= 1) {
            double ang = 2 * Math.PI / len * (invert ? -1 : 1);
            Complex wlen = new Complex(Math.cos(ang), Math.sin(ang));

            for (int i = 0; i < n; i += len) {
                Complex w = new Complex(1, 0);
                int half = len >>> 1;
                for (int j = 0; j < half; j++) {
                    Complex u = a[i + j];
                    Complex v = a[i + j + half].mul(w);
                    a[i + j] = u.add(v);
                    a[i + j + half] = u.sub(v);
                    w = w.mul(wlen);
                }
            }
        }

        if (invert) {
            for (int i = 0; i < n; i++) {
                a[i].re /= n;
                a[i].im /= n;
            }
        }
    }

    private static final class Complex {
        double re, im;
        Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }
        Complex add(Complex o) {
            return new Complex(re + o.re, im + o.im);
        }
        Complex sub(Complex o) {
            return new Complex(re - o.re, im - o.im);
        }
        Complex mul(Complex o) {
            return new Complex(re * o.re - im * o.im, re * o.im + im * o.re);
        }
    }
}
```

#### C++

```cpp
class Solution {
    using cd = complex<double>;

    void fft(vector<cd>& a, bool invert) {
        int n = a.size();
        for (int i = 1, j = 0; i < n; ++i) {
            int bit = n >> 1;
            for (; j & bit; bit >>= 1) j ^= bit;
            j ^= bit;
            if (i < j) swap(a[i], a[j]);
        }
        for (int len = 2; len <= n; len <<= 1) {
            double ang = 2 * M_PI / len * (invert ? -1 : 1);
            cd wlen(cos(ang), sin(ang));
            for (int i = 0; i < n; i += len) {
                cd w(1, 0);
                int half = len >> 1;
                for (int j = 0; j < half; ++j) {
                    cd u = a[i + j];
                    cd v = a[i + j + half] * w;
                    a[i + j] = u + v;
                    a[i + j + half] = u - v;
                    w *= wlen;
                }
            }
        }
        if (invert)
            for (cd& x : a) x /= n;
    }

public:
    vector<long long> multiply(vector<int>& poly1, vector<int>& poly2) {
        if (poly1.empty() || poly2.empty()) return {};
        int m = poly1.size() + poly2.size() - 1;
        int n = 1;
        while (n < m) n <<= 1;

        vector<cd> fa(n), fb(n);
        for (int i = 0; i < n; ++i) {
            fa[i] = i < poly1.size() ? cd(poly1[i], 0) : cd(0, 0);
            fb[i] = i < poly2.size() ? cd(poly2[i], 0) : cd(0, 0);
        }

        fft(fa, false);
        fft(fb, false);
        for (int i = 0; i < n; ++i) fa[i] *= fb[i];
        fft(fa, true);

        vector<long long> res(m);
        for (int i = 0; i < m; ++i) res[i] = llround(fa[i].real());
        return res;
    }
};
```

#### Go

```go

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
```

#### TypeScript

```ts
export function multiply(poly1: number[], poly2: number[]): number[] {
    const n1 = poly1.length,
        n2 = poly2.length;
    if (!n1 || !n2) return [];

    if (Math.min(n1, n2) <= 64) {
        const m = n1 + n2 - 1,
            res = new Array<number>(m).fill(0);
        for (let i = 0; i < n1; ++i) for (let j = 0; j < n2; ++j) res[i + j] += poly1[i] * poly2[j];
        return res.map(v => Math.round(v));
    }

    let n = 1,
        m = n1 + n2 - 1;
    while (n < m) n <<= 1;

    const reA = new Float64Array(n);
    const imA = new Float64Array(n);
    for (let i = 0; i < n1; ++i) reA[i] = poly1[i];

    const reB = new Float64Array(n);
    const imB = new Float64Array(n);
    for (let i = 0; i < n2; ++i) reB[i] = poly2[i];

    fft(reA, imA, false);
    fft(reB, imB, false);

    for (let i = 0; i < n; ++i) {
        const a = reA[i],
            b = imA[i],
            c = reB[i],
            d = imB[i];
        reA[i] = a * c - b * d;
        imA[i] = a * d + b * c;
    }

    fft(reA, imA, true);

    const out = new Array<number>(m);
    for (let i = 0; i < m; ++i) out[i] = Math.round(reA[i]);
    return out;
}

function fft(re: Float64Array, im: Float64Array, invert: boolean): void {
    const n = re.length;

    for (let i = 1, j = 0; i < n; ++i) {
        let bit = n >> 1;
        for (; j & bit; bit >>= 1) j ^= bit;
        j ^= bit;
        if (i < j) {
            [re[i], re[j]] = [re[j], re[i]];
            [im[i], im[j]] = [im[j], im[i]];
        }
    }

    for (let len = 2; len <= n; len <<= 1) {
        const ang = ((2 * Math.PI) / len) * (invert ? -1 : 1);
        const wlenCos = Math.cos(ang),
            wlenSin = Math.sin(ang);

        for (let i = 0; i < n; i += len) {
            let wRe = 1,
                wIm = 0;
            const half = len >> 1;
            for (let j = 0; j < half; ++j) {
                const uRe = re[i + j],
                    uIm = im[i + j];
                const vRe0 = re[i + j + half],
                    vIm0 = im[i + j + half];
                const vRe = vRe0 * wRe - vIm0 * wIm;
                const vIm = vRe0 * wIm + vIm0 * wRe;
                re[i + j] = uRe + vRe;
                im[i + j] = uIm + vIm;
                re[i + j + half] = uRe - vRe;
                im[i + j + half] = uIm - vIm;
                const nextWRe = wRe * wlenCos - wIm * wlenSin;
                wIm = wRe * wlenSin + wIm * wlenCos;
                wRe = nextWRe;
            }
        }
    }

    if (invert) {
        for (let i = 0; i < n; ++i) {
            re[i] /= n;
            im[i] /= n;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
