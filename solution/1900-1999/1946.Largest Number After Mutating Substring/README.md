# [1946. 子字符串突变后可能得到的最大整数](https://leetcode.cn/problems/largest-number-after-mutating-substring)

[English Version](/solution/1900-1999/1946.Largest%20Number%20After%20Mutating%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>num</code> ，该字符串表示一个大整数。另给你一个长度为 <code>10</code> 且 <strong>下标从 0&nbsp; 开始</strong> 的整数数组 <code>change</code> ，该数组将 <code>0-9</code> 中的每个数字映射到另一个数字。更规范的说法是，数字 <code>d</code> 映射为数字 <code>change[d]</code> 。</p>

<p>你可以选择 <strong>突变</strong>&nbsp; <code>num</code> 的任一子字符串。<strong>突变</strong> 子字符串意味着将每位数字 <code>num[i]</code> 替换为该数字在 <code>change</code> 中的映射（也就是说，将 <code>num[i]</code> 替换为 <code>change[num[i]]</code>）。</p>

<p>请你找出在对 <code>num</code> 的任一子字符串执行突变操作（也可以不执行）后，可能得到的 <strong>最大整数</strong> ，并用字符串表示返回。</p>

<p><strong>子字符串</strong> 是字符串中的一个连续序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = "<strong><em>1</em></strong>32", change = [9,8,5,0,3,6,4,2,6,8]
<strong>输出：</strong>"<strong><em>8</em></strong>32"
<strong>解释：</strong>替换子字符串 "1"：
- 1 映射为 change[1] = 8 。
因此 "<strong><em>1</em></strong>32" 变为 "<strong><em>8</em></strong>32" 。
"832" 是可以构造的最大整数，所以返回它的字符串表示。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = "<strong><em>021</em></strong>", change = [9,4,3,5,7,2,1,9,0,6]
<strong>输出：</strong>"<strong><em>934</em></strong>"
<strong>解释：</strong>替换子字符串 "021"：
- 0 映射为 change[0] = 9 。
- 2 映射为 change[2] = 3 。
- 1 映射为 change[1] = 4 。
因此，"<strong><em>021</em></strong>" 变为 "<strong><em>934</em></strong>" 。
"934" 是可以构造的最大整数，所以返回它的字符串表示。 
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>num = "5", change = [1,4,7,5,3,2,5,6,9,4]
<strong>输出：</strong>"5"
<strong>解释：</strong>"5" 已经是可以构造的最大整数，所以返回它的字符串表示。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>5</sup></code></li>
	<li><code>num</code> 仅由数字 <code>0-9</code> 组成</li>
	<li><code>change.length == 10</code></li>
	<li><code>0 &lt;= change[d] &lt;= 9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

从左到右遍历字符串 `num`，找到第一个比 `change` 中对应数字小的数字，然后将其替换为 `change` 中对应的数字，直到遇到比 `change` 中对应数字大的数字，停止替换。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 `num` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumNumber(self, num: str, change: List[int]) -> str:
        s = list(num)
        for i, c in enumerate(s):
            if change[int(c)] > int(c):
                while i < len(s) and int(s[i]) <= change[int(s[i])]:
                    s[i] = str(change[int(s[i])])
                    i += 1
                break
        return ''.join(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String maximumNumber(String num, int[] change) {
        char[] s = num.toCharArray();
        for (int i = 0; i < s.length; ++i) {
            if (change[s[i] - '0'] > s[i] - '0') {
                for (; i < s.length && s[i] - '0' <= change[s[i] - '0']; ++i) {
                    s[i] = (char) (change[s[i] - '0'] + '0');
                }
                break;
            }
        }
        return String.valueOf(s);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string maximumNumber(string num, vector<int>& change) {
        int n = num.size();
        for (int i = 0; i < n; ++i) {
            if (change[num[i] - '0'] > num[i] - '0') {
                for (; i < n && change[num[i] - '0'] >= num[i] - '0'; ++i) {
                    num[i] = change[num[i] - '0'] + '0';
                }
                break;
            }
        }
        return num;
    }
};
```

### **Go**

```go
func maximumNumber(num string, change []int) string {
	s := []byte(num)
	for i, c := range num {
		if change[c-'0'] > int(c-'0') {
			for ; i < len(s) && change[s[i]-'0'] >= int(s[i]-'0'); i++ {
				s[i] = byte(change[s[i]-'0']) + '0'
			}
			break
		}
	}
	return string(s)
}
```

### **...**

```

```

<!-- tabs:end -->
