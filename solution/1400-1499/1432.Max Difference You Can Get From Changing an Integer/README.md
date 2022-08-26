# [1432. 改变一个整数能得到的最大差值](https://leetcode.cn/problems/max-difference-you-can-get-from-changing-an-integer)

[English Version](/solution/1400-1499/1432.Max%20Difference%20You%20Can%20Get%20From%20Changing%20an%20Integer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>num</code>&nbsp;。你可以对它进行如下步骤恰好 <strong>两次</strong>&nbsp;：</p>

<ul>
	<li>选择一个数字&nbsp;<code>x (0&nbsp;&lt;= x &lt;= 9)</code>.</li>
	<li>选择另一个数字&nbsp;<code>y (0&nbsp;&lt;= y &lt;= 9)</code>&nbsp;。数字&nbsp;<code>y</code>&nbsp;可以等于&nbsp;<code>x</code>&nbsp;。</li>
	<li>将 <code>num</code>&nbsp;中所有出现 <code>x</code>&nbsp;的数位都用 <code>y</code>&nbsp;替换。</li>
	<li>得到的新的整数 <strong>不能</strong>&nbsp;有前导 0 ，得到的新整数也 <strong>不能</strong>&nbsp;是 0&nbsp;。</li>
</ul>

<p>令两次对 <code>num</code>&nbsp;的操作得到的结果分别为&nbsp;<code>a</code>&nbsp;和&nbsp;<code>b</code>&nbsp;。</p>

<p>请你返回&nbsp;<code>a</code> 和&nbsp;<code>b</code>&nbsp;的 <strong>最大差值</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = 555
<strong>输出：</strong>888
<strong>解释：</strong>第一次选择 x = 5 且 y = 9 ，并把得到的新数字保存在 a 中。
第二次选择 x = 5 且 y = 1 ，并把得到的新数字保存在 b 中。
现在，我们有 a = 999 和 b = 111 ，最大差值为 888
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = 9
<strong>输出：</strong>8
<strong>解释：</strong>第一次选择 x = 9 且 y = 9 ，并把得到的新数字保存在 a 中。
第二次选择 x = 9 且 y = 1 ，并把得到的新数字保存在 b 中。
现在，我们有 a = 9 和 b = 1 ，最大差值为 8
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>num = 123456
<strong>输出：</strong>820000
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>num = 10000
<strong>输出：</strong>80000
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>num = 9288
<strong>输出：</strong>8700
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10^8</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

要想得到最大差值，那么我们应该拿到最大值与最小值，这样差值最大。

从高到低枚举 `nums` 每个位置上的数，如果数字不为 `9`，就将所有该数字替换为 `9`，得到最大整数 $a$。

从高到低枚举 `nums` 每个位置上的数，首位不能为 `0`，因此如果首位不为 `1`，我们将其替换为 `1`；如果非首位，且数字不与首位相同，我们将其替换为 `0`。得到最大整数 $b$。

答案为差值 $a - b$。

时间复杂度 $O(log(num))$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxDiff(self, num: int) -> int:
        a, b = str(num), str(num)
        for c in a:
            if c != '9':
                a = a.replace(c, '9')
                break
        for i, c in enumerate(b):
            if i == 0:
                if c != '1':
                    b = b.replace(c, '1')
                    break
            else:
                if c != '0' and c != b[0]:
                    b = b.replace(c, '0')
                    break
        return int(a) - int(b)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxDiff(int num) {
        String a = String.valueOf(num);
        String b = String.valueOf(num);
        for (char c : a.toCharArray()) {
            if (c != '9') {
                a = a.replaceAll(String.valueOf(c), "9");
                break;
            }
        }
        for (int i = 0; i < b.length(); ++i) {
            char c = b.charAt(i);
            if (i == 0) {
                if (c != '1') {
                    b = b.replaceAll(String.valueOf(c), "1");
                    break;
                }
            } else {
                if (c != '0' && c != b.charAt(0)) {
                    b = b.replaceAll(String.valueOf(c), "0");
                    break;
                }
            }
        }
        return Integer.parseInt(a) - Integer.parseInt(b);
    }
}
```

### **Go**

```go
func maxDiff(num int) int {
	a, b := num, num
	s := strconv.Itoa(num)
	for i := range s {
		if s[i] != '9' {
			a, _ = strconv.Atoi(strings.ReplaceAll(s, string(s[i]), "9"))
			break
		}
	}
	if s[0] > '1' {
		b, _ = strconv.Atoi(strings.ReplaceAll(s, string(s[0]), "1"))
	} else {
		for i := 1; i < len(s); i++ {
			if s[i] != '0' && s[i] != s[0] {
				b, _ = strconv.Atoi(strings.ReplaceAll(s, string(s[i]), "0"))
				break
			}
		}
	}
	return a - b
}
```

### **...**

```

```

<!-- tabs:end -->
