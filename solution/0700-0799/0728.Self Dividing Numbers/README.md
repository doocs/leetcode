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

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def selfDividingNumbers(self, left: int, right: int) -> List[int]:
        return [
            num
            for num in range(left, right + 1)
            if all(i != '0' and num % int(i) == 0 for i in str(num))
        ]
```

#### Java

```java
class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; ++i) {
            if (check(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private boolean check(int num) {
        for (int t = num; t != 0; t /= 10) {
            int x = t % 10;
            if (x == 0 || num % x != 0) {
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
        vector<int> ans;
        for (int i = left; i <= right; ++i)
            if (check(i))
                ans.push_back(i);
        return ans;
    }

    bool check(int num) {
        for (int t = num; t; t /= 10) {
            int x = t % 10;
            if (x == 0 || num % x) return false;
        }
        return true;
    }
};
```

#### Go

```go
func selfDividingNumbers(left int, right int) []int {
	check := func(num int) bool {
		for t := num; t != 0; t /= 10 {
			x := t % 10
			if x == 0 || num%x != 0 {
				return false
			}
		}
		return true
	}

	var ans []int
	for i := left; i <= right; i++ {
		if check(i) {
			ans = append(ans, i)
		}
	}
	return ans
}
```

#### Rust

```rust
impl Solution {
    pub fn self_dividing_numbers(left: i32, right: i32) -> Vec<i32> {
        let mut res = vec![];
        for i in left..=right {
            let mut num = i;
            if (loop {
                if num == 0 {
                    break true;
                }
                let j = num % 10;
                if j == 0 || i % j != 0 {
                    break false;
                }
                num /= 10;
            }) {
                res.push(i);
            }
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
