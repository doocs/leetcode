---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0765.Couples%20Holding%20Hands/README_EN.md
tags:
    - Greedy
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Graph
---

<!-- problem:start -->

# [765. Couples Holding Hands](https://leetcode.com/problems/couples-holding-hands)

[中文文档](/solution/0700-0799/0765.Couples%20Holding%20Hands/README.md)

## Description

<p>There are <code>n</code> couples sitting in <code>2n</code> seats arranged in a row and want to hold hands.</p>

<p>The people and seats are represented by an integer array <code>row</code> where <code>row[i]</code> is the ID of the person sitting in the <code>i<sup>th</sup></code> seat. The couples are numbered in order, the first couple being <code>(0, 1)</code>, the second couple being <code>(2, 3)</code>, and so on with the last couple being <code>(2n - 2, 2n - 1)</code>.</p>

<p>Return <em>the minimum number of swaps so that every couple is sitting side by side</em>. A swap consists of choosing any two people, then they stand up and switch seats.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> row = [0,2,1,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We only need to swap the second (row[1]) and third (row[2]) person.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> row = [3,2,0,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> All couples are already seated side by side.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2n == row.length</code></li>
	<li><code>2 &lt;= n &lt;= 30</code></li>
	<li><code>n</code> is even.</li>
	<li><code>0 &lt;= row[i] &lt; 2n</code></li>
	<li>All the elements of <code>row</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minSwapsCouples(self, row: List[int]) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(row) >> 1
        p = list(range(n))
        for i in range(0, len(row), 2):
            a, b = row[i] >> 1, row[i + 1] >> 1
            p[find(a)] = find(b)
        return n - sum(i == find(i) for i in range(n))
```

```java
class Solution {
    private int[] p;

    public int minSwapsCouples(int[] row) {
        int n = row.length >> 1;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < n << 1; i += 2) {
            int a = row[i] >> 1, b = row[i + 1] >> 1;
            p[find(a)] = find(b);
        }
        int ans = n;
        for (int i = 0; i < n; ++i) {
            if (i == find(i)) {
                --ans;
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```cpp
class Solution {
public:
    int minSwapsCouples(vector<int>& row) {
        int n = row.size() / 2;
        int p[n];
        iota(p, p + n, 0);
        function<int(int)> find = [&](int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        for (int i = 0; i < n << 1; i += 2) {
            int a = row[i] >> 1, b = row[i + 1] >> 1;
            p[find(a)] = find(b);
        }
        int ans = n;
        for (int i = 0; i < n; ++i) {
            ans -= i == find(i);
        }
        return ans;
    }
};
```

```go
func minSwapsCouples(row []int) int {
	n := len(row) >> 1
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for i := 0; i < n<<1; i += 2 {
		a, b := row[i]>>1, row[i+1]>>1
		p[find(a)] = find(b)
	}
	ans := n
	for i := range p {
		if find(i) == i {
			ans--
		}
	}
	return ans
}
```

```ts
function minSwapsCouples(row: number[]): number {
    const n = row.length >> 1;
    const p: number[] = Array(n)
        .fill(0)
        .map((_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (let i = 0; i < n << 1; i += 2) {
        const a = row[i] >> 1;
        const b = row[i + 1] >> 1;
        p[find(a)] = find(b);
    }
    let ans = n;
    for (let i = 0; i < n; ++i) {
        if (i === find(i)) {
            --ans;
        }
    }
    return ans;
}
```

```cs
public class Solution {
    private int[] p;

    public int MinSwapsCouples(int[] row) {
        int n = row.Length >> 1;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < n << 1; i += 2) {
            int a = row[i] >> 1;
            int b = row[i + 1] >> 1;
            p[find(a)] = find(b);
        }
        int ans = n;
        for (int i = 0; i < n; ++i) {
            if (p[i] == i) {
                --ans;
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
