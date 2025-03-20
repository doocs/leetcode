---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0434.Number%20of%20Segments%20in%20a%20String/README.md
tags:
    - 字符串
---

<!-- problem:start -->

# [434. 字符串中的单词数](https://leetcode.cn/problems/number-of-segments-in-a-string)

[English Version](/solution/0400-0499/0434.Number%20of%20Segments%20in%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。</p>

<p>请注意，你可以假定字符串里不包括任何不可打印的字符。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> &quot;Hello, my name is John&quot;
<strong>输出:</strong> 5
<strong>解释: </strong>这里的单词是指连续的不是空格的字符，所以 &quot;Hello,&quot; 算作 1 个单词。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字符串分割

我们将字符串 $\textit{s}$ 按照空格进行分割，然后统计不为空的单词个数。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $\textit{s}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSegments(self, s: str) -> int:
        return len(s.split())
```

#### Java

```java
class Solution {
    public int countSegments(String s) {
        int ans = 0;
        for (String t : s.split(" ")) {
            if (!"".equals(t)) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countSegments(string s) {
        int ans = 0;
        istringstream ss(s);
        while (ss >> s) ++ans;
        return ans;
    }
};
```

#### Go

```go
func countSegments(s string) int {
	ans := 0
	for _, t := range strings.Split(s, " ") {
		if len(t) > 0 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countSegments(s: string): number {
    return s.split(/\s+/).filter(Boolean).length;
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function countSegments($s) {
        $arr = explode(' ', $s);
        $cnt = 0;
        for ($i = 0; $i < count($arr); $i++) {
            if (strlen($arr[$i]) != 0) {
                $cnt++;
            }
        }
        return $cnt;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：模拟

我们也可以直接遍历字符串的每个字符 $\text{s[i]}$，如果 $\text{s[i]}$ 不是空格且 $\text{s[i-1]}$ 是空格或者 $i = 0$，那么就说明 $\text{s[i]}$ 是一个新的单词的开始，我们就将答案加一。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $\textit{s}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSegments(self, s: str) -> int:
        ans = 0
        for i, c in enumerate(s):
            if c != ' ' and (i == 0 or s[i - 1] == ' '):
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countSegments(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countSegments(string s) {
        int ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] != ' ' && (i == 0 || s[i - 1] == ' ')) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countSegments(s string) int {
	ans := 0
	for i, c := range s {
		if c != ' ' && (i == 0 || s[i-1] == ' ') {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countSegments(s: string): number {
    let ans = 0;
    for (let i = 0; i < s.length; i++) {
        let c = s[i];
        if (c !== ' ' && (i === 0 || s[i - 1] === ' ')) {
            ans++;
        }
    }
    return ans;
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function countSegments($s) {
        $ans = 0;
        $n = strlen($s);
        for ($i = 0; $i < $n; $i++) {
            $c = $s[$i];
            if ($c !== ' ' && ($i === 0 || $s[$i - 1] === ' ')) {
                $ans++;
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
