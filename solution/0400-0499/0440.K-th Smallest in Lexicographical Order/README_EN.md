---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0440.K-th%20Smallest%20in%20Lexicographical%20Order/README_EN.md
tags:
    - Trie
---

<!-- problem:start -->

# [440. K-th Smallest in Lexicographical Order](https://leetcode.com/problems/k-th-smallest-in-lexicographical-order)

[中文文档](/solution/0400-0499/0440.K-th%20Smallest%20in%20Lexicographical%20Order/README.md)

## Description

<!-- description:start -->

<p>Given two integers <code>n</code> and <code>k</code>, return <em>the</em> <code>k<sup>th</sup></code> <em>lexicographically smallest integer in the range</em> <code>[1, n]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 13, k = 2
<strong>Output:</strong> 10
<strong>Explanation:</strong> The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Trie-Based Counting + Greedy Construction

The problem asks for the \$k\$-th smallest number in the range $[1, n]$ when all numbers are sorted in **lexicographical order**. Since $n$ can be as large as $10^9$, we cannot afford to generate and sort all the numbers explicitly. Instead, we adopt a strategy based on **greedy traversal over a conceptual Trie**.

We treat the range $[1, n]$ as a **10-ary prefix tree (Trie)**:

-   Each node represents a numeric prefix, starting from an empty root;
-   Each node has 10 children, corresponding to appending digits $0 \sim 9$;
-   For example, prefix $1$ has children $10, 11, \ldots, 19$, and node $10$ has children $100, 101, \ldots, 109$;
-   This tree naturally reflects lexicographical order traversal.

```
root
├── 1
│   ├── 10
│   ├── 11
│   ├── ...
├── 2
├── ...
```

We use a variable $\textit{curr}$ to denote the current prefix, initialized as $1$. At each step, we try to expand or skip prefixes until we find the \$k\$-th smallest number.

At each step, we calculate how many valid numbers (i.e., numbers $\le n$ with prefix $\textit{curr}$) exist under this prefix subtree. Let this count be $\textit{count}(\text{curr})$:

-   If $k \ge \text{count}(\text{curr})$: the target number is not in this subtree. We skip the entire subtree by moving to the next sibling:

    $$
    \textit{curr} \leftarrow \textit{curr} + 1,\quad k \leftarrow k - \text{count}(\text{curr})
    $$

-   Otherwise: the target is within this subtree. We go one level deeper:

    $$
    \textit{curr} \leftarrow \textit{curr} \times 10,\quad k \leftarrow k - 1
    $$

At each level, we enlarge the current range by multiplying by 10 and continue descending until we exceed $n$.

The time complexity is $O(\log^2 n)$, as we perform logarithmic operations for counting and traversing the Trie structure. The space complexity is $O(1)$ since we only use a few variables to track the current prefix and count.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findKthNumber(self, n: int, k: int) -> int:
        def count(curr):
            next, cnt = curr + 1, 0
            while curr <= n:
                cnt += min(n - curr + 1, next - curr)
                next, curr = next * 10, curr * 10
            return cnt

        curr = 1
        k -= 1
        while k:
            cnt = count(curr)
            if k >= cnt:
                k -= cnt
                curr += 1
            else:
                k -= 1
                curr *= 10
        return curr
```

#### Java

```java
class Solution {
    private int n;

    public int findKthNumber(int n, int k) {
        this.n = n;
        long curr = 1;
        --k;
        while (k > 0) {
            int cnt = count(curr);
            if (k >= cnt) {
                k -= cnt;
                ++curr;
            } else {
                --k;
                curr *= 10;
            }
        }
        return (int) curr;
    }

    public int count(long curr) {
        long next = curr + 1;
        long cnt = 0;
        while (curr <= n) {
            cnt += Math.min(n - curr + 1, next - curr);
            next *= 10;
            curr *= 10;
        }
        return (int) cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int n;

    int findKthNumber(int n, int k) {
        this->n = n;
        --k;
        long long curr = 1;
        while (k) {
            int cnt = count(curr);
            if (k >= cnt) {
                k -= cnt;
                ++curr;
            } else {
                --k;
                curr *= 10;
            }
        }
        return (int) curr;
    }

    int count(long long curr) {
        long long next = curr + 1;
        int cnt = 0;
        while (curr <= n) {
            cnt += min(n - curr + 1, next - curr);
            next *= 10;
            curr *= 10;
        }
        return cnt;
    }
};
```

#### Go

```go
func findKthNumber(n int, k int) int {
	count := func(curr int) int {
		next := curr + 1
		cnt := 0
		for curr <= n {
			cnt += min(n-curr+1, next-curr)
			next *= 10
			curr *= 10
		}
		return cnt
	}
	curr := 1
	k--
	for k > 0 {
		cnt := count(curr)
		if k >= cnt {
			k -= cnt
			curr++
		} else {
			k--
			curr *= 10
		}
	}
	return curr
}
```

#### TypeScript

```ts
function findKthNumber(n: number, k: number): number {
    function count(curr: number): number {
        let next = curr + 1;
        let cnt = 0;
        while (curr <= n) {
            cnt += Math.min(n - curr + 1, next - curr);
            curr *= 10;
            next *= 10;
        }
        return cnt;
    }

    let curr = 1;
    k--;

    while (k > 0) {
        const cnt = count(curr);
        if (k >= cnt) {
            k -= cnt;
            curr += 1;
        } else {
            k -= 1;
            curr *= 10;
        }
    }

    return curr;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_kth_number(n: i32, k: i32) -> i32 {
        fn count(mut curr: i64, n: i32) -> i32 {
            let mut next = curr + 1;
            let mut total = 0;
            let n = n as i64;
            while curr <= n {
                total += std::cmp::min(n - curr + 1, next - curr);
                curr *= 10;
                next *= 10;
            }
            total as i32
        }

        let mut curr = 1;
        let mut k = k - 1;

        while k > 0 {
            let cnt = count(curr as i64, n);
            if k >= cnt {
                k -= cnt;
                curr += 1;
            } else {
                k -= 1;
                curr *= 10;
            }
        }

        curr
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
