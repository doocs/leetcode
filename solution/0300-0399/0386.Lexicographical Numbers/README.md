---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0386.Lexicographical%20Numbers/README.md
tags:
    - 深度优先搜索
    - 字典树
---

<!-- problem:start -->

# [386. 字典序排数](https://leetcode.cn/problems/lexicographical-numbers)

[English Version](/solution/0300-0399/0386.Lexicographical%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> ，按字典序返回范围 <code>[1, n]</code> 内所有整数。</p>

<p>你必须设计一个时间复杂度为 <code>O(n)</code> 且使用 <code>O(1)</code> 额外空间的算法。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 13
<strong>输出：</strong>[1,10,11,12,13,2,3,4,5,6,7,8,9]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>[1,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：迭代

我们首先定义一个变量 $v$，初始时 $v = 1$。然后我们从 $1$ 开始迭代，每次迭代都将 $v$ 添加到答案数组中。然后，如果 $v \times 10 \leq n$，我们将 $v$ 更新为 $v \times 10$；否则，如果 $v \bmod 10 = 9$ 或者 $v + 1 > n$，我们就循环将 $v$ 除以 $10$。循环结束后，我们将 $v$ 加一。继续迭代，直到我们添加了 $n$ 个数到答案数组中。

时间复杂度 $O(n)$，其中 $n$ 是给定的整数 $n$。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lexicalOrder(self, n: int) -> List[int]:
        ans = []
        v = 1
        for _ in range(n):
            ans.append(v)
            if v * 10 <= n:
                v *= 10
            else:
                while v % 10 == 9 or v + 1 > n:
                    v //= 10
                v += 1
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>(n);
        int v = 1;
        for (int i = 0; i < n; ++i) {
            ans.add(v);
            if (v * 10 <= n) {
                v *= 10;
            } else {
                while (v % 10 == 9 || v + 1 > n) {
                    v /= 10;
                }
                ++v;
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
    vector<int> lexicalOrder(int n) {
        vector<int> ans;
        int v = 1;
        for (int i = 0; i < n; ++i) {
            ans.push_back(v);
            if (v * 10 <= n) {
                v *= 10;
            } else {
                while (v % 10 == 9 || v + 1 > n) {
                    v /= 10;
                }
                ++v;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func lexicalOrder(n int) (ans []int) {
	v := 1
	for i := 0; i < n; i++ {
		ans = append(ans, v)
		if v*10 <= n {
			v *= 10
		} else {
			for v%10 == 9 || v+1 > n {
				v /= 10
			}
			v++
		}
	}
	return
}
```

#### TypeScript

```ts
function lexicalOrder(n: number): number[] {
    const ans: number[] = [];
    let v = 1;
    for (let i = 0; i < n; ++i) {
        ans.push(v);
        if (v * 10 <= n) {
            v *= 10;
        } else {
            while (v % 10 === 9 || v === n) {
                v = Math.floor(v / 10);
            }
            ++v;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn lexical_order(n: i32) -> Vec<i32> {
        let mut ans = Vec::with_capacity(n as usize);
        let mut v = 1;
        for _ in 0..n {
            ans.push(v);
            if v * 10 <= n {
                v *= 10;
            } else {
                while v % 10 == 9 || v + 1 > n {
                    v /= 10;
                }
                v += 1;
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number[]}
 */
var lexicalOrder = function (n) {
    const ans = [];
    let v = 1;
    for (let i = 0; i < n; ++i) {
        ans.push(v);
        if (v * 10 <= n) {
            v *= 10;
        } else {
            while (v % 10 === 9 || v === n) {
                v = Math.floor(v / 10);
            }
            ++v;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
