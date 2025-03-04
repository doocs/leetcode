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

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union-Find

We can assign a number to each pair of couples. Person with number $0$ and $1$ corresponds to couple $0$, person with number $2$ and $3$ corresponds to couple $1$, and so on. In other words, the person corresponding to $row[i]$ has a couple number of $\lfloor \frac{row[i]}{2} \rfloor$.

If there are $k$ pairs of couples who are seated incorrectly with respect to each other, i.e., if $k$ pairs of couples are in the same permutation cycle, it will take $k-1$ swaps for all of them to be seated correctly.

Why? Consider the following: we first adjust the positions of a couple to their correct seats. After this, the problem reduces from $k$ couples to $k-1$ couples. This process continues, and when $k = 1$, the number of swaps required is $0$. Therefore, if $k$ pairs of couples are in the wrong positions, we need $k-1$ swaps.

Thus, we only need to traverse the array once, use union-find to determine how many permutation cycles there are. Suppose there are $x$ cycles, and the size of each cycle (in terms of couple pairs) is $y_1, y_2, \cdots, y_x$. The number of swaps required is $y_1-1 + y_2-1 + \cdots + y_x-1 = y_1 + y_2 + \cdots + y_x - x = n - x$.

The time complexity is $O(n \times \alpha(n))$, and the space complexity is $O(n)$, where $\alpha(n)$ is the inverse Ackermann function, which can be considered a very small constant.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

#### C#

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
