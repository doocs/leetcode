# [1750. 删除字符串两端相同字符后的最短长度](https://leetcode.cn/problems/minimum-length-of-string-after-deleting-similar-ends)

[English Version](/solution/1700-1799/1750.Minimum%20Length%20of%20String%20After%20Deleting%20Similar%20Ends/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个只包含字符 <code>'a'</code>，<code>'b'</code> 和 <code>'c'</code> 的字符串 <code>s</code> ，你可以执行下面这个操作（5 个步骤）任意次：</p>

<ol>
	<li>选择字符串 <code>s</code> 一个 <strong>非空</strong> 的前缀，这个前缀的所有字符都相同。</li>
	<li>选择字符串 <code>s</code> 一个 <strong>非空</strong> 的后缀，这个后缀的所有字符都相同。</li>
	<li>前缀和后缀在字符串中任意位置都不能有交集。</li>
	<li>前缀和后缀包含的所有字符都要相同。</li>
	<li>同时删除前缀和后缀。</li>
</ol>

<p>请你返回对字符串 <code>s</code> 执行上面操作任意次以后（可能 0 次），能得到的 <strong>最短长度</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "ca"
<b>输出：</b>2
<strong>解释：</strong>你没法删除任何一个字符，所以字符串长度仍然保持不变。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "cabaabac"
<b>输出：</b>0
<b>解释：</b>最优操作序列为：
- 选择前缀 "c" 和后缀 "c" 并删除它们，得到 s = "abaaba" 。
- 选择前缀 "a" 和后缀 "a" 并删除它们，得到 s = "baab" 。
- 选择前缀 "b" 和后缀 "b" 并删除它们，得到 s = "aa" 。
- 选择前缀 "a" 和后缀 "a" 并删除它们，得到 s = "" 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>s = "aabccabba"
<b>输出：</b>3
<b>解释：</b>最优操作序列为：
- 选择前缀 "aa" 和后缀 "a" 并删除它们，得到 s = "bccabb" 。
- 选择前缀 "b" 和后缀 "bb" 并删除它们，得到 s = "cca" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 10<sup>5</sup></code></li>
	<li><code>s</code> 只包含字符 <code>'a'</code>，<code>'b'</code> 和 <code>'c'</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们定义两个指针 $i$ 和 $j$ 分别指向字符串 $s$ 的头部和尾部，然后向中间移动，直到 $i$ 和 $j$ 指向的字符不相等，此时 $\max(0, j - i + 1)$ 即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumLength(self, s: str) -> int:
        i, j = 0, len(s) - 1
        while i < j and s[i] == s[j]:
            while i + 1 < j and s[i] == s[i + 1]:
                i += 1
            while i < j - 1 and s[j - 1] == s[j]:
                j -= 1
            i, j = i + 1, j - 1
        return max(0, j - i + 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumLength(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j && s.charAt(i) == s.charAt(j)) {
            while (i + 1 < j && s.charAt(i) == s.charAt(i + 1)) {
                ++i;
            }
            while (i < j - 1 && s.charAt(j) == s.charAt(j - 1)) {
                --j;
            }
            ++i;
            --j;
        }
        return Math.max(0, j - i + 1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumLength(string s) {
        int i = 0, j = s.size() - 1;
        while (i < j && s[i] == s[j]) {
            while (i + 1 < j && s[i] == s[i + 1]) {
                ++i;
            }
            while (i < j - 1 && s[j] == s[j - 1]) {
                --j;
            }
            ++i;
            --j;
        }
        return max(0, j - i + 1);
    }
};
```

### **Go**

```go
func minimumLength(s string) int {
	i, j := 0, len(s)-1
	for i < j && s[i] == s[j] {
		for i+1 < j && s[i] == s[i+1] {
			i++
		}
		for i < j-1 && s[j] == s[j-1] {
			j--
		}
		i, j = i+1, j-1
	}
	return max(0, j-i+1)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minimumLength(s: string): number {
    let i = 0;
    let j = s.length - 1;
    while (i < j && s[i] === s[j]) {
        while (i + 1 < j && s[i + 1] === s[i]) {
            ++i;
        }
        while (i < j - 1 && s[j - 1] === s[j]) {
            --j;
        }
        ++i;
        --j;
    }
    return Math.max(0, j - i + 1);
}
```

### **Rust**

```rust
impl Solution {
    pub fn minimum_length(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut start = 0;
        let mut end = n - 1;
        while start < end && s[start] == s[end] {
            while start + 1 < end && s[start] == s[start + 1] {
                start += 1;
            }
            while start < end - 1 && s[end] == s[end - 1] {
                end -= 1;
            }
            start += 1;
            end -= 1;
        }
        0.max(end - start + 1) as i32
    }
}
```

### **C**

```c
int minimumLength(char *s) {
    int n = strlen(s);
    int start = 0;
    int end = n - 1;
    while (start < end && s[start] == s[end]) {
        while (start + 1 < end && s[start] == s[start + 1]) {
            start++;
        }
        while (start < end - 1 && s[end] == s[end - 1]) {
            end--;
        }
        start++;
        end--;
    }
    if (start > end) {
        return 0;
    }
    return end - start + 1;
}
```

### **...**

```

```

<!-- tabs:end -->
