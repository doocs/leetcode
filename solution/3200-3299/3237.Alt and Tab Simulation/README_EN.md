---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3237.Alt%20and%20Tab%20Simulation/README_EN.md
---

<!-- problem:start -->

# [3237. Alt and Tab Simulation 🔒](https://leetcode.com/problems/alt-and-tab-simulation)

[中文文档](/solution/3200-3299/3237.Alt%20and%20Tab%20Simulation/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> windows open numbered from <code>1</code> to <code>n</code>, we want to simulate using alt + tab to navigate between the windows.</p>

<p>You are given an array <code>windows</code> which contains the initial order of the windows (the first element is at the top and the last one is at the bottom).</p>

<p>You are also given an array <code>queries</code> where for each query, the window <code>queries[i]</code> is brought to the top.</p>

<p>Return the final state of the array <code>windows</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">windows = [1,2,3], queries = [3,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,3,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Here is the window array after each query:</p>

<ul>
	<li>Initial order: <code>[1,2,3]</code></li>
	<li>After the first query: <code>[<u><strong>3</strong></u>,2,1]</code></li>
	<li>After the second query: <code>[<u><strong>3</strong></u>,2,1]</code></li>
	<li>After the last query: <code>[<u><strong>2</strong></u>,3,1]</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">windows = [1,4,2,3], queries = [4,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,1,4,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>Here is the window array after each query:</p>

<ul>
	<li>Initial order: <code>[1,4,2,3]</code></li>
	<li>After the first query: <code>[<u><strong>4</strong></u>,1,2,3]</code></li>
	<li>After the second query: <code>[<u><strong>1</strong></u>,4,2,3]</code></li>
	<li>After the last query: <code>[<u><strong>3</strong></u>,1,4,2]</code></li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == windows.length &lt;= 10<sup>5</sup></code></li>
	<li><code>windows</code> is a permutation of <code>[1, n]</code>.</li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries[i] &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Reverse Traversal

According to the problem description, the later the query, the earlier it appears in the result. Therefore, we can traverse the $\textit{queries}$ array in reverse order, using a hash table $\textit{s}$ to record the windows that have already appeared. For each query, if the current window is not in the hash table, we add it to the answer array and also add it to the hash table. Finally, we traverse the $\textit{windows}$ array again, adding the windows that are not in the hash table to the answer array.

The time complexity is $O(n + m)$, and the space complexity is $O(m)$. Here, $n$ and $m$ are the lengths of the $\textit{windows}$ and $\textit{queries}$ arrays, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def simulationResult(self, windows: List[int], queries: List[int]) -> List[int]:
        s = set()
        ans = []
        for q in queries[::-1]:
            if q not in s:
                ans.append(q)
                s.add(q)
        for w in windows:
            if w not in s:
                ans.append(w)
        return ans
```

#### Java

```java
class Solution {
    public int[] simulationResult(int[] windows, int[] queries) {
        int n = windows.length;
        boolean[] s = new boolean[n + 1];
        int[] ans = new int[n];
        int k = 0;
        for (int i = queries.length - 1; i >= 0; --i) {
            int q = queries[i];
            if (!s[q]) {
                ans[k++] = q;
                s[q] = true;
            }
        }
        for (int w : windows) {
            if (!s[w]) {
                ans[k++] = w;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> simulationResult(vector<int>& windows, vector<int>& queries) {
        int n = windows.size();
        vector<bool> s(n + 1);
        vector<int> ans;
        for (int i = queries.size() - 1; ~i; --i) {
            int q = queries[i];
            if (!s[q]) {
                s[q] = true;
                ans.push_back(q);
            }
        }
        for (int w : windows) {
            if (!s[w]) {
                ans.push_back(w);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func simulationResult(windows []int, queries []int) (ans []int) {
	n := len(windows)
	s := make([]bool, n+1)
	for i := len(queries) - 1; i >= 0; i-- {
		q := queries[i]
		if !s[q] {
			s[q] = true
			ans = append(ans, q)
		}
	}
	for _, w := range windows {
		if !s[w] {
			ans = append(ans, w)
		}
	}
	return
}
```

#### TypeScript

```ts
function simulationResult(windows: number[], queries: number[]): number[] {
    const n = windows.length;
    const s: boolean[] = Array(n + 1).fill(false);
    const ans: number[] = [];
    for (let i = queries.length - 1; i >= 0; i--) {
        const q = queries[i];
        if (!s[q]) {
            s[q] = true;
            ans.push(q);
        }
    }
    for (const w of windows) {
        if (!s[w]) {
            ans.push(w);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
