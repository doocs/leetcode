---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0728.Self%20Dividing%20Numbers/README.md
tags:
    - 数学
---

<!-- problem:start -->

# [728. 自除数](https://leetcode.cn/problems/self-dividing-numbers)

[English Version](/solution/0700-0799/0728.Self%20Dividing%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p><strong>自除数</strong><em>&nbsp;</em>是指可以被它包含的每一位数整除的数。</p>

<ul>
	<li>例如，<code>128</code> 是一个 <strong>自除数</strong> ，因为&nbsp;<code>128 % 1 == 0</code>，<code>128 % 2 == 0</code>，<code>128 % 8 == 0</code>。</li>
</ul>

<p><strong>自除数</strong> 不允许包含 0 。</p>

<p>给定两个整数&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code> ，返回一个列表，<em>列表的元素是范围&nbsp;<code>[left, right]</code>（包括两个端点）内所有的 <strong>自除数</strong></em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>left = 1, right = 22
<strong>输出：</strong>[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>left = 47, right = 85
<b>输出：</b>[48,55,66,77]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们定义一个函数 $\textit{check}(x)$，用来判断 $x$ 是否是自除数。函数的实现思路如下：

我们用 $y$ 来记录 $x$ 的值，然后不断地对 $y$ 除以 $10$，直到 $y$ 为 $0$。在这个过程中，我们判断 $y$ 的末位是否为 $0$，或者 $x$ 是否不能被 $y$ 的末位整除，如果满足这两个条件中的任意一个，那么 $x$ 就不是自除数，返回 $\text{false}$。否则遍历完所有的位数后，返回 $\text{true}$。

最后，我们遍历区间 $[\textit{left}, \textit{right}]$ 中的所有数，对每个数调用 $\textit{check}(x)$，如果返回 $\text{true}$，那么我们就将这个数加入答案数组中。

时间复杂度 $O(n \times \log_{10} M)$，其中 $n$ 是区间 $[\textit{left}, \textit{right}]$ 中的元素个数，而 $M = \textit{right}$，表示区间中的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def selfDividingNumbers(self, left: int, right: int) -> List[int]:
        def check(x: int) -> bool:
            y = x
            while y:
                if y % 10 == 0 or x % (y % 10):
                    return False
                y //= 10
            return True

        return [x for x in range(left, right + 1) if check(x)]
```

#### Java

```java
class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int x = left; x <= right; ++x) {
            if (check(x)) {
                ans.add(x);
            }
        }
        return ans;
    }

    private boolean check(int x) {
        for (int y = x; y > 0; y /= 10) {
            if (y % 10 == 0 || x % (y % 10) != 0) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> selfDividingNumbers(int left, int right) {
        auto check = [&](int x) -> bool {
            for (int y = x; y; y /= 10) {
                if (y % 10 == 0 || x % (y % 10)) {
                    return false;
                }
            }
            return true;
        };
        vector<int> ans;
        for (int x = left; x <= right; ++x) {
            if (check(x)) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func selfDividingNumbers(left int, right int) (ans []int) {
	check := func(x int) bool {
		for y := x; y > 0; y /= 10 {
			if y%10 == 0 || x%(y%10) != 0 {
				return false
			}
		}
		return true
	}
	for x := left; x <= right; x++ {
		if check(x) {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function selfDividingNumbers(left: number, right: number): number[] {
    const check = (x: number): boolean => {
        for (let y = x; y; y = Math.floor(y / 10)) {
            if (y % 10 === 0 || x % (y % 10) !== 0) {
                return false;
            }
        }
        return true;
    };
    return Array.from({ length: right - left + 1 }, (_, i) => i + left).filter(check);
}
```

#### Rust

```rust
impl Solution {
    pub fn self_dividing_numbers(left: i32, right: i32) -> Vec<i32> {
        fn check(x: i32) -> bool {
            let mut y = x;
            while y > 0 {
                if y % 10 == 0 || x % (y % 10) != 0 {
                    return false;
                }
                y /= 10;
            }
            true
        }

        (left..=right).filter(|&x| check(x)).collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
