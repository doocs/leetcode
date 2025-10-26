---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2048.Next%20Greater%20Numerically%20Balanced%20Number/README.md
rating: 1734
source: 第 264 场周赛 Q2
tags:
    - 哈希表
    - 数学
    - 回溯
    - 计数
    - 枚举
---

<!-- problem:start -->

# [2048. 下一个更大的数值平衡数](https://leetcode.cn/problems/next-greater-numerically-balanced-number)

[English Version](/solution/2000-2099/2048.Next%20Greater%20Numerically%20Balanced%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果整数&nbsp; <code>x</code> 满足：对于每个数位&nbsp;<code>d</code> ，这个数位&nbsp;<strong>恰好</strong> 在 <code>x</code> 中出现 <code>d</code> 次。那么整数 <code>x</code> 就是一个 <strong>数值平衡数</strong> 。</p>

<p>给你一个整数 <code>n</code> ，请你返回 <strong>严格大于</strong> <code>n</code> 的 <strong>最小数值平衡数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>22
<strong>解释：</strong>
22 是一个数值平衡数，因为：
- 数字 2 出现 2 次 
这也是严格大于 1 的最小数值平衡数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1000
<strong>输出：</strong>1333
<strong>解释：</strong>
1333 是一个数值平衡数，因为：
- 数字 1 出现 1 次。
- 数字 3 出现 3 次。 
这也是严格大于 1000 的最小数值平衡数。
注意，1022 不能作为本输入的答案，因为数字 0 的出现次数超过了 0 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 3000
<strong>输出：</strong>3133
<strong>解释：</strong>
3133 是一个数值平衡数，因为：
- 数字 1 出现 1 次。
- 数字 3 出现 3 次。 
这也是严格大于 3000 的最小数值平衡数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们注意到，题目中 $n$ 的范围是 $[0, 10^6]$，而大于 $10^6$ 的其中一个数值平衡数是 $1224444$，因此我们直接枚举 $x \in [n + 1, ..]$，然后判断 $x$ 是否是数值平衡数即可。枚举的 $x$ 一定不会超过 $1224444$。

时间复杂度 $O(M - n)$，其中 $M = 1224444$。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def nextBeautifulNumber(self, n: int) -> int:
        for x in count(n + 1):
            y = x
            cnt = [0] * 10
            while y:
                y, v = divmod(y, 10)
                cnt[v] += 1
            if all(v == 0 or i == v for i, v in enumerate(cnt)):
                return x
```

#### Java

```java
class Solution {
    public int nextBeautifulNumber(int n) {
        for (int x = n + 1;; ++x) {
            int[] cnt = new int[10];
            for (int y = x; y > 0; y /= 10) {
                ++cnt[y % 10];
            }
            boolean ok = true;
            for (int y = x; y > 0; y /= 10) {
                if (y % 10 != cnt[y % 10]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return x;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int nextBeautifulNumber(int n) {
        for (int x = n + 1;; ++x) {
            int cnt[10]{};
            for (int y = x; y > 0; y /= 10) {
                ++cnt[y % 10];
            }
            bool ok = true;
            for (int y = x; y > 0; y /= 10) {
                if (y % 10 != cnt[y % 10]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return x;
            }
        }
    }
};
```

#### Go

```go
func nextBeautifulNumber(n int) int {
	for x := n + 1; ; x++ {
		cnt := [10]int{}
		for y := x; y > 0; y /= 10 {
			cnt[y%10]++
		}
		ok := true
		for y := x; y > 0; y /= 10 {
			if y%10 != cnt[y%10] {
				ok = false
				break
			}
		}
		if ok {
			return x
		}
	}
}
```

#### TypeScript

```ts
function nextBeautifulNumber(n: number): number {
    for (let x = n + 1; ; ++x) {
        const cnt: number[] = Array(10).fill(0);
        for (let y = x; y > 0; y = (y / 10) | 0) {
            cnt[y % 10]++;
        }
        let ok = true;
        for (let i = 0; i < 10; ++i) {
            if (cnt[i] && cnt[i] !== i) {
                ok = false;
                break;
            }
        }
        if (ok) {
            return x;
        }
    }
}
```

#### Rust

```rust
impl Solution {
    pub fn next_beautiful_number(n: i32) -> i32 {
        let mut x = n + 1;
        loop {
            let mut cnt = [0; 10];
            let mut y = x;
            while y > 0 {
                cnt[(y % 10) as usize] += 1;
                y /= 10;
            }
            let mut ok = true;
            let mut y2 = x;
            while y2 > 0 {
                let d = (y2 % 10) as usize;
                if d != cnt[d] {
                    ok = false;
                    break;
                }
                y2 /= 10;
            }
            if ok {
                return x;
            }
            x += 1;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
