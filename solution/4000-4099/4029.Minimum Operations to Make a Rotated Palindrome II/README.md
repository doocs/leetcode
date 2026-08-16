---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4029.Minimum%20Operations%20to%20Make%20a%20Rotated%20Palindrome%20II/README.md
---

<!-- problem:start -->

# [4029. Minimum Operations to Make a Rotated Palindrome II 🔒](https://leetcode.cn/problems/minimum-operations-to-make-a-rotated-palindrome-ii)

[English Version](/solution/4000-4099/4029.Minimum%20Operations%20to%20Make%20a%20Rotated%20Palindrome%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters.</p>

<p>You can perform the following operations any number of times (including zero) and in any order:</p>

<ul>
	<li><strong>Increment</strong>: Choose any index <code>i</code> and replace <code>s[i]</code> with the next lowercase English letter. The letter after <code>&#39;z&#39;</code> is <code>&#39;a&#39;</code>.</li>
	<li><strong>Left rotate</strong>: Move the first character of the string to the end.</li>
</ul>

<p>Return the <strong>minimum</strong> number of operations required to make <code>s</code> a <span data-keyword="palindrome-string">palindrome</span>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>
One optimal solution:

<ul>
	<li>Left rotate the string: <code>&quot;abc&quot; -&gt; &quot;bca&quot;</code>.</li>
	<li>Increment <code>&#39;a&#39;</code> to <code>&#39;b&#39;</code>: <code>&quot;bca&quot; -&gt; &quot;bcb&quot;</code>.</li>
	<li><code>&quot;bcb&quot;</code> is a palindrome. Thus, the answer is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;yb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Increment the first character three times: <code>&quot;yb&quot; -&gt; &quot;zb&quot; -&gt; &quot;ab&quot; -&gt; &quot;bb&quot;</code>.</li>
	<li><code>&quot;bb&quot;</code> is a palindrome. Thus, the answer is 3.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s​​​​​​​​​​​​​​</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：FFT

本题与「得到旋转回文字符串的最少操作次数 I」相同，但 $n$ 可达 $5 \times 10^4$，无法枚举旋转次数后暴力配对。

左旋 $k$ 次后，新串下标 $i$ 对应原串下标 $(i+k) \bmod n$。回文配对 $(i, n-1-i)$ 的原串下标之和为 $2k+n-1$，对所有配对为常数。因此，旋转 $k$ 次后的所有配对，其原串下标之和模 $n$ 都等于 $c = (2k+n-1) \bmod n$。

一对字母的递增代价为环上较短弧长 $\min(d, 26-d)$。将代价函数看作 $\mathbb{Z}/26\mathbb{Z}$ 上的函数，用离散傅里叶变换展开后，对每个频率 $t$，把字符映射为相位 $e^{2\pi i t x / 26}$，再对序列做圆周卷积，即可一次性求出所有下标和 $c$ 对应的配对总代价。由于代价函数是偶函数，只需计算 $t = 0, \ldots, 13$ 共 $14$ 个频率（其余由共轭对称补全）。每个配对被计算两次，再除以 DFT 的 $26$，因此将卷积结果除以 $52$ 并四舍五入即得递增代价。

对每个 $k$，答案候选为 $k$ 加上对应 $c$ 的递增代价，取最小值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串的长度。

<!-- tabs:start -->

#### Python3

```python
import numpy as np


class Solution:
    def minOperations(self, s: str) -> int:
        n = len(s)

        size = 1
        while size < 2 * n:
            size <<= 1

        nums = np.array([ord(c) - ord('a') for c in s], dtype=np.int64)

        cost = np.zeros(26)

        for t in range(26):
            for z in range(26):
                cost[t] += min(z, 26 - z) * math.cos(2 * math.pi * t * z / 26)

        dp = np.zeros(n)

        for t in range(14):
            theta = 2 * math.pi * t / 26

            a = np.exp(1j * theta * nums)
            a = np.pad(a, (0, size - n))

            b = np.conj(a)

            fa = np.fft.fft(a)
            fb = np.fft.fft(b)

            conv = np.fft.ifft(fa * fb).real

            mult = 1 if t == 0 or t == 13 else 2

            dp += mult * cost[t] * (conv[:n] + conv[n : 2 * n])

        ans = inf

        for k in range(n):
            c = (2 * k + n - 1) % n
            d = round(dp[c] / 52)

            ans = min(ans, k + d)

        return ans
```

#### Java

```java
class Solution {
    static final double PI = Math.PI;

    void fft(double[] re, double[] im, boolean inv) {
        int n = re.length;

        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >> 1;
            while ((j & bit) != 0) {
                j ^= bit;
                bit >>= 1;
            }
            j ^= bit;

            if (i < j) {
                double t = re[i];
                re[i] = re[j];
                re[j] = t;

                t = im[i];
                im[i] = im[j];
                im[j] = t;
            }
        }

        for (int len = 2; len <= n; len <<= 1) {
            double ang = 2.0 * PI / len * (inv ? -1 : 1);
            double wr = Math.cos(ang);
            double wi = Math.sin(ang);

            int half = len >> 1;

            for (int i = 0; i < n; i += len) {
                double cr = 1.0;
                double ci = 0.0;

                for (int j = 0; j < half; j++) {
                    int x = i + j;
                    int y = x + half;

                    double tr = re[y] * cr - im[y] * ci;
                    double ti = re[y] * ci + im[y] * cr;

                    double ur = re[x];
                    double ui = im[x];

                    re[x] = ur + tr;
                    im[x] = ui + ti;
                    re[y] = ur - tr;
                    im[y] = ui - ti;

                    double nr = cr * wr - ci * wi;
                    double ni = cr * wi + ci * wr;
                    cr = nr;
                    ci = ni;
                }
            }
        }

        if (inv) {
            for (int i = 0; i < n; i++) {
                re[i] /= n;
                im[i] /= n;
            }
        }
    }

    public int minOperations(String s) {
        int n = s.length();

        int size = 1;
        while (size < 2 * n) {
            size <<= 1;
        }

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = s.charAt(i) - 'a';
        }

        double[] cost = new double[26];

        for (int t = 0; t < 26; t++) {
            for (int z = 0; z < 26; z++) {
                int d = Math.min(z, 26 - z);
                cost[t] += d * Math.cos(-2.0 * PI * t * z / 26);
            }
        }

        double[] dp = new double[n];

        double[] re = new double[size];
        double[] im = new double[size];

        double[] bre = new double[size];
        double[] bim = new double[size];

        for (int t = 0; t < 14; t++) {
            double theta = 2.0 * PI * t / 26;

            for (int i = 0; i < n; i++) {
                double angle = theta * nums[i];
                re[i] = Math.cos(angle);
                im[i] = Math.sin(angle);
            }

            Arrays.fill(re, n, size, 0);
            Arrays.fill(im, n, size, 0);

            fft(re, im, false);

            for (int i = 0; i < size; i++) {
                double ar = re[i];
                double ai = im[i];

                int j = (size - i) & (size - 1);

                double br = re[j];
                double bi = -im[j];

                bre[i] = ar * br - ai * bi;
                bim[i] = ar * bi + ai * br;

                bim[i] = -bim[i];
            }

            fft(bre, bim, false);

            double mult = (t == 0 || t == 13) ? 1.0 : 2.0;
            double factor = mult * cost[t] / size;

            for (int c = 0; c < n; c++) {
                dp[c] += factor * (bre[c] + bre[c + n]);
            }
        }

        long ans = Long.MAX_VALUE;

        for (int k = 0; k < n; k++) {
            int c = (2 * k + n - 1) % n;
            long d = Math.round(dp[c] / 52.0);

            ans = Math.min(ans, k + d);
        }

        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
    using cd = complex<double>;
    const double PI = acos(-1);

    void fft(vector<cd>& a, bool inv) {
        int n = a.size();

        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >> 1;

            while (j & bit) {
                j ^= bit;
                bit >>= 1;
            }

            j ^= bit;

            if (i < j) {
                swap(a[i], a[j]);
            }
        }

        for (int len = 2; len <= n; len <<= 1) {
            double ang = 2.0 * PI / len * (inv ? -1 : 1);
            cd wlen(cos(ang), sin(ang));

            for (int i = 0; i < n; i += len) {
                cd w(1);

                for (int j = 0; j < len / 2; j++) {
                    cd u = a[i + j];
                    cd v = a[i + j + len / 2] * w;

                    a[i + j] = u + v;
                    a[i + j + len / 2] = u - v;

                    w *= wlen;
                }
            }
        }

        if (inv) {
            for (auto& x : a) {
                x /= n;
            }
        }
    }

public:
    int minOperations(string s) {
        int n = s.size();

        int size = 1;
        while (size < 2 * n) {
            size <<= 1;
        }

        vector<int> nums(n);
        for (int i = 0; i < n; i++) {
            nums[i] = s[i] - 'a';
        }

        vector<double> cost(26);

        for (int t = 0; t < 26; t++) {
            for (int z = 0; z < 26; z++) {
                int d = min(z, 26 - z);

                cost[t] += d * cos(-2.0 * PI * t * z / 26);
            }
        }

        vector<double> dp(n);

        vector<cd> a(size);
        vector<cd> b(size);

        for (int t = 0; t < 14; t++) {
            double theta = 2.0 * PI * t / 26;

            for (int i = 0; i < n; i++) {
                double angle = theta * nums[i];
                a[i] = cd(cos(angle), sin(angle));
            }

            for (int i = n; i < size; i++) {
                a[i] = 0;
            }

            fft(a, false);

            for (int i = 0; i < size; i++) {
                cd x = a[i];
                cd y = conj(a[(size - i) & (size - 1)]);

                b[i] = x * y;
                b[i] = conj(b[i]);
            }

            fft(b, false);

            double mult = (t == 0 || t == 13) ? 1.0 : 2.0;
            double factor = mult * cost[t] / size;

            for (int c = 0; c < n; c++) {
                dp[c] += factor * (b[c].real() + b[c + n].real());
            }
        }

        long long ans = LLONG_MAX;

        for (int k = 0; k < n; k++) {
            int c = (2 * k + n - 1) % n;
            long long d = llround(dp[c] / 52.0);

            ans = min(ans, k + d);
        }

        return (int) ans;
    }
};
```

#### Go

```go
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
```

#### TypeScript

```ts
function minOperations(s: string): number {
    const n = s.length;

    let size = 1;
    while (size < 2 * n) {
        size <<= 1;
    }

    const nums: number[] = [];
    for (const c of s) {
        nums.push(c.charCodeAt(0) - 97);
    }

    const cost = Array(26).fill(0);

    for (let t = 0; t < 26; t++) {
        for (let z = 0; z < 26; z++) {
            const d = Math.min(z, 26 - z);
            cost[t] += d * Math.cos((-2 * Math.PI * t * z) / 26);
        }
    }

    const dp = Array(n).fill(0);

    const re = Array(size).fill(0);
    const im = Array(size).fill(0);
    const bre = Array(size).fill(0);
    const bim = Array(size).fill(0);

    function fft(re: number[], im: number[], inv: boolean): void {
        const n = re.length;

        for (let i = 1, j = 0; i < n; i++) {
            let bit = n >> 1;

            while (j & bit) {
                j ^= bit;
                bit >>= 1;
            }

            j ^= bit;

            if (i < j) {
                [re[i], re[j]] = [re[j], re[i]];
                [im[i], im[j]] = [im[j], im[i]];
            }
        }

        for (let len = 2; len <= n; len <<= 1) {
            let ang = (2 * Math.PI) / len;

            if (inv) {
                ang = -ang;
            }

            const wr = Math.cos(ang);
            const wi = Math.sin(ang);
            const half = len >> 1;

            for (let i = 0; i < n; i += len) {
                let cr = 1;
                let ci = 0;

                for (let j = 0; j < half; j++) {
                    const x = i + j;
                    const y = x + half;

                    const tr = re[y] * cr - im[y] * ci;
                    const ti = re[y] * ci + im[y] * cr;

                    const ur = re[x];
                    const ui = im[x];

                    re[x] = ur + tr;
                    im[x] = ui + ti;

                    re[y] = ur - tr;
                    im[y] = ui - ti;

                    const nr = cr * wr - ci * wi;
                    const ni = cr * wi + ci * wr;

                    cr = nr;
                    ci = ni;
                }
            }
        }

        if (inv) {
            for (let i = 0; i < n; i++) {
                re[i] /= n;
                im[i] /= n;
            }
        }
    }

    for (let t = 0; t < 14; t++) {
        const theta = (2 * Math.PI * t) / 26;

        for (let i = 0; i < n; i++) {
            const angle = theta * nums[i];

            re[i] = Math.cos(angle);
            im[i] = Math.sin(angle);
        }

        for (let i = n; i < size; i++) {
            re[i] = 0;
            im[i] = 0;
        }

        fft(re, im, false);

        for (let i = 0; i < size; i++) {
            const j = (size - i) & (size - 1);

            const ar = re[i];
            const ai = im[i];

            const br = re[j];
            const bi = -im[j];

            bre[i] = ar * br - ai * bi;
            bim[i] = -(ar * bi + ai * br);
        }

        fft(bre, bim, false);

        const mult = t === 0 || t === 13 ? 1 : 2;
        const factor = (mult * cost[t]) / size;

        for (let c = 0; c < n; c++) {
            dp[c] += factor * (bre[c] + bre[c + n]);
        }
    }

    let ans = Number.MAX_SAFE_INTEGER;

    for (let k = 0; k < n; k++) {
        const c = (2 * k + n - 1) % n;
        const d = Math.round(dp[c] / 52);

        ans = Math.min(ans, k + d);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
