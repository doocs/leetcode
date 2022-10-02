# [777. 在 LR 字符串中交换相邻字符](https://leetcode.cn/problems/swap-adjacent-in-lr-string)

[English Version](/solution/0700-0799/0777.Swap%20Adjacent%20in%20LR%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个由 <code>&#39;L&#39;</code> , <code>&#39;R&#39;</code> 和 <code>&#39;X&#39;</code> 三个字符组成的字符串（例如<code>&quot;RXXLRXRXL&quot;</code>）中进行移动操作。一次移动操作指用一个<code>&quot;LX&quot;</code>替换一个<code>&quot;XL&quot;</code>，或者用一个<code>&quot;XR&quot;</code>替换一个<code>&quot;RX&quot;</code>。现给定起始字符串<code>start</code>和结束字符串<code>end</code>，请编写代码，当且仅当存在一系列移动操作使得<code>start</code>可以转换成<code>end</code>时， 返回<code>True</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 :</strong></p>

<pre><strong>输入:</strong> start = &quot;RXXLRXRXL&quot;, end = &quot;XRLXXRRLX&quot;
<strong>输出:</strong> True
<strong>解释:</strong>
我们可以通过以下几步将start转换成end:
RXXLRXRXL -&gt;
XRXLRXRXL -&gt;
XRLXRXRXL -&gt;
XRLXXRRXL -&gt;
XRLXXRRLX
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= len(start) = len(end) &lt;= 10000</code>。</li>
	<li><code>start</code>和<code>end</code>中的字符串仅限于<code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>和<code>&#39;X&#39;</code>。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

替换操作实际上是将 `L` 往左移动（`L` 左边为 `X` 时才能移动），`R` 往右移动（`R` 右边是 `X` 时才能移动），但 `L` 无法穿过 `R`。所以，如果去掉 `start` 和 `end` 中的所有 `X`，剩下的字符应该是相同的，否则返回 `false`。

双指针遍历 `start` 和 `end`：

-   如果当前字符为 `L` 且 $i\lt j$，那么这个 `L` 无法向右移动，返回 `false`；
-   如果当前字符为 `R` 且 $i\gt j$，那么这个 `R` 无法向左移动，返回 `false`。

如果双指针均遍历到末尾，返回 `true`。

时间复杂度 $O(n)$，其中 $n$ 表示字符串 `start` 或 `end` 的长度。

相似题目：[2337. 移动片段得到字符串](/solution/2300-2399/2337.Move%20Pieces%20to%20Obtain%20a%20String/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canTransform(self, start: str, end: str) -> bool:
        n = len(start)
        i = j = 0
        while 1:
            while i < n and start[i] == 'X':
                i += 1
            while j < n and end[j] == 'X':
                j += 1
            if i >= n and j >= n:
                return True
            if i >= n or j >= n or start[i] != end[j]:
                return False
            if start[i] == 'L' and i < j:
                return False
            if start[i] == 'R' and i > j:
                return False
            i, j = i + 1, j + 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canTransform(String start, String end) {
        int n = start.length();
        int i = 0, j = 0;
        while (true) {
            while (i < n && start.charAt(i) == 'X') {
                ++i;
            }
            while (j < n && end.charAt(j) == 'X') {
                ++j;
            }
            if (i == n && j == n) {
                return true;
            }
            if (i == n || j == n || start.charAt(i) != end.charAt(j)) {
                return false;
            }
            if (start.charAt(i) == 'L' && i < j || start.charAt(i) == 'R' && i > j) {
                return false;
            }
            ++i;
            ++j;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canTransform(string start, string end) {
        int n = start.size();
        int i = 0, j = 0;
        while (true) {
            while (i < n && start[i] == 'X') ++i;
            while (j < n && end[j] == 'X') ++j;
            if (i == n && j == n) return true;
            if (i == n || j == n || start[i] != end[j]) return false;
            if (start[i] == 'L' && i < j) return false;
            if (start[i] == 'R' && i > j) return false;
            ++i;
            ++j;
        }
    }
};
```

### **Go**

```go
func canTransform(start string, end string) bool {
	n := len(start)
	i, j := 0, 0
	for {
		for i < n && start[i] == 'X' {
			i++
		}
		for j < n && end[j] == 'X' {
			j++
		}
		if i == n && j == n {
			return true
		}
		if i == n || j == n || start[i] != end[j] {
			return false
		}
		if start[i] == 'L' && i < j {
			return false
		}
		if start[i] == 'R' && i > j {
			return false
		}
		i, j = i+1, j+1
	}
}
```

### **...**

```

```

<!-- tabs:end -->
