---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1925.Count%20Square%20Sum%20Triples/README.md
rating: 1323
source: 第 56 场双周赛 Q1
tags:
    - 数学
    - 枚举
---

<!-- problem:start -->

# [1925. 统计平方和三元组的数目](https://leetcode.cn/problems/count-square-sum-triples)

[English Version](/solution/1900-1999/1925.Count%20Square%20Sum%20Triples/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个 <strong>平方和三元组</strong> <code>(a,b,c)</code> 指的是满足 <code>a<sup>2</sup> + b<sup>2</sup> = c<sup>2</sup></code> 的 <strong>整数 </strong>三元组 <code>a</code>，<code>b</code> 和 <code>c</code> 。</p>

<p>给你一个整数 <code>n</code> ，请你返回满足<em> </em><code>1 &lt;= a, b, c &lt;= n</code> 的 <strong>平方和三元组</strong> 的数目。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>n = 5
<b>输出：</b>2
<b>解释：</b>平方和三元组为 (3,4,5) 和 (4,3,5) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 10
<b>输出：</b>4
<b>解释：</b>平方和三元组为 (3,4,5)，(4,3,5)，(6,8,10) 和 (8,6,10) 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 250</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们在 $[1, n)$ 的范围内枚举 $a$ 和 $b$，然后计算 $c = \sqrt{a^2 + b^2}$，如果 $c$ 是整数且 $c \leq n$，那么就找到了一个平方和三元组，答案加一。

枚举结束后，返回答案即可。

时间复杂度 $O(n^2)$，其中 $n$ 是给定的整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countTriples(self, n: int) -> int:
        ans = 0
        for a in range(1, n):
            for b in range(1, n):
                x = a * a + b * b
                c = int(sqrt(x))
                if c <= n and c * c == x:
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countTriples(int n) {
        int ans = 0;
        for (int a = 1; a < n; a++) {
            for (int b = 1; b < n; b++) {
                int x = a * a + b * b;
                int c = (int) Math.sqrt(x);
                if (c <= n && c * c == x) {
                    ans++;
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
    int countTriples(int n) {
        int ans = 0;
        for (int a = 1; a < n; ++a) {
            for (int b = 1; b < n; ++b) {
                int x = a * a + b * b;
                int c = static_cast<int>(sqrt(x));
                if (c <= n && c * c == x) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countTriples(n int) (ans int) {
	for a := 1; a < n; a++ {
		for b := 1; b < n; b++ {
			x := a*a + b*b
			c := int(math.Sqrt(float64(x)))
			if c <= n && c*c == x {
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function countTriples(n: number): number {
    let ans = 0;
    for (let a = 1; a < n; a++) {
        for (let b = 1; b < n; b++) {
            const x = a * a + b * b;
            const c = Math.floor(Math.sqrt(x));
            if (c <= n && c * c === x) {
                ans++;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
