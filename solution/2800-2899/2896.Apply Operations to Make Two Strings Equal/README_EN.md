# [2896. Apply Operations to Make Two Strings Equal](https://leetcode.com/problems/apply-operations-to-make-two-strings-equal)

[中文文档](/solution/2800-2899/2896.Apply%20Operations%20to%20Make%20Two%20Strings%20Equal/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> binary strings <code>s1</code> and <code>s2</code>, both of length <code>n</code>, and a positive integer <code>x</code>.</p>

<p>You can perform any of the following operations on the string <code>s1</code> <strong>any</strong> number of times:</p>

<ul>
	<li>Choose two indices <code>i</code> and <code>j</code>, and flip both <code>s1[i]</code> and <code>s1[j]</code>. The cost of this operation is <code>x</code>.</li>
	<li>Choose an index <code>i</code> such that <code>i &lt; n - 1</code> and flip both <code>s1[i]</code> and <code>s1[i + 1]</code>. The cost of this operation is <code>1</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> cost needed to make the strings </em><code>s1</code><em> and </em><code>s2</code><em> equal, or return </em><code>-1</code><em> if it is impossible.</em></p>

<p><strong>Note</strong> that flipping a character means changing it from <code>0</code> to <code>1</code> or vice-versa.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;1100011000&quot;, s2 = &quot;0101001010&quot;, x = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can do the following operations:
- Choose i = 3 and apply the second operation. The resulting string is s1 = &quot;110<u><strong>11</strong></u>11000&quot;.
- Choose i = 4 and apply the second operation. The resulting string is s1 = &quot;1101<strong><u>00</u></strong>1000&quot;.
- Choose i = 0 and j = 8 and apply the first operation. The resulting string is s1 = &quot;<u><strong>0</strong></u>1010010<u><strong>1</strong></u>0&quot; = s2.
The total cost is 1 + 1 + 2 = 4. It can be shown that it is the minimum cost possible.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;10110&quot;, s2 = &quot;00011&quot;, x = 4
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is not possible to make the two strings equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s1.length == s2.length</code></li>
	<li><code>1 &lt;= n, x &lt;= 500</code></li>
	<li><code>s1</code> and <code>s2</code> consist only of the characters <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

**Solution 1: Memoization**

We notice that since each operation reverses two characters, if the number of different characters in the two strings is odd, it is impossible to make them equal, and we directly return $-1$. Otherwise, we store the indices of the different characters in the two strings in an array $idx$, and let $m$ be the length of $idx$.

Next, we design a function $dfs(i, j)$, which represents the minimum cost of reversing the characters in $idx[i..j]$. The answer is $dfs(0, m - 1)$.

The calculation process of the function $dfs(i, j)$ is as follows:

If $i > j$, we do not need to perform any operation, and return $0$.

Otherwise, we consider the two endpoints of the interval $[i, j]$:

-   If we perform the first operation on endpoint $i$, since the cost $x$ is fixed, the optimal choice is to reverse $idx[i]$ and $idx[j]$, and then recursively calculate $dfs(i + 1, j - 1)$, with a total cost of $dfs(i + 1, j - 1) + x$.
-   If we perform the second operation on endpoint $i$, we need to reverse all the characters in $[idx[i]..idx[i + 1]]$, and then recursively calculate $dfs(i + 2, j)$, with a total cost of $dfs(i + 2, j) + idx[i + 1] - idx[i]$.
-   If we perform the second operation on endpoint $j$, we need to reverse all the characters in $[idx[j - 1]..idx[j]]$, and then recursively calculate $dfs(i, j - 2)$, with a total cost of $dfs(i, j - 2) + idx[j] - idx[j - 1]$.

We take the minimum value of the above three operations as the value of $dfs(i, j)$.

To avoid redundant calculations, we can use memoization to record the return value of $dfs(i, j)$ in a 2D array $f$. If $f[i][j]$ is not equal to $-1$, it means that we have already calculated it, so we can directly return $f[i][j]$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the strings.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minOperations(self, s1: str, s2: str, x: int) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i > j:
                return 0
            a = dfs(i + 1, j - 1) + x
            b = dfs(i + 2, j) + idx[i + 1] - idx[i]
            c = dfs(i, j - 2) + idx[j] - idx[j - 1]
            return min(a, b, c)

        n = len(s1)
        idx = [i for i in range(n) if s1[i] != s2[i]]
        m = len(idx)
        if m & 1:
            return -1
        return dfs(0, m - 1)
```

### **Java**

```java
class Solution {
    private List<Integer> idx = new ArrayList<>();
    private Integer[][] f;
    private int x;

    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                idx.add(i);
            }
        }
        int m = idx.size();
        if (m % 2 == 1) {
            return -1;
        }
        this.x = x;
        f = new Integer[m][m];
        return dfs(0, m - 1);
    }

    private int dfs(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        f[i][j] = dfs(i + 1, j - 1) + x;
        f[i][j] = Math.min(f[i][j], dfs(i + 2, j) + idx.get(i + 1) - idx.get(i));
        f[i][j] = Math.min(f[i][j], dfs(i, j - 2) + idx.get(j) - idx.get(j - 1));
        return f[i][j];
    }
}
```

```java
class Solution {
    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        int inf = 50_000;
        int one = inf, two = inf, last = inf;
        int done = 0;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                one = Math.min(one, last);
                last = last + 1;
                two = two + 1;
                continue;
            }
            if (done < n) {
                one = Math.min(two + 1, done + x);
                last = Math.min(two + x, done);
                done = two = inf;
                continue;
            }
            done = Math.min(one + x, last + 1);
            two = one;
            one = last = inf;
            continue;
        }
        return done == inf ? -1 : done;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(string s1, string s2, int x) {
        vector<int> idx;
        for (int i = 0; i < s1.size(); ++i) {
            if (s1[i] != s2[i]) {
                idx.push_back(i);
            }
        }
        int m = idx.size();
        if (m & 1) {
            return -1;
        }
        if (m == 0) {
            return 0;
        }
        int f[m][m];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            f[i][j] = min({dfs(i + 1, j - 1) + x, dfs(i + 2, j) + idx[i + 1] - idx[i], dfs(i, j - 2) + idx[j] - idx[j - 1]});
            return f[i][j];
        };
        return dfs(0, m - 1);
    }
};
```

### **Go**

```go
func minOperations(s1 string, s2 string, x int) int {
	idx := []int{}
	for i := range s1 {
		if s1[i] != s2[i] {
			idx = append(idx, i)
		}
	}
	m := len(idx)
	if m&1 == 1 {
		return -1
	}
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, m)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		f[i][j] = dfs(i+1, j-1) + x
		f[i][j] = min(f[i][j], dfs(i+2, j)+idx[i+1]-idx[i])
		f[i][j] = min(f[i][j], dfs(i, j-2)+idx[j]-idx[j-1])
		return f[i][j]
	}
	return dfs(0, m-1)
}
```

### **TypeScript**

```ts
function minOperations(s1: string, s2: string, x: number): number {
    const idx: number[] = [];
    for (let i = 0; i < s1.length; ++i) {
        if (s1[i] !== s2[i]) {
            idx.push(i);
        }
    }
    const m = idx.length;
    if (m % 2 === 1) {
        return -1;
    }
    if (m === 0) {
        return 0;
    }
    const f: number[][] = Array.from({ length: m }, () => Array.from({ length: m }, () => -1));
    const dfs = (i: number, j: number): number => {
        if (i > j) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        f[i][j] = dfs(i + 1, j - 1) + x;
        f[i][j] = Math.min(f[i][j], dfs(i + 2, j) + idx[i + 1] - idx[i]);
        f[i][j] = Math.min(f[i][j], dfs(i, j - 2) + idx[j] - idx[j - 1]);
        return f[i][j];
    };
    return dfs(0, m - 1);
}
```

### **...**

```

```

<!-- tabs:end -->
