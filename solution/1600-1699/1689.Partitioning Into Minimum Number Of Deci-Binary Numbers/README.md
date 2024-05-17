---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1689.Partitioning%20Into%20Minimum%20Number%20Of%20Deci-Binary%20Numbers/README.md
rating: 1355
source: 第 219 场周赛 Q2
tags:
    - 贪心
    - 字符串
---

<!-- problem:start -->

# [1689. 十-二进制数的最少数目](https://leetcode.cn/problems/partitioning-into-minimum-number-of-deci-binary-numbers)

[English Version](/solution/1600-1699/1689.Partitioning%20Into%20Minimum%20Number%20Of%20Deci-Binary%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果一个十进制数字不含任何前导零，且每一位上的数字不是 <code>0</code> 就是 <code>1</code> ，那么该数字就是一个 <strong>十-二进制数</strong> 。例如，<code>101</code> 和 <code>1100</code> 都是 <strong>十-二进制数</strong>，而 <code>112</code> 和 <code>3001</code> 不是。</p>

<p>给你一个表示十进制整数的字符串 <code>n</code> ，返回和为 <code>n</code> 的 <strong>十-二进制数 </strong>的最少数目。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = "32"
<strong>输出：</strong>3
<strong>解释：</strong>10 + 11 + 11 = 32
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = "82734"
<strong>输出：</strong>8
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = "27346209830709182346"
<strong>输出：</strong>9
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n.length &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> 仅由数字组成</li>
	<li><code>n</code> 不含任何前导零并总是表示正整数</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

题目等价于找字符串中的最大数。

时间复杂度 $O(n)$，其中 $n$ 为字符串长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minPartitions(self, n: str) -> int:
        return int(max(n))
```

```java
class Solution {
    public int minPartitions(String n) {
        int ans = 0;
        for (int i = 0; i < n.length(); ++i) {
            ans = Math.max(ans, n.charAt(i) - '0');
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minPartitions(string n) {
        int ans = 0;
        for (char& c : n) {
            ans = max(ans, c - '0');
        }
        return ans;
    }
};
```

```go
func minPartitions(n string) (ans int) {
	for _, c := range n {
		if t := int(c - '0'); ans < t {
			ans = t
		}
	}
	return
}
```

```ts
function minPartitions(n: string): number {
    return Math.max(...n.split('').map(d => parseInt(d)));
}
```

```rust
impl Solution {
    pub fn min_partitions(n: String) -> i32 {
        let mut ans = 0;
        for c in n.as_bytes() {
            ans = ans.max((c - b'0') as i32);
        }
        ans
    }
}
```

```c
int minPartitions(char* n) {
    int ans = 0;
    for (int i = 0; n[i]; i++) {
        int v = n[i] - '0';
        if (v > ans) {
            ans = v;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
