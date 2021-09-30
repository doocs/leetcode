# [345. 反转字符串中的元音字母](https://leetcode-cn.com/problems/reverse-vowels-of-a-string)

[English Version](/solution/0300-0399/0345.Reverse%20Vowels%20of%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个函数，以字符串作为输入，反转该字符串中的元音字母。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>&quot;hello&quot;
<strong>输出：</strong>&quot;holle&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>&quot;leetcode&quot;
<strong>输出：</strong>&quot;leotcede&quot;</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>元音字母不包含字母 &quot;y&quot; 。</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

将字符串转为字符数组（或列表），定义双指针 i、j，分别指向数组（列表）头部和尾部，当 i、j 指向的字符均为元音字母时，进行交换。

依次遍历，当 `i >= j` 时，遍历结束。将字符数组（列表）转为字符串返回即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reverseVowels(self, s: str) -> str:
        vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}
        i, j = 0, len(s) - 1
        chars = list(s)
        while i < j:
            if chars[i] not in vowels:
                i += 1
                continue
            if chars[j] not in vowels:
                j -= 1
                continue
            chars[i], chars[j] = chars[j], chars[i]
            i += 1
            j -= 1
        return ''.join(chars)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int i = 0, j = s.length() - 1;
        char[] chars = s.toCharArray();
        while (i < j) {
            if (!vowels.contains(chars[i])) {
                ++i;
                continue;
            }
            if (!vowels.contains(chars[j])) {
                --j;
                continue;
            }
            char t = chars[i];
            chars[i] = chars[j];
            chars[j] = t;
            ++i;
            --j;
        }
        return new String(chars);
    }
}
```

### **Go**

```go
func reverseVowels(s string) string {
	left, right := 0, len(s)-1
	a := []byte(s)
	for left < right {
		for left < right && !isVowel(a[left]) {
			left++
		}
		for left < right && !isVowel(a[right]) {
			right--
		}
		if left != right && isVowel(a[left]) && isVowel(a[right]) {
			a[left], a[right] = a[right], a[left]
			left++
			right--
		}
	}
	return string(a)
}

func isVowel(b byte) bool {
	return b == 'a' || b == 'e' || b == 'i' || b == 'o' || b == 'u' ||
		b == 'A' || b == 'E' || b == 'I' || b == 'O' || b == 'U'
}
```

### **...**

```

```

<!-- tabs:end -->
