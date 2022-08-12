# [1433. 检查一个字符串是否可以打破另一个字符串](https://leetcode.cn/problems/check-if-a-string-can-break-another-string)

[English Version](/solution/1400-1499/1433.Check%20If%20a%20String%20Can%20Break%20Another%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code>&nbsp;，它们长度相等，请你检查是否存在一个&nbsp;<code>s1</code>&nbsp; 的排列可以打破 <code>s2</code>&nbsp;的一个排列，或者是否存在一个&nbsp;<code>s2</code>&nbsp;的排列可以打破 <code>s1</code> 的一个排列。</p>

<p>字符串&nbsp;<code>x</code>&nbsp;可以打破字符串&nbsp;<code>y</code>&nbsp;（两者长度都为&nbsp;<code>n</code>&nbsp;）需满足对于所有&nbsp;<code>i</code>（在&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;之间）都有&nbsp;<code>x[i] &gt;= y[i]</code>（字典序意义下的顺序）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s1 = &quot;abc&quot;, s2 = &quot;xya&quot;
<strong>输出：</strong>true
<strong>解释：</strong>&quot;ayx&quot; 是 s2=&quot;xya&quot; 的一个排列，&quot;abc&quot; 是字符串 s1=&quot;abc&quot; 的一个排列，且 &quot;ayx&quot; 可以打破 &quot;abc&quot; 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s1 = &quot;abe&quot;, s2 = &quot;acd&quot;
<strong>输出：</strong>false 
<strong>解释：</strong>s1=&quot;abe&quot; 的所有排列包括：&quot;abe&quot;，&quot;aeb&quot;，&quot;bae&quot;，&quot;bea&quot;，&quot;eab&quot; 和 &quot;eba&quot; ，s2=&quot;acd&quot; 的所有排列包括：&quot;acd&quot;，&quot;adc&quot;，&quot;cad&quot;，&quot;cda&quot;，&quot;dac&quot; 和 &quot;dca&quot;。然而没有任何 s1 的排列可以打破 s2 的排列。也没有 s2 的排列能打破 s1 的排列。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s1 = &quot;leetcodee&quot;, s2 = &quot;interview&quot;
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s1.length == n</code></li>
	<li><code>s2.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li>所有字符串都只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

将字符串 $s1$, $s2$ 分别进行升序排序。然后同时遍历两个字符串，对应字符进行大小比较。若对于任意 $i∈[0, n)，都有 $s1[i] \le s2[i]$，或者都有 $s1[i] \ge s2[i]$，则存在一个排列可以打破另一个排列。

时间复杂度 $O(nlogn)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkIfCanBreak(self, s1: str, s2: str) -> bool:
        cs1 = sorted(s1)
        cs2 = sorted(s2)
        return all(a >= b for a, b in zip(cs1, cs2)) or all(
            a <= b for a, b in zip(cs1, cs2)
        )
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        Arrays.sort(cs1);
        Arrays.sort(cs2);
        return check(cs1, cs2) || check(cs2, cs1);
    }

    private boolean check(char[] cs1, char[] cs2) {
        for (int i = 0; i < cs1.length; ++i) {
            if (cs1[i] < cs2[i]) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkIfCanBreak(string s1, string s2) {
        sort(s1.begin(), s1.end());
        sort(s2.begin(), s2.end());
        return check(s1, s2) || check(s2, s1);
    }

    bool check(string& s1, string& s2) {
        for (int i = 0; i < s1.size(); ++i) {
            if (s1[i] < s2[i]) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func checkIfCanBreak(s1 string, s2 string) bool {
	cs1 := []byte(s1)
	cs2 := []byte(s2)
	sort.Slice(cs1, func(i, j int) bool { return cs1[i] < cs1[j] })
	sort.Slice(cs2, func(i, j int) bool { return cs2[i] < cs2[j] })
	check := func(cs1, cs2 []byte) bool {
		for i := range cs1 {
			if cs1[i] < cs2[i] {
				return false
			}
		}
		return true
	}
	return check(cs1, cs2) || check(cs2, cs1)
}
```

### **...**

```

```

<!-- tabs:end -->
