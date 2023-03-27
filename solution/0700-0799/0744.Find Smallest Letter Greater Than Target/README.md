# [744. 寻找比目标字母大的最小字母](https://leetcode.cn/problems/find-smallest-letter-greater-than-target)

[English Version](/solution/0700-0799/0744.Find%20Smallest%20Letter%20Greater%20Than%20Target/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符数组 <code>letters</code>，该数组按<strong>非递减顺序</strong>排序，以及一个字符 <code>target</code>。<code>letters</code>&nbsp;里<strong>至少有两个不同</strong>的字符。</p>

<p>返回&nbsp;<code>letters</code>&nbsp;中大于 <code>target</code> 的最小的字符。如果不存在这样的字符，则返回&nbsp;<code>letters</code> 的第一个字符。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>letters = ["c", "f", "j"]，target = "a"
<strong>输出:</strong> "c"
<strong>解释：</strong>letters 中字典上比 'a' 大的最小字符是 'c'。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> letters = ["c","f","j"], target = "c"
<strong>输出:</strong> "f"
<strong>解释：</strong>letters 中字典顺序上大于 'c' 的最小字符是 'f'。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> letters = ["x","x","y","y"], target = "z"
<strong>输出:</strong> "x"
<strong>解释：</strong>letters 中没有一个字符在字典上大于 'z'，所以我们返回 letters[0]。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= letters.length &lt;= 10<sup>4</sup></code></li>
	<li><code>letters[i]</code>&nbsp;是一个小写字母</li>
	<li><code>letters</code> 按<strong>非递减顺序</strong>排序</li>
	<li><code>letters</code> 最少包含两个不同的字母</li>
	<li><code>target</code> 是一个小写字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历**

遍历 `letters`，返回第一个满足 `letters[i] > target` 条件的元素。若是遍历结束还未找到，则返回 `letters[0]`。

> 至少存在两个不同的字母，所以不会返回 `target`。

时间复杂度：$O(N)$。

**方法二：二分**

利用 `letters` 有序的特点，可以使用二分来快速查找。

在返回值方面相比传统二分不一样，需要对结果进行取余操作：`letters[l % n]`。

为什么？如题描述，字母是重复出现的，当索引过界时，不是没有结果，而是需要返回前面的元素。

一个容易理解的版本，使用减法：

```c
if (l < n) {
    return letters[l];
}
return letters[l - n];
```

时间复杂度：$O(logN)$。

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
    const n = letters.length;
    let left = 0;
    let right = letters.length;
    while (left < right) {
        let mid = (left + right) >>> 1;
        if (letters[mid] > target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return letters[left % n];
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

### **Rust**

```rust
impl Solution {
    pub fn next_greatest_letter(letters: Vec<char>, target: char) -> char {
        *letters.iter().find(|&&c| c > target).unwrap_or(&letters[0])
    }
}
```

```rust
impl Solution {
    pub fn next_greatest_letter(letters: Vec<char>, target: char) -> char {
        let n = letters.len();
        let mut left = 0;
        let mut right = n;
        while left < right {
            let mid = left + (right - left) / 2;
            if letters[mid] > target {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        letters[left % n]
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param String[] $letters
     * @param String $target
     * @return String
     */
    function nextGreatestLetter($letters, $target) {
        $left = 0;
        $right = count($letters);
        while ($left <= $right) {
            $mid = floor($left + ($right - $left) / 2);
            if ($letters[$mid] > $target) $right = $mid - 1;
            else $left = $mid + 1;
        }
        if ($left >= count($letters)) return $letters[0];
        else return $letters[$left];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
