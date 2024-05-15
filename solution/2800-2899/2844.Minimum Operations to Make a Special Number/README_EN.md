---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2844.Minimum%20Operations%20to%20Make%20a%20Special%20Number/README_EN.md
rating: 1588
tags:
    - Greedy
    - Math
    - String
    - Enumeration
---

# [2844. Minimum Operations to Make a Special Number](https://leetcode.com/problems/minimum-operations-to-make-a-special-number)

[中文文档](/solution/2800-2899/2844.Minimum%20Operations%20to%20Make%20a%20Special%20Number/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>num</code> representing a non-negative integer.</p>

<p>In one operation, you can pick any digit of <code>num</code> and delete it. Note that if you delete all the digits of <code>num</code>, <code>num</code> becomes <code>0</code>.</p>

<p>Return <em>the <strong>minimum number of operations</strong> required to make</em> <code>num</code> <i>special</i>.</p>

<p>An integer <code>x</code> is considered <strong>special</strong> if it is divisible by <code>25</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;2245047&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Delete digits num[5] and num[6]. The resulting number is &quot;22450&quot; which is special since it is divisible by 25.
It can be shown that 2 is the minimum number of operations required to get a special number.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;2908305&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Delete digits num[3], num[4], and num[6]. The resulting number is &quot;2900&quot; which is special since it is divisible by 25.
It can be shown that 3 is the minimum number of operations required to get a special number.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;10&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> Delete digit num[0]. The resulting number is &quot;0&quot; which is special since it is divisible by 25.
It can be shown that 1 is the minimum number of operations required to get a special number.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 100</code></li>
	<li><code>num</code> only consists of digits <code>&#39;0&#39;</code> through <code>&#39;9&#39;</code>.</li>
	<li><code>num</code> does not contain any leading zeros.</li>
</ul>

## Solutions

### Solution 1: Memoization Search

We notice that an integer $x$ can be divisible by $25$, i.e., $x \bmod 25 = 0$. Therefore, we can design a function $dfs(i, k)$, which represents the minimum number of digits to be deleted to make the number a special number, starting from the $i$th digit of the string $num$, and the current number modulo $25$ is $k$. The answer is $dfs(0, 0)$.

The execution logic of the function $dfs(i, k)$ is as follows:

-   If $i = n$, i.e., all digits of the string $num$ have been processed, then if $k = 0$, the current number can be divisible by $25$, return $0$, otherwise return $n$;
-   Otherwise, the $i$th digit can be deleted, in this case one digit needs to be deleted, i.e., $dfs(i + 1, k) + 1$; if the $i$th digit is not deleted, then the value of $k$ becomes $(k \times 10 + \textit{num}[i]) \bmod 25$, i.e., $dfs(i + 1, (k \times 10 + \textit{num}[i]) \bmod 25)$. Take the minimum of these two.

To prevent repeated calculations, we can use memoization to optimize the time complexity.

The time complexity is $O(n \times 25)$, and the space complexity is $O(n \times 25)$. Here, $n$ is the length of the string $num$.

<!-- tabs:start -->

```python
class Solution:
    def minimumOperations(self, num: str) -> int:
        @cache
        def dfs(i: int, k: int) -> int:
            if i == n:
                return 0 if k == 0 else n
            ans = dfs(i + 1, k) + 1
            ans = min(ans, dfs(i + 1, (k * 10 + int(num[i])) % 25))
            return ans

        n = len(num)
        return dfs(0, 0)
```

```java
class Solution {
    private Integer[][] f;
    private String num;
    private int n;

    public int minimumOperations(String num) {
        n = num.length();
        this.num = num;
        f = new Integer[n][25];
        return dfs(0, 0);
    }

    private int dfs(int i, int k) {
        if (i == n) {
            return k == 0 ? 0 : n;
        }
        if (f[i][k] != null) {
            return f[i][k];
        }
        f[i][k] = dfs(i + 1, k) + 1;
        f[i][k] = Math.min(f[i][k], dfs(i + 1, (k * 10 + num.charAt(i) - '0') % 25));
        return f[i][k];
    }
}
```

```cpp
class Solution {
public:
    int minimumOperations(string num) {
        int n = num.size();
        int f[n][25];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int k) -> int {
            if (i == n) {
                return k == 0 ? 0 : n;
            }
            if (f[i][k] != -1) {
                return f[i][k];
            }
            f[i][k] = dfs(i + 1, k) + 1;
            f[i][k] = min(f[i][k], dfs(i + 1, (k * 10 + num[i] - '0') % 25));
            return f[i][k];
        };
        return dfs(0, 0);
    }
};
```

```go
func minimumOperations(num string) int {
	n := len(num)
	f := make([][25]int, n)
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, k int) int
	dfs = func(i, k int) int {
		if i == n {
			if k == 0 {
				return 0
			}
			return n
		}
		if f[i][k] != -1 {
			return f[i][k]
		}
		f[i][k] = dfs(i+1, k) + 1
		f[i][k] = min(f[i][k], dfs(i+1, (k*10+int(num[i]-'0'))%25))
		return f[i][k]
	}
	return dfs(0, 0)
}
```

```ts
function minimumOperations(num: string): number {
    const n = num.length;
    const f: number[][] = Array.from({ length: n }, () => Array.from({ length: 25 }, () => -1));
    const dfs = (i: number, k: number): number => {
        if (i === n) {
            return k === 0 ? 0 : n;
        }
        if (f[i][k] !== -1) {
            return f[i][k];
        }
        f[i][k] = dfs(i + 1, k) + 1;
        f[i][k] = Math.min(f[i][k], dfs(i + 1, (k * 10 + Number(num[i])) % 25));
        return f[i][k];
    };
    return dfs(0, 0);
}
```

<!-- tabs:end -->

<!-- end -->
