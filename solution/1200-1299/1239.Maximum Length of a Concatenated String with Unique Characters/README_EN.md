# [1239. Maximum Length of a Concatenated String with Unique Characters](https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters)

[中文文档](/solution/1200-1299/1239.Maximum%20Length%20of%20a%20Concatenated%20String%20with%20Unique%20Characters/README.md)

## Description

<p>Given an array of strings <code>arr</code>. String <code>s</code> is a concatenation of a sub-sequence of <code>arr</code> which have <strong>unique characters</strong>.</p>

<p>Return <em>the maximum possible length</em> of <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;un&quot;,&quot;iq&quot;,&quot;ue&quot;]
<strong>Output:</strong> 4
<strong>Explanation:</strong> All possible concatenations are &quot;&quot;,&quot;un&quot;,&quot;iq&quot;,&quot;ue&quot;,&quot;uniq&quot; and &quot;ique&quot;.
Maximum length is 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;cha&quot;,&quot;r&quot;,&quot;act&quot;,&quot;ers&quot;]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Possible solutions are &quot;chaers&quot; and &quot;acters&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;abcdefghijklmnopqrstuvwxyz&quot;]
<strong>Output:</strong> 26
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 16</code></li>
	<li><code>1 &lt;= arr[i].length &lt;= 26</code></li>
	<li><code>arr[i]</code> contains only lower case English letters.</li>
</ul>

## Solutions

State compression uses a 32-bit number to record the appearance of letters, and `masks` stores the previously enumerated strings.

<!-- tabs:start -->

### **Python3**

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
