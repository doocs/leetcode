---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3955.Valid%20Binary%20Strings%20With%20Cost%20Limit/README_EN.md
---

<!-- problem:start -->

# [3955. Valid Binary Strings With Cost Limit](https://leetcode.com/problems/valid-binary-strings-with-cost-limit)

[中文文档](/solution/3900-3999/3955.Valid%20Binary%20Strings%20With%20Cost%20Limit/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>n</code> and <code>k</code>.</p>

<p>The <strong>cost</strong> of a binary string <code>s</code> is defined as the sum of all indices <code>i</code> (0-based) such that <code>s[i] == &#39;1&#39;</code>.</p>

<p>A binary string is considered <strong>valid</strong> if:</p>

<ul>
	<li>It does not contain two consecutive <code>&#39;1&#39;</code> characters.</li>
	<li>Its cost is <strong>less than or equal</strong> to <code>k</code>.</li>
</ul>

<p>Return a list of all valid binary strings of length <code>n</code> in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;000&quot;,&quot;010&quot;,&quot;100&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p>The binary strings of length 3 without consecutive <code>&#39;1&#39;</code> characters are:</p>

<ul>
	<li><code>&quot;000&quot;</code> : <code>cost = 0</code></li>
	<li>&quot;<code>100&quot;</code> : <code>cost = 0</code></li>
	<li><code>&quot;010&quot;</code> : <code>cost = 1</code></li>
	<li><code>&quot;001&quot;</code> : <code>cost = 2</code></li>
	<li><code>&quot;101&quot;</code> : <code>cost = 0 + 2 = 2</code></li>
</ul>

<p>Among these, the strings with cost less than or equal to <code>k = 1</code> are <code>&quot;000&quot;</code>, <code>&quot;010&quot;</code> and <code>&quot;100&quot;</code>.</p>

<p>Thus, the valid strings are <code>[&quot;000&quot;, &quot;010&quot;, &quot;100&quot;]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1, k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;0&quot;,&quot;1&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid binary strings of length 1 are <code>&quot;0&quot;</code> and <code>&quot;1&quot;</code>.</p>

<p>Thus the answer is <code>[&quot;0&quot;, &quot;1&quot;]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 12</code></li>
	<li><code>0 &lt;= k &lt;= n * (n - 1) / 2</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We want to generate binary strings of length $n$ that satisfy the following conditions:

- The sum of the positions $i$ (0-indexed) of each `1` does not exceed $k$, which can be expressed as:

$$
\sum_{i \mid s_i = 1} i \le k
$$

- No two `1`s can be adjacent to each other.

Therefore, we design a recursive function $\text{dfs}(i, tot)$, where:

- $i$ represents the current position being processed in the string;
- $tot$ represents the sum of the indices of all `1`s placed so far.

#### Recursive Logic

**1. Base Case (Termination Condition)**

When $i \ge n$, it means a string of length $n$ has been fully constructed. At this point, add the current path to the answer list.

**2. Choosing `0`**

A `0` can always be placed at the current position. We recursively call $\text{dfs}(i + 1, tot)$. Since a `0` is placed, the total sum $tot$ remains unchanged.

**3. Choosing `1`**

A `1` can be placed at the current position only if both of the following conditions are met: the previous character does not exist (or is `0`), and $tot + i \le k$. In this case, we recursively call $\text{dfs}(i + 1, tot + i)$.

**4. Backtracking**

After each recursive call returns, we undo the current choice to restore the state before entering the recursion, allowing the algorithm to explore other possible combinations.

The time complexity is $O(n \times 2^n)$, and the space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def generateValidStrings(self, n: int, k: int) -> list[str]:
        def dfs(i: int, tot: int):
            if i >= n:
                ans.append("".join(path))
                return
            path.append("0")
            dfs(i + 1, tot)
            path.pop()
            if (not path or path[-1] == "0") and tot + i <= k:
                path.append("1")
                dfs(i + 1, tot + i)
                path.pop()

        ans = []
        path = []
        dfs(0, 0)
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private int k;
    private List<String> ans;
    private StringBuilder path;

    public List<String> generateValidStrings(int n, int k) {
        this.n = n;
        this.k = k;
        ans = new ArrayList<>();
        path = new StringBuilder();

        dfs(0, 0);

        return ans;
    }

    private void dfs(int i, int tot) {
        if (i >= n) {
            ans.add(path.toString());
            return;
        }

        path.append('0');
        dfs(i + 1, tot);
        path.deleteCharAt(path.length() - 1);

        if ((path.isEmpty() || path.charAt(path.length() - 1) == '0') && tot + i <= k) {
            path.append('1');
            dfs(i + 1, tot + i);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> generateValidStrings(int n, int k) {
        vector<string> ans;
        string path;

        auto dfs = [&](this auto&& dfs, int i, int tot) -> void {
            if (i >= n) {
                ans.push_back(path);
                return;
            }

            path.push_back('0');
            dfs(i + 1, tot);
            path.pop_back();

            if ((path.empty() || path.back() == '0') && tot + i <= k) {
                path.push_back('1');
                dfs(i + 1, tot + i);
                path.pop_back();
            }
        };

        dfs(0, 0);

        return ans;
    }
};
```

#### Go

```go
func generateValidStrings(n int, k int) []string {
	ans := []string{}
	path := make([]byte, 0, n)

	var dfs func(int, int)
	dfs = func(i, tot int) {
		if i >= n {
			ans = append(ans, string(path))
			return
		}

		path = append(path, '0')
		dfs(i+1, tot)
		path = path[:len(path)-1]

		if (len(path) == 0 || path[len(path)-1] == '0') && tot+i <= k {
			path = append(path, '1')
			dfs(i+1, tot+i)
			path = path[:len(path)-1]
		}
	}

	dfs(0, 0)

	return ans
}
```

#### TypeScript

```ts
function generateValidStrings(n: number, k: number): string[] {
    const ans: string[] = [];
    const path: string[] = [];

    const dfs = (i: number, tot: number): void => {
        if (i >= n) {
            ans.push(path.join(''));
            return;
        }

        path.push('0');
        dfs(i + 1, tot);
        path.pop();

        if ((path.length === 0 || path[path.length - 1] === '0') && tot + i <= k) {
            path.push('1');
            dfs(i + 1, tot + i);
            path.pop();
        }
    };

    dfs(0, 0);

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
