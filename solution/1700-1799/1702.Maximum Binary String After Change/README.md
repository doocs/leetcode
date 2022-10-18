# [1702. 修改后的最大二进制字符串](https://leetcode.cn/problems/maximum-binary-string-after-change)

[English Version](/solution/1700-1799/1702.Maximum%20Binary%20String%20After%20Change/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制字符串 <code>binary</code> ，它仅有 <code>0</code> 或者 <code>1</code> 组成。你可以使用下面的操作任意次对它进行修改：</p>

<ul>
	<li>操作 1 ：如果二进制串包含子字符串 <code>"00"</code> ，你可以用 <code>"10"</code> 将其替换。
    <ul>
    	<li>比方说， <code>"<strong>00</strong>010" -> "<strong>10</strong>010"</code></li>
    </ul>
    </li>
    <li>操作 2 ：如果二进制串包含子字符串 <code>"10"</code> ，你可以用 <code>"01"</code> 将其替换。
    <ul>
    	<li>比方说， <code>"000<strong>10</strong>" -> "000<strong>01</strong>"</code></li>
    </ul>
    </li>
</ul>

<p>请你返回执行上述操作任意次以后能得到的 <strong>最大二进制字符串</strong> 。如果二进制字符串 <code>x</code> 对应的十进制数字大于二进制字符串 <code>y</code> 对应的十进制数字，那么我们称二进制字符串<em> </em><code>x</code><em> </em>大于二进制字符串<em> </em><code>y</code><em> </em>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>binary = "000110"
<b>输出：</b>"111011"
<b>解释：</b>一个可行的转换为：
"0001<strong>10</strong>" -> "0001<strong>01</strong>" 
"<strong>00</strong>0101" -> "<strong>10</strong>0101" 
"1<strong>00</strong>101" -> "1<strong>10</strong>101" 
"110<strong>10</strong>1" -> "110<strong>01</strong>1" 
"11<strong>00</strong>11" -> "11<strong>10</strong>11"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>binary = "01"
<b>输出：</b>"01"
<b>解释：</b>"01" 没办法进行任何转换。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= binary.length <= 10<sup>5</sup></code></li>
	<li><code>binary</code> 仅包含 <code>'0'</code> 和 <code>'1'</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：脑筋急转弯**

我们观察发现，操作 2 可以把所有的 $1$ 都移动到字符串的末尾，而操作 1 可以把所有的 `0000..000` 串变为 `111..110`。

因此，要想得到最大的二进制串，我们应该把所有不在开头的 $1$ 移动到字符串末尾，使得字符串变为 `111..11...00..11` 的形式，然后借助操作 1 把中间的 `000..00` 变为 `111..10`。这样我们最终可以得到一个最多包含一个 $0$ 的二进制字符串，这个字符串就是我们要求的最大二进制串。

时间复杂度 $O(n)$，忽略答案的空间消耗，空间复杂度 $O(1)$。其中 $n$ 为字符串 `binary` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumBinaryString(self, binary: str) -> str:
        k = binary.find('0')
        if k == -1:
            return binary
        k += binary[k + 1:].count('0')
        return '1' * k + '0' + '1' * (len(binary) - k - 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String maximumBinaryString(String binary) {
        int k = binary.indexOf('0');
        if (k == -1) {
            return binary;
        }
        int n = binary.length();
        for (int i = k + 1; i < n; ++i) {
            if (binary.charAt(i) == '0') {
                ++k;
            }
        }
        char[] ans = binary.toCharArray();
        Arrays.fill(ans, '1');
        ans[k] = '0';
        return String.valueOf(ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string maximumBinaryString(string binary) {
        int k = binary.find('0');
        if (k == binary.npos) return binary;
        int n = binary.size();
        for (int i = k + 1; i < n; ++i) {
            if (binary[i] == '0') {
                ++k;
            }
        }
        return string(k, '1') + '0' + string(n - k - 1, '1');
    }
};
```

### **Go**

```go
func maximumBinaryString(binary string) string {
	k := strings.IndexByte(binary, '0')
	if k == -1 {
		return binary
	}
	for _, c := range binary[k+1:] {
		if c == '0' {
			k++
		}
	}
	ans := []byte(binary)
	for i := range ans {
		ans[i] = '1'
	}
	ans[k] = '0'
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
