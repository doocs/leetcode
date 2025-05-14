---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3337.Total%20Characters%20in%20String%20After%20Transformations%20II/README_EN.md
rating: 2411
source: Weekly Contest 421 Q4
tags:
    - Hash Table
    - Math
    - String
    - Dynamic Programming
    - Counting
---

<!-- problem:start -->

# [3337. Total Characters in String After Transformations II](https://leetcode.com/problems/total-characters-in-string-after-transformations-ii)

[中文文档](/solution/3300-3399/3337.Total%20Characters%20in%20String%20After%20Transformations%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters, an integer <code>t</code> representing the number of <strong>transformations</strong> to perform, and an array <code>nums</code> of size 26. In one <strong>transformation</strong>, every character in <code>s</code> is replaced according to the following rules:</p>

<ul>
	<li>Replace <code>s[i]</code> with the <strong>next</strong> <code>nums[s[i] - &#39;a&#39;]</code> consecutive characters in the alphabet. For example, if <code>s[i] = &#39;a&#39;</code> and <code>nums[0] = 3</code>, the character <code>&#39;a&#39;</code> transforms into the next 3 consecutive characters ahead of it, which results in <code>&quot;bcd&quot;</code>.</li>
	<li>The transformation <strong>wraps</strong> around the alphabet if it exceeds <code>&#39;z&#39;</code>. For example, if <code>s[i] = &#39;y&#39;</code> and <code>nums[24] = 3</code>, the character <code>&#39;y&#39;</code> transforms into the next 3 consecutive characters ahead of it, which results in <code>&quot;zab&quot;</code>.</li>
</ul>

<p>Return the length of the resulting string after <strong>exactly</strong> <code>t</code> transformations.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcyy&quot;, t = 2, nums = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>
	<p><strong>First Transformation (t = 1):</strong></p>

    <ul>
    	<li><code>&#39;a&#39;</code> becomes <code>&#39;b&#39;</code> as <code>nums[0] == 1</code></li>
    	<li><code>&#39;b&#39;</code> becomes <code>&#39;c&#39;</code> as <code>nums[1] == 1</code></li>
    	<li><code>&#39;c&#39;</code> becomes <code>&#39;d&#39;</code> as <code>nums[2] == 1</code></li>
    	<li><code>&#39;y&#39;</code> becomes <code>&#39;z&#39;</code> as <code>nums[24] == 1</code></li>
    	<li><code>&#39;y&#39;</code> becomes <code>&#39;z&#39;</code> as <code>nums[24] == 1</code></li>
    	<li>String after the first transformation: <code>&quot;bcdzz&quot;</code></li>
    </ul>
    </li>
    <li>
    <p><strong>Second Transformation (t = 2):</strong></p>

    <ul>
    	<li><code>&#39;b&#39;</code> becomes <code>&#39;c&#39;</code> as <code>nums[1] == 1</code></li>
    	<li><code>&#39;c&#39;</code> becomes <code>&#39;d&#39;</code> as <code>nums[2] == 1</code></li>
    	<li><code>&#39;d&#39;</code> becomes <code>&#39;e&#39;</code> as <code>nums[3] == 1</code></li>
    	<li><code>&#39;z&#39;</code> becomes <code>&#39;ab&#39;</code> as <code>nums[25] == 2</code></li>
    	<li><code>&#39;z&#39;</code> becomes <code>&#39;ab&#39;</code> as <code>nums[25] == 2</code></li>
    	<li>String after the second transformation: <code>&quot;cdeabab&quot;</code></li>
    </ul>
    </li>
    <li>
    <p><strong>Final Length of the string:</strong> The string is <code>&quot;cdeabab&quot;</code>, which has 7 characters.</p>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;azbk&quot;, t = 1, nums = [2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>
	<p><strong>First Transformation (t = 1):</strong></p>

    <ul>
    	<li><code>&#39;a&#39;</code> becomes <code>&#39;bc&#39;</code> as <code>nums[0] == 2</code></li>
    	<li><code>&#39;z&#39;</code> becomes <code>&#39;ab&#39;</code> as <code>nums[25] == 2</code></li>
    	<li><code>&#39;b&#39;</code> becomes <code>&#39;cd&#39;</code> as <code>nums[1] == 2</code></li>
    	<li><code>&#39;k&#39;</code> becomes <code>&#39;lm&#39;</code> as <code>nums[10] == 2</code></li>
    	<li>String after the first transformation: <code>&quot;bcabcdlm&quot;</code></li>
    </ul>
    </li>
    <li>
    <p><strong>Final Length of the string:</strong> The string is <code>&quot;bcabcdlm&quot;</code>, which has 8 characters.</p>
    </li>

</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= t &lt;= 10<sup>9</sup></code></li>
	<li><code><font face="monospace">nums.length == 26</font></code></li>
	<li><code><font face="monospace">1 &lt;= nums[i] &lt;= 25</font></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

Solution 1: Fast Matrix Exponentiation to Accelerate Recurrence
We define $f[i][j]$ as the number of times the $j$-th letter appears in the alphabet after $i$ transformations. Initially, $f[0][j]$ corresponds to the frequency of the $j$-th letter in the input string $s$.

Since the frequency of each letter after a transformation affects the next transformation, and the total number of transformations $t$ can be large, we can accelerate this recurrence process using fast matrix exponentiation.

Note that the result can be very large, so we take modulo $10^9 + 7$.

The time complexity of this approach is $O(n + \log t \times |\Sigma|^3)$, where $n$ is the length of the string and $|\Sigma|$ is the size of the alphabet (in this case, 26). The space complexity is $O(|\Sigma|^2)$, which is the size of the matrix used for matrix multiplication.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lengthAfterTransformations(self, s: str, t: int, nums: List[int]) -> int:
        mod = 10**9 + 7
        m = 26

        cnt = [0] * m
        for c in s:
            cnt[ord(c) - ord("a")] += 1

        matrix = [[0] * m for _ in range(m)]
        for i, x in enumerate(nums):
            for j in range(1, x + 1):
                matrix[i][(i + j) % m] = 1

        def matmul(a: List[List[int]], b: List[List[int]]) -> List[List[int]]:
            n, p, q = len(a), len(b), len(b[0])
            res = [[0] * q for _ in range(n)]
            for i in range(n):
                for k in range(p):
                    if a[i][k]:
                        for j in range(q):
                            res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % mod
            return res

        def matpow(mat: List[List[int]], power: int) -> List[List[int]]:
            res = [[int(i == j) for j in range(m)] for i in range(m)]
            while power:
                if power % 2:
                    res = matmul(res, mat)
                mat = matmul(mat, mat)
                power //= 2
            return res

        cnt = [cnt]
        factor = matpow(matrix, t)
        result = matmul(cnt, factor)[0]

        ans = sum(result) % mod
        return ans
```

#### Java

```java
class Solution {
    private final int mod = (int) 1e9 + 7;

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        final int m = 26;

        int[] cnt = new int[m];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int[][] matrix = new int[m][m];
        for (int i = 0; i < m; i++) {
            int num = nums.get(i);
            for (int j = 1; j <= num; j++) {
                matrix[i][(i + j) % m] = 1;
            }
        }

        int[][] factor = matpow(matrix, t, m);
        int[] result = vectorMatrixMultiply(cnt, factor);
        int ans = 0;
        for (int val : result) {
            ans = (ans + val) % mod;
        }
        return ans;
    }

    private int[][] matmul(int[][] a, int[][] b) {
        int n = a.length;
        int p = b.length;
        int q = b[0].length;
        int[][] res = new int[n][q];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < p; k++) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < q; j++) {
                    res[i][j] = (int) ((res[i][j] + 1L * a[i][k] * b[k][j]) % mod);
                }
            }
        }
        return res;
    }

    private int[][] matpow(int[][] mat, int power, int m) {
        int[][] res = new int[m][m];
        for (int i = 0; i < m; i++) {
            res[i][i] = 1;
        }
        while (power > 0) {
            if ((power & 1) != 0) {
                res = matmul(res, mat);
            }
            mat = matmul(mat, mat);
            power >>= 1;
        }
        return res;
    }

    private int[] vectorMatrixMultiply(int[] vector, int[][] matrix) {
        int n = matrix.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                sum = (sum + 1L * vector[j] * matrix[j][i]) % mod;
            }
            result[i] = (int) sum;
        }
        return result;
    }
}
```

#### C++

```cpp
class Solution {
public:
    static constexpr int MOD = 1e9 + 7;
    static constexpr int M = 26;

