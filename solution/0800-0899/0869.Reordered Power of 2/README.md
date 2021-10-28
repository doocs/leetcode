# [869. 重新排序得到 2 的幂](https://leetcode-cn.com/problems/reordered-power-of-2)

[English Version](/solution/0800-0899/0869.Reordered%20Power%20of%202/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定正整数 <code>N</code>&nbsp;，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。</p>

<p>如果我们可以通过上述方式得到&nbsp;2 的幂，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>1
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>10
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>16
<strong>输出：</strong>true
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>24
<strong>输出：</strong>false
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>46
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 10^9</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reorderedPowerOf2(self, n: int) -> bool:
        def convert(n):
            counter = [0] * 10
            while n > 0:
                counter[n % 10] += 1
                n //= 10
            return counter

        i, s = 1, convert(n)
        while i <= 10 ** 9:
            if convert(i) == s:
                return True
            i <<= 1
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean reorderedPowerOf2(int n) {
        String s = convert(n);
        for (int i = 1; i <= Math.pow(10, 9); i <<= 1) {
            if (s.equals(convert(i))) {
                return true;
            }
        }
        return false;
    }

    private String convert(int n) {
        char[] counter = new char[10];
        while (n > 0) {
            ++counter[n % 10];
            n /= 10;
        }
        return new String(counter);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool reorderedPowerOf2(int n) {
        vector<int> s = convert(n);
        for (int i = 1; i <= pow(10, 9); i <<= 1)
            if (s == convert(i)) return true;
        return false;
    }

    vector<int> convert(int n) {
        vector<int> counter(10);
        while (n)
        {
            ++counter[n % 10];
            n /= 10;
        }
        return counter;
    }
};
```

### **Go**

```go
func reorderedPowerOf2(n int) bool {
	convert := func(n int) []byte {
		counter := make([]byte, 10)
		for n > 0 {
			counter[n%10]++
			n /= 10
		}
		return counter
	}

	s := convert(n)
	for i := 1; i <= 1e9; i <<= 1 {
		if bytes.Equal(s, convert(i)) {
			return true
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
