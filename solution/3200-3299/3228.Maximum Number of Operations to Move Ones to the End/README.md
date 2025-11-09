---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3228.Maximum%20Number%20of%20Operations%20to%20Move%20Ones%20to%20the%20End/README.md
rating: 1593
source: 第 407 场周赛 Q3
tags:
    - 贪心
    - 字符串
    - 计数
---

<!-- problem:start -->

# [3228. 将 1 移动到末尾的最大操作次数](https://leetcode.cn/problems/maximum-number-of-operations-to-move-ones-to-the-end)

[English Version](/solution/3200-3299/3228.Maximum%20Number%20of%20Operations%20to%20Move%20Ones%20to%20the%20End/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <span data-keyword="binary-string">二进制字符串</span> <code>s</code>。</p>

<p>你可以对这个字符串执行 <strong>任意次</strong> 下述操作：</p>

<ul>
	<li>选择字符串中的任一下标 <code>i</code>（ <code>i + 1 &lt; s.length</code> ），该下标满足 <code>s[i] == '1'</code> 且 <code>s[i + 1] == '0'</code>。</li>
	<li>将字符 <code>s[i]</code> 向 <strong>右移 </strong>直到它到达字符串的末端或另一个 <code>'1'</code>。例如，对于 <code>s = "010010"</code>，如果我们选择 <code>i = 1</code>，结果字符串将会是 <code>s = "0<strong><u>001</u></strong>10"</code>。</li>
</ul>

<p>返回你能执行的<strong> 最大 </strong>操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "1001101"</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>可以执行以下操作：</p>

<ul>
	<li>选择下标 <code>i = 0</code>。结果字符串为 <code>s = "<u><strong>001</strong></u>1101"</code>。</li>
	<li>选择下标 <code>i = 4</code>。结果字符串为 <code>s = "0011<u><strong>01</strong></u>1"</code>。</li>
	<li>选择下标 <code>i = 3</code>。结果字符串为 <code>s = "001<strong><u>01</u></strong>11"</code>。</li>
	<li>选择下标 <code>i = 2</code>。结果字符串为 <code>s = "00<strong><u>01</u></strong>111"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "00111"</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 为 <code>'0'</code> 或 <code>'1'</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们用一个变量 $\textit{ans}$ 记录答案，用一个变量 $\textit{cnt}$ 记录当前的 $1$ 的个数。

然后我们遍历字符串 $s$，如果当前字符是 $1$，则 $\textit{cnt}$ 加一，否则如果存在前一个字符，且前一个字符是 $1$，那么前面的 $\textit{cnt}$ 个 $1$ 可以往后移动，答案加上 $\textit{cnt}$。

最后返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxOperations(self, s: str) -> int:
        ans = cnt = 0
        for i, c in enumerate(s):
            if c == "1":
                cnt += 1
            elif i and s[i - 1] == "1":
                ans += cnt
        return ans
```

#### Java

```java
class Solution {
    public int maxOperations(String s) {
        int ans = 0, cnt = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++cnt;
            } else if (i > 0 && s.charAt(i - 1) == '1') {
                ans += cnt;
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
    int maxOperations(string s) {
        int ans = 0, cnt = 0;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            if (s[i] == '1') {
                ++cnt;
            } else if (i && s[i - 1] == '1') {
                ans += cnt;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxOperations(s string) (ans int) {
	cnt := 0
	for i, c := range s {
		if c == '1' {
			cnt++
		} else if i > 0 && s[i-1] == '1' {
			ans += cnt
		}
	}
	return
}
```

#### TypeScript

```ts
function maxOperations(s: string): number {
    let [ans, cnt] = [0, 0];
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        if (s[i] === '1') {
            ++cnt;
        } else if (i && s[i - 1] === '1') {
            ans += cnt;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_operations(s: String) -> i32 {
        let mut ans = 0;
        let mut cnt = 0;
        let n = s.len();
        let bytes = s.as_bytes();
        for i in 0..n {
            if bytes[i] == b'1' {
                cnt += 1;
            } else if i > 0 && bytes[i - 1] == b'1' {
                ans += cnt;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
