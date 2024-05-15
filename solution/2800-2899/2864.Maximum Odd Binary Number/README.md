---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2864.Maximum%20Odd%20Binary%20Number/README.md
rating: 1237
tags:
    - 贪心
    - 数学
    - 字符串
---

# [2864. 最大二进制奇数](https://leetcode.cn/problems/maximum-odd-binary-number)

[English Version](/solution/2800-2899/2864.Maximum%20Odd%20Binary%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>二进制</strong> 字符串 <code>s</code> ，其中至少包含一个 <code>'1'</code> 。</p>

<p>你必须按某种方式 <strong>重新排列</strong> 字符串中的位，使得到的二进制数字是可以由该组合生成的 <strong>最大二进制奇数</strong> 。</p>

<p>以字符串形式，表示并返回可以由给定组合生成的最大二进制奇数。</p>

<p><strong>注意 </strong>返回的结果字符串 <strong>可以</strong> 含前导零。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "010"
<strong>输出：</strong>"001"
<strong>解释：</strong>因为字符串 s 中仅有一个 '1' ，其必须出现在最后一位上。所以答案是 "001" 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "0101"
<strong>输出：</strong>"1001"
<strong>解释：</strong>其中一个 '1' 必须出现在最后一位上。而由剩下的数字可以生产的最大数字是 "100" 。所以答案是 "1001" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由 <code>'0'</code> 和 <code>'1'</code> 组成</li>
	<li><code>s</code> 中至少包含一个 <code>'1'</code></li>
</ul>

## 解法

### 方法一：贪心

我们先统计字符串 $s$ 中 $1$ 的个数，记为 $cnt$。那么我们将 $cnt - 1$ 个 $1$ 放在最高位，剩下的 $|s| - cnt$ 个 $0$ 放在后面，最后再加上一个 $1$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumOddBinaryNumber(self, s: str) -> str:
        cnt = s.count("1")
        return "1" * (cnt - 1) + (len(s) - cnt) * "0" + "1"
```

```java
class Solution {
    public String maximumOddBinaryNumber(String s) {
        int cnt = s.length() - s.replace("1", "").length();
        return "1".repeat(cnt - 1) + "0".repeat(s.length() - cnt) + "1";
    }
}
```

```cpp
class Solution {
public:
    string maximumOddBinaryNumber(string s) {
        int cnt = count(s.begin(), s.end(), '1');
        return string(cnt - 1, '1') + string(s.size() - cnt, '0') + '1';
    }
};
```

```go
func maximumOddBinaryNumber(s string) string {
	cnt := strings.Count(s, "1")
	return strings.Repeat("1", cnt-1) + strings.Repeat("0", len(s)-cnt) + "1"
}
```

```ts
function maximumOddBinaryNumber(s: string): string {
    const cnt = s.length - s.replace(/1/g, '').length;
    return '1'.repeat(cnt - 1) + '0'.repeat(s.length - cnt) + '1';
}
```

```rust
impl Solution {
    pub fn maximum_odd_binary_number(s: String) -> String {
        let cnt = s
            .chars()
            .filter(|&c| c == '1')
            .count();
        "1".repeat(cnt - 1) + &"0".repeat(s.len() - cnt) + "1"
    }
}
```

<!-- tabs:end -->

<!-- end -->
