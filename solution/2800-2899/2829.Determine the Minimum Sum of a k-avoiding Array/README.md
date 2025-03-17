---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2829.Determine%20the%20Minimum%20Sum%20of%20a%20k-avoiding%20Array/README.md
rating: 1347
source: 第 359 场周赛 Q2
tags:
    - 贪心
    - 数学
---

<!-- problem:start -->

# [2829. k-avoiding 数组的最小总和](https://leetcode.cn/problems/determine-the-minimum-sum-of-a-k-avoiding-array)

[English Version](/solution/2800-2899/2829.Determine%20the%20Minimum%20Sum%20of%20a%20k-avoiding%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>n</code> 和 <code>k</code> 。</p>

<p>对于一个由 <strong>不同</strong> 正整数组成的数组，如果其中不存在任何求和等于 k 的不同元素对，则称其为 <strong>k-avoiding</strong> 数组。</p>

<p>返回长度为 <code>n</code> 的 <strong>k-avoiding</strong> 数组的可能的最小总和。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 5, k = 4
<strong>输出：</strong>18
<strong>解释：</strong>设若 k-avoiding 数组为 [1,2,4,5,6] ，其元素总和为 18 。
可以证明不存在总和小于 18 的 k-avoiding 数组。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2, k = 6
<strong>输出：</strong>3
<strong>解释：</strong>可以构造数组 [1,2] ，其元素总和为 3 。
可以证明不存在总和小于 3 的 k-avoiding 数组。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 模拟

我们从正整数 $i = 1$ 开始，依次判断 $i$ 是否可以加入数组中，如果可以加入，则将 $i$ 加入数组中，累加到答案中，然后将 $k - i$ 置为已访问，表示 $k-i$ 不能加入数组中。循环直到数组长度为 $n$。

时间复杂度 $O(n + k)$，空间复杂度 $O(n + k)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSum(self, n: int, k: int) -> int:
        s, i = 0, 1
        vis = set()
        for _ in range(n):
            while i in vis:
                i += 1
            vis.add(k - i)
            s += i
            i += 1
        return s
```

#### Java

```java
class Solution {
    public int minimumSum(int n, int k) {
        int s = 0, i = 1;
        boolean[] vis = new boolean[n + k + 1];
        while (n-- > 0) {
            while (vis[i]) {
                ++i;
            }
            if (k >= i) {
                vis[k - i] = true;
            }
            s += i++;
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSum(int n, int k) {
        int s = 0, i = 1;
        bool vis[n + k + 1];
        memset(vis, false, sizeof(vis));
        while (n--) {
            while (vis[i]) {
                ++i;
            }
            if (k >= i) {
                vis[k - i] = true;
            }
            s += i++;
        }
        return s;
    }
};
```

#### Go

```go
func minimumSum(n int, k int) int {
	s, i := 0, 1
	vis := make([]bool, n+k+1)
	for ; n > 0; n-- {
		for vis[i] {
			i++
		}
		if k >= i {
			vis[k-i] = true
		}
		s += i
		i++
	}
	return s
}
```

#### TypeScript

```ts
function minimumSum(n: number, k: number): number {
    let s = 0;
    let i = 1;
    const vis: boolean[] = Array(n + k + 1).fill(false);
    while (n--) {
        while (vis[i]) {
            ++i;
        }
        if (k >= i) {
            vis[k - i] = true;
        }
        s += i++;
    }
    return s;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_sum(n: i32, k: i32) -> i32 {
        let (mut s, mut i) = (0, 1);
        let mut vis = std::collections::HashSet::new();

        for _ in 0..n {
            while vis.contains(&i) {
                i += 1;
            }
            vis.insert(k - i);
            s += i;
            i += 1;
        }

        s
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
