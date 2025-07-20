---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0873.Length%20of%20Longest%20Fibonacci%20Subsequence/README.md
tags:
    - 数组
    - 哈希表
    - 动态规划
---

<!-- problem:start -->

# [873. 最长的斐波那契子序列的长度](https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence)

[English Version](/solution/0800-0899/0873.Length%20of%20Longest%20Fibonacci%20Subsequence/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果序列&nbsp;<code>x<sub>1</sub>, x<sub>2</sub>, ..., x<sub>2</sub></code>&nbsp;满足下列条件，就说它是&nbsp;<em>斐波那契式&nbsp;</em>的：</p>

<ul>
	<li><code>n &gt;= 3</code></li>
	<li>对于所有&nbsp;<code>i + 2 &lt;= n</code>，都有&nbsp;<code>x<sub>i</sub>&nbsp;+ x<sub>i+1</sub>&nbsp;== x<sub>i+2</sub></code></li>
</ul>

<p>给定一个&nbsp;<strong>严格递增&nbsp;</strong>的正整数数组形成序列 <code>arr</code>&nbsp;，找到 <code><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.600000381469727px"><span style="caret-color:#c7254e"><span style="background-color:#f9f2f4">arr</span></span></span></font></font></code>&nbsp;中最长的斐波那契式的子序列的长度。如果不存在，返回&nbsp;&nbsp;<code>0</code> 。</p>

<p><strong>子序列</strong> 是通过从另一个序列 <code>arr</code> 中删除任意数量的元素（包括删除 0 个元素）得到的，同时不改变剩余元素顺序。例如，<code>[3, 5, 8]</code> 是 <code>[3, 4, 5, 6, 7, 8]</code>&nbsp;的子序列。</p>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>arr =<strong> </strong>[1,2,3,4,5,6,7,8]
<strong>输出: </strong>5
<strong>解释: </strong>最长的斐波那契式子序列为 [1,2,3,5,8] 。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入: </strong>arr =<strong> </strong>[1,3,7,11,12,14,18]
<strong>输出: </strong>3
<strong>解释</strong>: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 1000</code></li>
	<li>
	<p><code>1 &lt;= arr[i] &lt; arr[i + 1] &lt;= 10<sup>9</sup></code></p>
	</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示以 $\textit{arr}[i]$ 作为最后一个元素，以 $\textit{arr}[j]$ 作为倒数第二个元素的最长斐波那契子序列的长度。初始时，对于任意的 $i \in [0, n)$ 和 $j \in [0, i)$，都有 $f[i][j] = 2$。其余的元素都是 $0$。

我们用一个哈希表 $d$ 记录数组 $\textit{arr}$ 中每个元素对应的下标。

然后，我们可以枚举 $\textit{arr}[i]$ 和 $\textit{arr}[j]$，其中 $i \in [2, n)$ 且 $j \in [1, i)$。假设当前枚举到的元素是 $\textit{arr}[i]$ 和 $\textit{arr}[j]$，我们可以得到 $\textit{arr}[i] - \textit{arr}[j]$，记作 $t$。如果 $t$ 在数组 $\textit{arr}$ 中，且 $t$ 的下标 $k$ 满足 $k < j$，那么我们可以得到一个以 $\textit{arr}[j]$ 和 $\textit{arr}[i]$ 作为最后两个元素的斐波那契子序列，其长度为 $f[i][j] = \max(f[i][j], f[j][k] + 1)$。我们可以用这种方法不断更新 $f[i][j]$ 的值，然后更新答案。

枚举结束后，返回答案即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是数组 $\textit{arr}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lenLongestFibSubseq(self, arr: List[int]) -> int:
        n = len(arr)
        f = [[0] * n for _ in range(n)]
        d = {x: i for i, x in enumerate(arr)}
        for i in range(n):
            for j in range(i):
                f[i][j] = 2
        ans = 0
        for i in range(2, n):
            for j in range(1, i):
                t = arr[i] - arr[j]
                if t in d and (k := d[t]) < j:
                    f[i][j] = max(f[i][j], f[j][k] + 1)
                    ans = max(ans, f[i][j])
        return ans
```

#### Java

```java
class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int[][] f = new int[n][n];
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            d.put(arr[i], i);
            for (int j = 0; j < i; ++j) {
                f[i][j] = 2;
            }
        }
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            for (int j = 1; j < i; ++j) {
                int t = arr[i] - arr[j];
                Integer k = d.get(t);
                if (k != null && k < j) {
                    f[i][j] = Math.max(f[i][j], f[j][k] + 1);
                    ans = Math.max(ans, f[i][j]);
                }
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
    int lenLongestFibSubseq(vector<int>& arr) {
        int n = arr.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        unordered_map<int, int> d;
        for (int i = 0; i < n; ++i) {
            d[arr[i]] = i;
            for (int j = 0; j < i; ++j) {
                f[i][j] = 2;
            }
        }

        int ans = 0;
        for (int i = 2; i < n; ++i) {
            for (int j = 1; j < i; ++j) {
                int t = arr[i] - arr[j];
                auto it = d.find(t);
                if (it != d.end() && it->second < j) {
                    int k = it->second;
                    f[i][j] = max(f[i][j], f[j][k] + 1);
                    ans = max(ans, f[i][j]);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func lenLongestFibSubseq(arr []int) (ans int) {
	n := len(arr)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}

	d := make(map[int]int)
	for i, x := range arr {
		d[x] = i
		for j := 0; j < i; j++ {
			f[i][j] = 2
		}
	}

	for i := 2; i < n; i++ {
		for j := 1; j < i; j++ {
			t := arr[i] - arr[j]
			if k, ok := d[t]; ok && k < j {
				f[i][j] = max(f[i][j], f[j][k]+1)
				ans = max(ans, f[i][j])
			}
		}
	}

	return
}
```

#### TypeScript

```ts
function lenLongestFibSubseq(arr: number[]): number {
    const n = arr.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    const d: Map<number, number> = new Map();
    for (let i = 0; i < n; ++i) {
        d.set(arr[i], i);
        for (let j = 0; j < i; ++j) {
            f[i][j] = 2;
        }
    }
    let ans = 0;
    for (let i = 2; i < n; ++i) {
        for (let j = 1; j < i; ++j) {
            const t = arr[i] - arr[j];
            const k = d.get(t);
            if (k !== undefined && k < j) {
                f[i][j] = Math.max(f[i][j], f[j][k] + 1);
                ans = Math.max(ans, f[i][j]);
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;
impl Solution {
    pub fn len_longest_fib_subseq(arr: Vec<i32>) -> i32 {
        let n = arr.len();
        let mut f = vec![vec![0; n]; n];
        let mut d = HashMap::new();
        for i in 0..n {
            d.insert(arr[i], i);
            for j in 0..i {
                f[i][j] = 2;
            }
        }
        let mut ans = 0;
        for i in 2..n {
            for j in 1..i {
                let t = arr[i] - arr[j];
                if let Some(&k) = d.get(&t) {
                    if k < j {
                        f[i][j] = f[i][j].max(f[j][k] + 1);
                        ans = ans.max(f[i][j]);
                    }
                }
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} arr
 * @return {number}
 */
var lenLongestFibSubseq = function (arr) {
    const n = arr.length;
    const f = Array.from({ length: n }, () => Array(n).fill(0));
    const d = new Map();
    for (let i = 0; i < n; ++i) {
        d.set(arr[i], i);
        for (let j = 0; j < i; ++j) {
            f[i][j] = 2;
        }
    }
    let ans = 0;
    for (let i = 2; i < n; ++i) {
        for (let j = 1; j < i; ++j) {
            const t = arr[i] - arr[j];
            const k = d.get(t);
            if (k !== undefined && k < j) {
                f[i][j] = Math.max(f[i][j], f[j][k] + 1);
                ans = Math.max(ans, f[i][j]);
            }
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
