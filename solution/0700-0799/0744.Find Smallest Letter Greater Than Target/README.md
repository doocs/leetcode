# [744. 寻找比目标字母大的最小字母](https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target)

[English Version](/solution/0700-0799/0744.Find%20Smallest%20Letter%20Greater%20Than%20Target/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个排序后的字符列表 <code>letters</code> ，列表中只包含小写英文字母。另给出一个目标字母&nbsp;<code>target</code>，请你寻找在这一有序列表里比目标字母大的最小字母。</p>

<p>在比较时，字母是依序循环出现的。举个例子：</p>

<ul>
	<li>如果目标字母 <code>target = 'z'</code> 并且字符列表为&nbsp;<code>letters = ['a', 'b']</code>，则答案返回&nbsp;<code>'a'</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>letters = ["c", "f", "j"]，target = "a"
<strong>输出:</strong> "c"
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> letters = ["c","f","j"], target = "c"
<strong>输出:</strong> "f"
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> letters = ["c","f","j"], target = "d"
<strong>输出:</strong> "f"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= letters.length &lt;= 10<sup>4</sup></code></li>
	<li><code>letters[i]</code>&nbsp;是一个小写字母</li>
	<li><code>letters</code> 按非递减顺序排序</li>
	<li><code>letters</code> 最少包含两个不同的字母</li>
	<li><code>target</code> 是一个小写字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def nextGreatestLetter(self, letters: List[str], target: str) -> str:
        left, right = 0, len(letters)
        while left < right:
            mid = (left + right) >> 1
            if ord(letters[mid]) > ord(target):
                right = mid
            else:
                left = mid + 1
        return letters[left % len(letters)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return letters[left % letters.length];
    }
}
```

### **TypeScript**

```ts
function nextGreatestLetter(letters: string[], target: string): string {
    let left = 0,
        right = letters.length;
    let x = target.charCodeAt(0);
    while (left < right) {
        let mid = (left + right) >> 1;
        if (x < letters[mid].charCodeAt(0)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return letters[left % letters.length];
}
```

### **C++**

```cpp
class Solution {
public:
    char nextGreatestLetter(vector<char>& letters, char target) {
        int left = 0, right = letters.size();
        while (left < right) {
            int mid = left + right >> 1;
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return letters[left % letters.size()];
    }
};
```

### **Go**

```go
func nextGreatestLetter(letters []byte, target byte) byte {
	left, right := 0, len(letters)
	for left < right {
		mid := (left + right) >> 1
		if letters[mid] > target {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return letters[left%len(letters)]
}
```

### **...**

```

```

<!-- tabs:end -->
