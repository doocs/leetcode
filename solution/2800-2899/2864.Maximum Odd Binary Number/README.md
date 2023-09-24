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

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumOddBinaryNumber(self, s: str) -> str:
        cnt = s.count("1")
        return "1" * (cnt - 1) + (len(s) - cnt) * "0" + "1"
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String maximumOddBinaryNumber(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                ++cnt;
            }
        }
        return "1".repeat(cnt - 1) + "0".repeat(s.length() - cnt) + "1";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string maximumOddBinaryNumber(string s) {
        int cnt = count_if(s.begin(), s.end(), [](char c) { return c == '1'; });
        string ans;
        for (int i = 1; i < cnt; ++i) {
            ans.push_back('1');
        }
        for (int i = 0; i < s.size() - cnt; ++i) {
            ans.push_back('0');
        }
        ans.push_back('1');
        return ans;
    }
};
```

### **Go**

```go
func maximumOddBinaryNumber(s string) string {
	cnt := strings.Count(s, "1")
	return strings.Repeat("1", cnt-1) + strings.Repeat("0", len(s)-cnt) + "1"
}
```

### **TypeScript**

```ts
function maximumOddBinaryNumber(s: string): string {
    let cnt = 0;
    for (const c of s) {
        cnt += c === '1' ? 1 : 0;
    }
    return '1'.repeat(cnt - 1) + '0'.repeat(s.length - cnt) + '1';
}
```

### **...**

```

```

<!-- tabs:end -->
