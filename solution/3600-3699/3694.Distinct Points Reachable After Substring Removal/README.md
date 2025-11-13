---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3694.Distinct%20Points%20Reachable%20After%20Substring%20Removal/README.md
rating: 1739
source: 第 166 场双周赛 Q3
tags:
    - 哈希表
    - 字符串
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [3694. 删除子字符串后不同的终点](https://leetcode.cn/problems/distinct-points-reachable-after-substring-removal)

[English Version](/solution/3600-3699/3694.Distinct%20Points%20Reachable%20After%20Substring%20Removal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由字符 <code>'U'</code>、<code>'D'</code>、<code>'L'</code> 和 <code>'R'</code> 组成的字符串 <code>s</code>，表示在无限的二维笛卡尔网格上的移动。</p>

<ul>
	<li><code>'U'</code>: 从 <code>(x, y)</code> 移动到 <code>(x, y + 1)</code>。</li>
	<li><code>'D'</code>: 从 <code>(x, y)</code> 移动到 <code>(x, y - 1)</code>。</li>
	<li><code>'L'</code>: 从 <code>(x, y)</code> 移动到 <code>(x - 1, y)</code>。</li>
	<li><code>'R'</code>: 从 <code>(x, y)</code> 移动到 <code>(x + 1, y)</code>。</li>
</ul>

<p>你还得到了一个正整数 <code>k</code>。</p>

<p>你 <strong>必须</strong>&nbsp;选择并移除 <strong>恰好一个</strong> 长度为 <code>k</code> 的连续子字符串 <code>s</code>。然后，从坐标 <code>(0, 0)</code> 开始，按顺序执行剩余的移动。</p>

<p>返回可到达的 <strong>不同</strong>&nbsp;最终坐标的数量。</p>

<p>&nbsp;</p>

<p><strong><strong class="example">示例 1:</strong></strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "LUL", k = 1</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><b>解释：</b></p>

<p>移除长度为 1 的子字符串后，<code>s</code> 可以是 <code>"UL"</code>、<code>"LL"</code> 或 <code>"LU"</code>。执行这些移动后，最终坐标将分别是 <code>(-1, 1)</code>、<code>(-2, 0)</code> 和 <code>(-1, 1)</code>。有两个不同的点 <code>(-1, 1)</code> 和 <code>(-2, 0)</code>，因此答案是 2。</p>
</div>

<p><strong><strong class="example">示例 2:</strong></strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "UDLR", k = 4</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><b>解释：</b></p>

<p>移除长度为 4 的子字符串后，<code>s</code> 只能是空字符串。最终坐标将是 <code>(0, 0)</code>。只有一个不同的点 <code>(0, 0)</code>，因此答案是 1。</p>
</div>

<p><strong><strong class="example">示例 3:</strong></strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "UU", k = 1</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><b>解释：</b></p>

<p>移除长度为 1 的子字符串后，<code>s</code> 变为 <code>"U"</code>，它总是以 <code>(0, 1)</code> 结束，因此只有一个不同的最终坐标。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 只包含 <code>'U'</code>、<code>'D'</code>、<code>'L'</code> 和 <code>'R'</code>。</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 哈希表

我们可以使用前缀和数组来记录每一步移动后的位置变化。 具体地，我们使用两个前缀和数组 $f$ 和 $g$ 分别记录每一步移动后在 $x$ 轴和 $y$ 轴上的位置变化。

初始化 $f[0] = 0$ 和 $g[0] = 0$，表示初始位置为 $(0, 0)$。然后，我们遍历字符串 $s$，对于每个字符：

-   如果字符为 'U'，则 $g[i] = g[i-1] + 1$。
-   如果字符为 'D'，则 $g[i] = g[i-1] - 1$。
-   如果字符为 'L'，则 $f[i] = f[i-1] - 1$。
-   如果字符为 'R'，则 $f[i] = f[i-1] + 1$。

接下来，我们使用一个哈希集合来存储不同的最终坐标。对于每个可能的子字符串移除位置 $i$（从 $k$ 到 $n$），我们计算移除子字符串后的最终坐标 $(a, b)$，其中 $a = f[n] - (f[i] - f[i-k])$，而 $b = g[n] - (g[i] - g[i-k])$。将坐标 $(a, b)$ 添加到哈希集合中。

最后，哈希集合的大小即为不同最终坐标的数量。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def distinctPoints(self, s: str, k: int) -> int:
        n = len(s)
        f = [0] * (n + 1)
        g = [0] * (n + 1)
        x = y = 0
        for i, c in enumerate(s, 1):
            if c == "U":
                y += 1
            elif c == "D":
                y -= 1
            elif c == "L":
                x -= 1
            else:
                x += 1
            f[i] = x
            g[i] = y
        st = set()
        for i in range(k, n + 1):
            a = f[n] - (f[i] - f[i - k])
            b = g[n] - (g[i] - g[i - k])
            st.add((a, b))
        return len(st)
```

#### Java

```java
class Solution {
    public int distinctPoints(String s, int k) {
        int n = s.length();
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];
        int x = 0, y = 0;
        for (int i = 1; i <= n; ++i) {
            char c = s.charAt(i - 1);
            if (c == 'U') {
                ++y;
            } else if (c == 'D') {
                --y;
            } else if (c == 'L') {
                --x;
            } else {
                ++x;
            }
            f[i] = x;
            g[i] = y;
        }
        Set<Long> st = new HashSet<>();
        for (int i = k; i <= n; ++i) {
            int a = f[n] - (f[i] - f[i - k]);
            int b = g[n] - (g[i] - g[i - k]);
            st.add(1L * a * n + b);
        }
        return st.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int distinctPoints(string s, int k) {
        int n = s.size();
        vector<int> f(n + 1), g(n + 1);
        int x = 0, y = 0;
        for (int i = 1; i <= n; ++i) {
            char c = s[i - 1];
            if (c == 'U')
                ++y;
            else if (c == 'D')
                --y;
            else if (c == 'L')
                --x;
            else
                ++x;
            f[i] = x;
            g[i] = y;
        }
        unordered_set<long long> st;
        for (int i = k; i <= n; ++i) {
            int a = f[n] - (f[i] - f[i - k]);
            int b = g[n] - (g[i] - g[i - k]);
            st.insert(1LL * a * n + b);
        }
        return st.size();
    }
};
```

#### Go

```go
func distinctPoints(s string, k int) int {
	n := len(s)
	f := make([]int, n+1)
	g := make([]int, n+1)
	x, y := 0, 0
	for i := 1; i <= n; i++ {
		c := s[i-1]
		if c == 'U' {
			y++
		} else if c == 'D' {
			y--
		} else if c == 'L' {
			x--
		} else {
			x++
		}
		f[i] = x
		g[i] = y
	}
	st := make(map[int64]struct{})
	for i := k; i <= n; i++ {
		a := f[n] - (f[i] - f[i-k])
		b := g[n] - (g[i] - g[i-k])
		key := int64(a)*int64(n) + int64(b)
		st[key] = struct{}{}
	}
	return len(st)
}
```

#### TypeScript

```ts
function distinctPoints(s: string, k: number): number {
    const n = s.length;
    const f = new Array(n + 1).fill(0);
    const g = new Array(n + 1).fill(0);
    let x = 0,
        y = 0;
    for (let i = 1; i <= n; ++i) {
        const c = s[i - 1];
        if (c === 'U') ++y;
        else if (c === 'D') --y;
        else if (c === 'L') --x;
        else ++x;
        f[i] = x;
        g[i] = y;
    }
    const st = new Set<number>();
    for (let i = k; i <= n; ++i) {
        const a = f[n] - (f[i] - f[i - k]);
        const b = g[n] - (g[i] - g[i - k]);
        st.add(a * n + b);
    }
    return st.size;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn distinct_points(s: String, k: i32) -> i32 {
        let n = s.len();
        let mut f = vec![0; n + 1];
        let mut g = vec![0; n + 1];
        let mut x = 0;
        let mut y = 0;
        let bytes = s.as_bytes();
        for i in 1..=n {
            match bytes[i - 1] as char {
                'U' => y += 1,
                'D' => y -= 1,
                'L' => x -= 1,
                _ => x += 1,
            }
            f[i] = x;
            g[i] = y;
        }
        let mut st = HashSet::new();
        let k = k as usize;
        for i in k..=n {
            let a = f[n] - (f[i] - f[i - k]);
            let b = g[n] - (g[i] - g[i - k]);
            st.insert((a as i64) * (n as i64) + (b as i64));
        }
        st.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