    using Matrix = vector<vector<int>>;

    Matrix matmul(const Matrix& a, const Matrix& b) {
        int n = a.size(), p = b.size(), q = b[0].size();
        Matrix res(n, vector<int>(q, 0));
        for (int i = 0; i < n; ++i) {
            for (int k = 0; k < p; ++k) {
                if (a[i][k]) {
                    for (int j = 0; j < q; ++j) {
                        res[i][j] = (res[i][j] + 1LL * a[i][k] * b[k][j] % MOD) % MOD;
                    }
                }
            }
        }
        return res;
    }

    Matrix matpow(Matrix mat, int power) {
        Matrix res(M, vector<int>(M, 0));
        for (int i = 0; i < M; ++i) res[i][i] = 1;
        while (power) {
            if (power % 2) res = matmul(res, mat);
            mat = matmul(mat, mat);
            power /= 2;
        }
        return res;
    }

    int lengthAfterTransformations(string s, int t, vector<int>& nums) {
        vector<int> cnt(M, 0);
        for (char c : s) {
            cnt[c - 'a']++;
        }

        Matrix matrix(M, vector<int>(M, 0));
        for (int i = 0; i < M; ++i) {
            for (int j = 1; j <= nums[i]; ++j) {
                matrix[i][(i + j) % M] = 1;
            }
        }

        Matrix cntMat(1, vector<int>(M));
        for (int i = 0; i < M; ++i) cntMat[0][i] = cnt[i];

        Matrix factor = matpow(matrix, t);
        Matrix result = matmul(cntMat, factor);

        int ans = 0;
        for (int x : result[0]) {
            ans = (ans + x) % MOD;
        }

        return ans;
    }
};
```

#### Go

```go
func lengthAfterTransformations(s string, t int, nums []int) int {
	const MOD = 1e9 + 7
	const M = 26

	cnt := make([]int, M)
	for _, c := range s {
		cnt[int(c-'a')]++
	}

	matrix := make([][]int, M)
	for i := 0; i < M; i++ {
		matrix[i] = make([]int, M)
		for j := 1; j <= nums[i]; j++ {
			matrix[i][(i+j)%M] = 1
		}
	}

	matmul := func(a, b [][]int) [][]int {
		n, p, q := len(a), len(b), len(b[0])
		res := make([][]int, n)
		for i := 0; i < n; i++ {
			res[i] = make([]int, q)
			for k := 0; k < p; k++ {
				if a[i][k] != 0 {
					for j := 0; j < q; j++ {
						res[i][j] = (res[i][j] + a[i][k]*b[k][j]%MOD) % MOD
					}
				}
			}
		}
		return res
	}

	matpow := func(mat [][]int, power int) [][]int {
		res := make([][]int, M)
		for i := 0; i < M; i++ {
			res[i] = make([]int, M)
			res[i][i] = 1
		}
		for power > 0 {
			if power%2 == 1 {
				res = matmul(res, mat)
			}
			mat = matmul(mat, mat)
			power /= 2
		}
		return res
	}

	cntMat := [][]int{make([]int, M)}
	copy(cntMat[0], cnt)

	factor := matpow(matrix, t)
	result := matmul(cntMat, factor)

	ans := 0
	for _, v := range result[0] {
		ans = (ans + v) % MOD
	}
	return ans
}
```

#### TypeScript

```ts
function lengthAfterTransformations(s: string, t: number, nums: number[]): number {
    const MOD = BigInt(1e9 + 7);
    const M = 26;

    const cnt: number[] = Array(M).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
    }

    const matrix: number[][] = Array.from({ length: M }, () => Array(M).fill(0));
    for (let i = 0; i < M; i++) {
        for (let j = 1; j <= nums[i]; j++) {
            matrix[i][(i + j) % M] = 1;
        }
    }

    const matmul = (a: number[][], b: number[][]): number[][] => {
        const n = a.length,
            p = b.length,
            q = b[0].length;
        const res: number[][] = Array.from({ length: n }, () => Array(q).fill(0));
        for (let i = 0; i < n; i++) {
            for (let k = 0; k < p; k++) {
                const aik = BigInt(a[i][k]);
                if (aik !== BigInt(0)) {
                    for (let j = 0; j < q; j++) {
                        const product = aik * BigInt(b[k][j]);
                        const sum = BigInt(res[i][j]) + product;
                        res[i][j] = Number(sum % MOD);
                    }
                }
            }
        }
        return res;
    };

    const matpow = (mat: number[][], power: number): number[][] => {
        let res: number[][] = Array.from({ length: M }, (_, i) =>
            Array.from({ length: M }, (_, j) => (i === j ? 1 : 0)),
        );
        while (power > 0) {
            if (power % 2 === 1) res = matmul(res, mat);
            mat = matmul(mat, mat);
            power = Math.floor(power / 2);
        }
        return res;
    };

    const cntMat: number[][] = [cnt.slice()];
    const factor = matpow(matrix, t);
    const result = matmul(cntMat, factor);

    let ans = 0n;
    for (const v of result[0]) {
        ans = (ans + BigInt(v)) % MOD;
    }
    return Number(ans);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
