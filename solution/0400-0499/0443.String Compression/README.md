# [443. 压缩字符串](https://leetcode-cn.com/problems/string-compression)

[English Version](/solution/0400-0499/0443.String%20Compression/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一组字符，使用<a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95">原地算法</a>将其压缩。</p>

<p>压缩后的长度必须始终小于或等于原数组长度。</p>

<p>数组的每个元素应该是长度为1&nbsp;的<strong>字符</strong>（不是 int 整数类型）。</p>

<p>在完成<a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95">原地</a><strong>修改输入数组</strong>后，返回数组的新长度。</p>

<p>&nbsp;</p>

<p><strong>进阶：</strong><br>
你能否仅使用O(1) 空间解决问题？</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
[&quot;a&quot;,&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;c&quot;,&quot;c&quot;,&quot;c&quot;]

<strong>输出：</strong>
返回 6 ，输入数组的前 6 个字符应该是：[&quot;a&quot;,&quot;2&quot;,&quot;b&quot;,&quot;2&quot;,&quot;c&quot;,&quot;3&quot;]

<strong>说明：</strong>
&quot;aa&quot; 被 &quot;a2&quot; 替代。&quot;bb&quot; 被 &quot;b2&quot; 替代。&quot;ccc&quot; 被 &quot;c3&quot; 替代。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>
[&quot;a&quot;]

<strong>输出：</strong>
返回 1 ，输入数组的前 1 个字符应该是：[&quot;a&quot;]

<strong>解释：</strong>
没有任何字符串被替代。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>
[&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;]

<strong>输出：</strong>
返回 4 ，输入数组的前4个字符应该是：[&quot;a&quot;,&quot;b&quot;,&quot;1&quot;,&quot;2&quot;]。

<strong>解释：</strong>
由于字符 &quot;a&quot; 不重复，所以不会被压缩。&quot;bbbbbbbbbbbb&quot; 被 &ldquo;b12&rdquo; 替代。
注意每个数字在数组中都有它自己的位置。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>所有字符都有一个ASCII值在<code>[35, 126]</code>区间内。</li>
	<li><code>1 &lt;= len(chars) &lt;= 1000</code>。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def compress(self, chars: List[str]) -> int:
        i, k, n = 0, 0, len(chars)
        while i < n:
            j = i + 1
            while j < n and chars[j] == chars[i]:
                j += 1
            chars[k] = chars[i]
            k += 1
            if j - i > 1:
                cnt = str(j - i)
                for c in cnt:
                    chars[k] = c
                    k += 1
            i = j
        return k
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int compress(char[] chars) {
        int k = 0, n = chars.length;
        for (int i = 0, j = i + 1; i < n;) {
            while (j < n && chars[j] == chars[i]) {
                ++j;
            }
            chars[k++] = chars[i];
            if (j - i > 1) {
                String cnt = String.valueOf(j - i);
                for (char c : cnt.toCharArray()) {
                    chars[k++] = c;
                }
            }
            i = j;
        }
        return k;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int compress(vector<char> &chars) {
        int k = 0, n = chars.size();
        for (int i = 0, j = i + 1; i < n;)
        {
            while (j < n && chars[j] == chars[i])
                ++j;
            chars[k++] = chars[i];
            if (j - i > 1)
            {
                for (char c : to_string(j - i))
                {
                    chars[k++] = c;
                }
            }
            i = j;
        }
        return k;
    }
};
```

### **Go**

```go
func compress(chars []byte) int {
	i, k, n := 0, 0, len(chars)
	for i < n {
		j := i + 1
		for j < n && chars[j] == chars[i] {
			j++
		}
		chars[k] = chars[i]
		k++
		if j-i > 1 {
			cnt := strconv.Itoa(j - i)
			for _, c := range cnt {
				chars[k] = byte(c)
				k++
			}
		}
		i = j
	}
	return k
}
```

### **...**

```

```

<!-- tabs:end -->
