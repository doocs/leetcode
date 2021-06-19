# [1239. 串联字符串的最大长度](https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters)

[English Version](/solution/1200-1299/1239.Maximum%20Length%20of%20a%20Concatenated%20String%20with%20Unique%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串数组 <code>arr</code>，字符串 <code>s</code> 是将 <code>arr</code> 某一子序列字符串连接所得的字符串，如果 <code>s</code> 中的每一个字符都只出现过一次，那么它就是一个可行解。</p>

<p>请返回所有可行解 <code>s</code> 中最长长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [&quot;un&quot;,&quot;iq&quot;,&quot;ue&quot;]
<strong>输出：</strong>4
<strong>解释：</strong>所有可能的串联组合是 &quot;&quot;,&quot;un&quot;,&quot;iq&quot;,&quot;ue&quot;,&quot;uniq&quot; 和 &quot;ique&quot;，最大长度为 4。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [&quot;cha&quot;,&quot;r&quot;,&quot;act&quot;,&quot;ers&quot;]
<strong>输出：</strong>6
<strong>解释：</strong>可能的解答有 &quot;chaers&quot; 和 &quot;acters&quot;。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [&quot;abcdefghijklmnopqrstuvwxyz&quot;]
<strong>输出：</strong>26
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 16</code></li>
	<li><code>1 &lt;= arr[i].length &lt;= 26</code></li>
	<li><code>arr[i]</code>&nbsp;中只含有小写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

状态压缩，用一个 32 位数记录字母的出现情况，`masks` 存储之前枚举的字符串。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxLength(self, arr: List[str]) -> int:
        def ones_count(x):
            c = 0
            while x:
                x &= x - 1
                c += 1
            return c

        ans = 0
        masks = [0]
        for s in arr:
            mask = 0
            for ch in s:
                ch = ord(ch) - ord('a')
                if (mask >> ch) & 1 == 1:
                    mask = 0
                    break
                mask |= 1 << ch
            if mask == 0:
                continue
            for m in masks:
                if m & mask == 0:
                    masks.append(m | mask)
                    ans = max(ans, ones_count(m | mask))

        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxLength(List<String> arr) {
        int ans = 0;
        List<Integer> masks = new ArrayList<>();
        masks.add(0);

    loop:
        for (String s : arr) {
            int mask = 0;
            for (char ch : s.toCharArray()) {
                ch -= 'a';
                if (((mask >> ch) & 1) == 1) {
                    continue loop;
                }
                mask |= 1 << ch;
            }
            int n = masks.size();
            for (int i = 0; i < n; i++) {
                int m = masks.get(i);
                if ((m & mask) == 0) {
                    masks.add(m | mask);
                    ans = Math.max(ans, Integer.bitCount(m | mask));
                }
            }
        }

        return ans;
    }
}
```

### **Go**

```go
func maxLength(arr []string) int {

	max := func(x, y int) int {
		if x > y {
			return x
		}
		return y
	}

	ans := 0
	masks := []int{0}

loop:
	for _, s := range arr {
		mask := 0
		for _, ch := range s {
			ch -= 'a'
			if (mask>>ch)&1 == 1 {
				continue loop
			}
			mask |= 1 << ch
		}
		for _, m := range masks {
			if m&mask == 0 {
				masks = append(masks, m|mask)
				ans = max(ans, bits.OnesCount(uint(m|mask)))
			}
		}
	}

	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
