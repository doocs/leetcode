---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1363.Largest%20Multiple%20of%20Three/README_EN.md
rating: 1822
source: Weekly Contest 177 Q4
tags:
    - Greedy
    - Array
    - Dynamic Programming
---

# [1363. Largest Multiple of Three](https://leetcode.com/problems/largest-multiple-of-three)

[中文文档](/solution/1300-1399/1363.Largest%20Multiple%20of%20Three/README.md)

## Description

<p>Given an array of digits <code>digits</code>, return <em>the largest multiple of <strong>three</strong> that can be formed by concatenating some of the given digits in <strong>any order</strong></em>. If there is no answer return an empty string.</p>

<p>Since the answer may not fit in an integer data type, return the answer as a string. Note that the returning answer must not contain unnecessary leading zeros.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> digits = [8,1,9]
<strong>Output:</strong> &quot;981&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> digits = [8,6,7,1,0]
<strong>Output:</strong> &quot;8760&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> digits = [1]
<strong>Output:</strong> &quot;&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= digits.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= digits[i] &lt;= 9</code></li>
</ul>

## Solutions

### Solution 1: Greedy + Dynamic Programming + Backtracking

We define $f[i][j]$ as the maximum length of selecting several numbers from the first $i$ numbers, so that the sum of the selected numbers modulo $3$ equals $j$. To make the selected numbers as large as possible, we need to select as many numbers as possible, so we need to make $f[i][j]$ as large as possible. We initialize $f[0][0] = 0$, and the rest of $f[0][j] = -\infty$.

Consider how $f[i][j]$ transitions. We can choose not to select the $i$-th number, in which case $f[i][j] = f[i - 1][j]$; we can also choose to select the $i$-th number, in which case $f[i][j] = f[i - 1][(j - x_i \bmod 3 + 3) \bmod 3] + 1$, where $x_i$ represents the value of the $i$-th number. Therefore, we have the following state transition equation:

$$
f[i][j] = \max \{ f[i - 1][j], f[i - 1][(j - x_i \bmod 3 + 3) \bmod 3] + 1 \}
$$

If $f[n][0] \le 0$, then we cannot select any number, so the answer string is empty. Otherwise, we can backtrack through the $f$ array to find out the selected numbers.

Define $i = n$, $j = 0$, start backtracking from $f[i][j]$, let $k = (j - x_i \bmod 3 + 3) \bmod 3$, if $f[i - 1][k] + 1 = f[i][j]$, then we have selected the $i$-th number, otherwise we have not selected the $i$-th number. If we have selected the $i$-th number, then we update $j$ to $k$, otherwise we keep $j$ unchanged. To make the number of the same length as large as possible, we should prefer to select larger numbers, so we should sort the array first.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def largestMultipleOfThree(self, digits: List[int]) -> str:
        digits.sort()
        n = len(digits)
        f = [[-inf] * 3 for _ in range(n + 1)]
        f[0][0] = 0
        for i, x in enumerate(digits, 1):
            for j in range(3):
                f[i][j] = max(f[i - 1][j], f[i - 1][(j - x % 3 + 3) % 3] + 1)
        if f[n][0] <= 0:
            return ""
        arr = []
        j = 0
        for i in range(n, 0, -1):
            k = (j - digits[i - 1] % 3 + 3) % 3
            if f[i - 1][k] + 1 == f[i][j]:
                arr.append(digits[i - 1])
                j = k
        i = 0
        while i < len(arr) - 1 and arr[i] == 0:
            i += 1
        return "".join(map(str, arr[i:]))
```

```java
class Solution {
    public String largestMultipleOfThree(int[] digits) {
        Arrays.sort(digits);
        int n = digits.length;
        int[][] f = new int[n + 1][3];
        final int inf = 1 << 30;
        for (var g : f) {
            Arrays.fill(g, -inf);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 3; ++j) {
                f[i][j] = Math.max(f[i - 1][j], f[i - 1][(j - digits[i - 1] % 3 + 3) % 3] + 1);
            }
        }
        if (f[n][0] <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = n, j = 0; i > 0; --i) {
            int k = (j - digits[i - 1] % 3 + 3) % 3;
            if (f[i - 1][k] + 1 == f[i][j]) {
                sb.append(digits[i - 1]);
                j = k;
            }
        }
        int i = 0;
        while (i < sb.length() - 1 && sb.charAt(i) == '0') {
            ++i;
        }
        return sb.substring(i);
    }
}
```

```cpp
class Solution {
public:
    string largestMultipleOfThree(vector<int>& digits) {
        sort(digits.begin(), digits.end());
        int n = digits.size();
        int f[n + 1][3];
        memset(f, -0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 3; ++j) {
                f[i][j] = max(f[i - 1][j], f[i - 1][(j - digits[i - 1] % 3 + 3) % 3] + 1);
            }
        }
        if (f[n][0] <= 0) {
            return "";
        }
        string ans;
        for (int i = n, j = 0; i; --i) {
            int k = (j - digits[i - 1] % 3 + 3) % 3;
            if (f[i - 1][k] + 1 == f[i][j]) {
                ans += digits[i - 1] + '0';
                j = k;
            }
        }
        int i = 0;
        while (i < ans.size() - 1 && ans[i] == '0') {
            ++i;
        }
        return ans.substr(i);
    }
};
```

```go
func largestMultipleOfThree(digits []int) string {
	sort.Ints(digits)
	n := len(digits)
	const inf = 1 << 30
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, 3)
		for j := range f[i] {
			f[i][j] = -inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= n; i++ {
		for j := 0; j < 3; j++ {
			f[i][j] = max(f[i-1][j], f[i-1][(j-digits[i-1]%3+3)%3]+1)
		}
	}
	if f[n][0] <= 0 {
		return ""
	}
	ans := []byte{}
	for i, j := n, 0; i > 0; i-- {
		k := (j - digits[i-1]%3 + 3) % 3
		if f[i][j] == f[i-1][k]+1 {
			ans = append(ans, byte('0'+digits[i-1]))
			j = k
		}
	}
	i := 0
	for i < len(ans)-1 && ans[i] == '0' {
		i++
	}
	return string(ans[i:])
}
```

```ts
function largestMultipleOfThree(digits: number[]): string {
    digits.sort((a, b) => a - b);
    const n = digits.length;
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(3).fill(-Infinity));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < 3; ++j) {
            f[i][j] = Math.max(f[i - 1][j], f[i - 1][(j - (digits[i - 1] % 3) + 3) % 3] + 1);
        }
    }
    if (f[n][0] <= 0) {
        return '';
    }
    const arr: number[] = [];
    for (let i = n, j = 0; i; --i) {
        const k = (j - (digits[i - 1] % 3) + 3) % 3;
        if (f[i - 1][k] + 1 === f[i][j]) {
            arr.push(digits[i - 1]);
            j = k;
        }
    }
    let i = 0;
    while (i < arr.length - 1 && arr[i] === 0) {
        ++i;
    }
    return arr.slice(i).join('');
}
```

<!-- tabs:end -->

<!-- end -->
