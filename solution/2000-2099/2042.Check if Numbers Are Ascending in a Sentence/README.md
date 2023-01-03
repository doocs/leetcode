# [2042. 检查句子中的数字是否递增](https://leetcode.cn/problems/check-if-numbers-are-ascending-in-a-sentence)

[English Version](/solution/2000-2099/2042.Check%20if%20Numbers%20Are%20Ascending%20in%20a%20Sentence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>句子是由若干 <strong>token</strong> 组成的一个列表，<strong>token</strong> 间用 <strong>单个</strong> 空格分隔，句子没有前导或尾随空格。每个 token 要么是一个由数字 <code>0-9</code> 组成的不含前导零的 <strong>正整数</strong>&nbsp;，要么是一个由小写英文字母组成的 <strong>单词</strong> 。</p>

<ul>
	<li>示例，<code>"a puppy has 2 eyes 4 legs"</code> 是一个由 7 个 token 组成的句子：<code>"2"</code> 和 <code>"4"</code> 是数字，其他像&nbsp;<code>"puppy"</code> 这样的 tokens 属于单词。</li>
</ul>

<p>给你一个表示句子的字符串 <code>s</code> ，你需要检查 <code>s</code> 中的 <strong>全部</strong> 数字是否从左到右严格递增（即，除了最后一个数字，<code>s</code> 中的 <strong>每个</strong> 数字都严格小于它 <strong>右侧</strong> 的数字）。</p>

<p>如果满足题目要求，返回 <code>true</code>&nbsp;，否则，返回<em> </em><code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="example-1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2042.Check%20if%20Numbers%20Are%20Ascending%20in%20a%20Sentence/images/example1.png" style="width: 637px; height: 48px;" /></p>

<pre>
<strong>输入：</strong>s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles"
<strong>输出：</strong>true
<strong>解释：</strong>句子中的数字是：1, 3, 4, 6, 12 。
这些数字是按从左到右严格递增的 1 &lt; 3 &lt; 4 &lt; 6 &lt; 12 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "hello world 5 x 5"
<strong>输出：</strong>false
<strong>解释：</strong>句子中的数字是：<em><strong>5</strong></em>, <strong><em>5</em></strong> 。这些数字不是严格递增的。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="example-3" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2042.Check%20if%20Numbers%20Are%20Ascending%20in%20a%20Sentence/images/example3.png" style="width: 794px; height: 48px;" /></p>

<pre>
<strong>输入：</strong>s = "sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s"
<strong>输出：</strong>false
<strong>解释：</strong>s 中的数字是：7, <em><strong>51</strong></em>, <em><strong>50</strong></em>, 60 。这些数字不是严格递增的。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "4 5 11 26"
<strong>输出：</strong>true
<strong>解释：</strong>s 中的数字是：4, 5, 11, 26 。
这些数字是按从左到右严格递增的：4 &lt; 5 &lt; 11 &lt; 26 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 200</code></li>
	<li><code>s</code> 由小写英文字母、空格和数字 <code>0</code> 到 <code>9</code> 组成（包含 <code>0</code> 和 <code>9</code>）</li>
	<li><code>s</code> 中数字 token 的数目在 <code>2</code> 和 <code>100</code> 之间（包含 <code>2</code> 和 <code>100</code>）</li>
	<li><code>s</code> 中的 token 之间由单个空格分隔</li>
	<li><code>s</code> 中至少有 <strong>两个</strong> 数字</li>
	<li><code>s</code> 中的每个数字都是一个 <strong>小于</strong> <code>100</code> 的 <strong>正</strong> 数，且不含前导零</li>
	<li><code>s</code> 不含前导或尾随空格</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们可以将字符串 $s$ 按空格分割成若干个单词。然后遍历每个单词，判断其是否为数字，若是数字，则将其转换为整数，与前一个数字比较，若不严格递增，返回 `false`，否则，将当前数字赋值给前一个数字，继续遍历。

遍历结束，说明字符串中的数字严格递增，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def areNumbersAscending(self, s: str) -> bool:
        pre = 0
        for t in s.split():
            if t[0].isdigit():
                if (cur := int(t)) <= pre:
                    return False
                pre = cur
        return True
```

```python
class Solution:
    def areNumbersAscending(self, s: str) -> bool:
        pre = i = 0
        n = len(s)
        while i < n:
            if s[i].isdigit():
                cur = 0
                while i < n and s[i].isdigit():
                    cur = cur * 10 + int(s[i])
                    i += 1
                if pre >= cur:
                    return False
                pre = cur
            else:
                i += 1
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean areNumbersAscending(String s) {
        int pre = 0;
        for (var t : s.split(" ")) {
            if (t.charAt(0) <= '9') {
                int cur = Integer.parseInt(t);
                if (pre >= cur) {
                    return false;
                }
                pre = cur;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool areNumbersAscending(string s) {
        int pre = 0;
        istringstream is(s);
        string t;
        while (is >> t) {
            if (isdigit(t[0])) {
                int cur = stoi(t);
                if (pre >= cur) {
                    return false;
                }
                pre = cur;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func areNumbersAscending(s string) bool {
	pre := 0
	for _, t := range strings.Split(s, " ") {
		if t[0] <= '9' {
			cur, _ := strconv.Atoi(t)
			if pre >= cur {
				return false
			}
			pre = cur
		}
	}
	return true
}
```

### **TypeScript**

```ts
function areNumbersAscending(s: string): boolean {
    let pre = -1;
    for (const cur of s.split(' ')) {
        if (cur[0] <= '9') {
            const num = Number(cur);
            if (num <= pre) {
                return false;
            }
            pre = num;
        }
    }
    return true;
}
```

### **Rust**

```rust
impl Solution {
    pub fn are_numbers_ascending(s: String) -> bool {
        let mut pre = -1;
        for cur in s.split(' ') {
            if cur.as_bytes()[0] <= b'9' {
                let num = cur.parse::<i32>().unwrap();
                if num <= pre {
                    return false;
                }
                pre = num;
            }
        }
        true
    }
}
```

### **C**

```c
bool areNumbersAscending(char *s) {
    int pre = -1;
    int cur = 0;
    for (int i = 0; s[i]; i++) {
        if (isdigit(s[i])) {
            cur = cur * 10 + s[i] - '0';
        } else {
            if (cur != 0) {
                if (cur <= pre) {
                    return 0;
                }
                pre = cur;
                cur = 0;
            }
        }
    }
    if (cur != 0 && cur <= pre) {
        return 0;
    }
    return 1;
}
```

### **...**

```

```

<!-- tabs:end -->
