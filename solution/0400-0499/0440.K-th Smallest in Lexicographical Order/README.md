---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0440.K-th%20Smallest%20in%20Lexicographical%20Order/README.md
tags:
    - 字典树
---

<!-- problem:start -->

# [440. 字典序的第K小数字](https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order)

[English Version](/solution/0400-0499/0440.K-th%20Smallest%20in%20Lexicographical%20Order/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定整数&nbsp;<code>n</code>&nbsp;和&nbsp;<code>k</code>，返回&nbsp;&nbsp;<code>[1, n]</code>&nbsp;中字典序第&nbsp;<code>k</code>&nbsp;小的数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>n = 13, k = 2
<strong>输出: </strong>10
<strong>解释: </strong>字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 1, k = 1
<strong>输出:</strong> 1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字典树计数 + 贪心构造

本题要求在区间 $[1, n]$ 中，按**字典序**排序后，找到第 $k$ 小的数字。由于 $n$ 的范围非常大（最多可达 $10^9$），我们无法直接枚举所有数字后排序。因此我们采用**贪心 + 字典树模拟**的策略。

我们将 $[1, n]$ 看作一棵 **十叉字典树（Trie）**：

-   每个节点是一个前缀，根节点为空串；
-   节点的子节点是当前前缀拼接上 $0 \sim 9$；
-   例如前缀 $1$ 会有子节点 $10, 11, \ldots, 19$，而 $10$ 会有 $100, 101, \ldots, 109$；
-   这种结构天然符合字典序遍历。

```
根
├── 1
│   ├── 10
│   ├── 11
│   ├── ...
├── 2
├── ...
```

我们使用变量 $\textit{curr}$ 表示当前前缀，初始为 $1$。每次我们尝试向下扩展前缀，直到找到第 $k$ 小的数字。

每次我们计算当前前缀下有多少个合法数字（即以 $\textit{curr}$ 为前缀、且不超过 $n$ 的整数个数），记作 $\textit{count}(\text{curr})$：

-   如果 $k \ge \text{count}(\text{curr})$：说明目标不在这棵子树中，跳过整棵子树，前缀右移：$\textit{curr} \leftarrow \text{curr} + 1$，并更新 $k \leftarrow k - \text{count}(\text{curr})$；
-   否则：说明目标在当前前缀的子树中，进入下一层：$\textit{curr} \leftarrow \text{curr} \times 10$，并消耗一个前缀：$k \leftarrow k - 1$。

每一层我们将当前区间扩大 $10$ 倍，向下延伸到更长的前缀，直到超出 $n$。

时间复杂度 $O(\log^2 n)$，空间复杂度 $O(1)$。

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
