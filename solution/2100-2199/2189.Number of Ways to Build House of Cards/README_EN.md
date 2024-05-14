# [2189. Number of Ways to Build House of Cards 🔒](https://leetcode.com/problems/number-of-ways-to-build-house-of-cards)

[中文文档](/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/README.md)

<!-- tags:Math,Dynamic Programming -->

<!-- difficulty:Medium -->

## Description

<p>You are given an integer <code>n</code> representing the number of playing cards you have. A <strong>house of cards</strong> meets the following conditions:</p>

<ul>
	<li>A <strong>house of cards</strong> consists of one or more rows of <strong>triangles</strong> and horizontal cards.</li>
	<li><strong>Triangles</strong> are created by leaning two cards against each other.</li>
	<li>One card must be placed horizontally between <strong>all adjacent</strong> triangles in a row.</li>
	<li>Any triangle on a row higher than the first must be placed on a horizontal card from the previous row.</li>
	<li>Each triangle is placed in the <strong>leftmost</strong> available spot in the row.</li>
</ul>

<p>Return <em>the number of <strong>distinct</strong> <strong>house of cards</strong> you can build using <strong>all</strong></em> <code>n</code><em> cards.</em> Two houses of cards are considered distinct if there exists a row where the two houses contain a different number of cards.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/images/image-20220227213243-1.png" style="width: 726px; height: 150px;" />
<pre>
<strong>Input:</strong> n = 16
<strong>Output:</strong> 2
<strong>Explanation:</strong> The two valid houses of cards are shown.
The third house of cards in the diagram is not valid because the rightmost triangle on the top row is not placed on top of a horizontal card.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/images/image-20220227213306-2.png" style="width: 96px; height: 80px;" />
<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> The one valid house of cards is shown.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/images/image-20220227213331-3.png" style="width: 330px; height: 85px;" />
<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 0
<strong>Explanation:</strong> The three houses of cards in the diagram are not valid.
The first house of cards needs a horizontal card placed between the two triangles.
The second house of cards uses 5 cards.
The third house of cards uses 2 cards.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def houseOfCards(self, n: int) -> int:
        @cache
        def dfs(n: int, k: int) -> int:
            x = 3 * k + 2
            if x > n:
                return 0
            if x == n:
                return 1
            return dfs(n - x, k + 1) + dfs(n, k + 1)

        return dfs(n, 0)
```

```java
class Solution {
    private Integer[][] f;

    public int houseOfCards(int n) {
        f = new Integer[n + 1][n / 3];
        return dfs(n, 0);
    }

    private int dfs(int n, int k) {
        int x = 3 * k + 2;
        if (x > n) {
            return 0;
        }
        if (x == n) {
            return 1;
        }
        if (f[n][k] != null) {
            return f[n][k];
        }
        return f[n][k] = dfs(n - x, k + 1) + dfs(n, k + 1);
    }
}
```

```cpp
class Solution {
public:
    int houseOfCards(int n) {
        int f[n + 1][n / 3 + 1];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int n, int k) -> int {
            int x = 3 * k + 2;
            if (x > n) {
                return 0;
            }
            if (x == n) {
                return 1;
            }
            if (f[n][k] != -1) {
                return f[n][k];
            }
            return f[n][k] = dfs(n - x, k + 1) + dfs(n, k + 1);
        };
        return dfs(n, 0);
    }
};
```

```go
func houseOfCards(n int) int {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n/3+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(n, k int) int
	dfs = func(n, k int) int {
		x := 3*k + 2
		if x > n {
			return 0
		}
		if x == n {
			return 1
		}
		if f[n][k] == -1 {
			f[n][k] = dfs(n-x, k+1) + dfs(n, k+1)
		}
		return f[n][k]
	}
	return dfs(n, 0)
}
```

```ts
function houseOfCards(n: number): number {
    const f: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(Math.floor(n / 3) + 1).fill(-1));
    const dfs = (n: number, k: number): number => {
        const x = k * 3 + 2;
        if (x > n) {
            return 0;
        }
        if (x === n) {
            return 1;
        }
        if (f[n][k] === -1) {
            f[n][k] = dfs(n - x, k + 1) + dfs(n, k + 1);
        }
        return f[n][k];
    };
    return dfs(n, 0);
}
```

<!-- tabs:end -->

<!-- end -->
