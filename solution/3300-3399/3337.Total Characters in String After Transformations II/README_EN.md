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

### Solution 1

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

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
