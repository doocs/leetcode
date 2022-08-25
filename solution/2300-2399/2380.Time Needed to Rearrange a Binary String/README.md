# [2380. 二进制字符串重新安排顺序需要的时间](https://leetcode.cn/problems/time-needed-to-rearrange-a-binary-string)

[English Version](/solution/2300-2399/2380.Time%20Needed%20to%20Rearrange%20a%20Binary%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制字符串&nbsp;<code>s</code>&nbsp;。在一秒之中，<strong>所有</strong>&nbsp;子字符串&nbsp;<code>"01"</code> <strong>同时</strong>&nbsp;被替换成&nbsp;<code>"10"</code>&nbsp;。这个过程持续进行到没有&nbsp;<code>"01"</code>&nbsp;存在。</p>

<p>请你返回完成这个过程所需要的秒数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "0110101"
<b>输出：</b>4
<b>解释：</b>
一秒后，s 变成 "1011010" 。
再过 1 秒后，s 变成 "1101100" 。
第三秒过后，s 变成 "1110100" 。
第四秒后，s 变成 "1111000" 。
此时没有 "01" 存在，整个过程花费 4 秒。
所以我们返回 4 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "11100"
<b>输出：</b>0
<strong>解释：</strong>
s 中没有 "01" 存在，整个过程花费 0 秒。
所以我们返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code>&nbsp;要么是&nbsp;<code>'0'</code>&nbsp;，要么是&nbsp;<code>'1'</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<p>你能以 O(n) 的时间复杂度解决这个问题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力模拟**

由于本题数据范围不大，所以可以暴力模拟，每一轮将字符串中所有 “01” 替换为 “10”，统计轮次作为答案。

时间复杂度 $O(n^2)$。每一轮时间复杂度 $O(n)$，最多进行 $n$ 轮操作。

**方法二：动态规划**

题目要把所有“01”串替换为“10”，实际上是将所有的“1”往左移动。操作过后，左侧均为“1”，而右侧均为“0”。

假如我们要把“0100010”重排为“1100000”，会出现两种情况：

1. 如果一个“1”左边有 $cnt$ 个“0”，那么将这个“1”移动到最左边的位置需要 $cnt$ 秒；
1. 如果有连续的“1”，则将这两个“1”移动到最左边的位置需要额外的 $1$ 秒。

看下面的示例：

| 时刻（秒） | 示例 1 | 示例 2 |
| ---------- | ------ | ------ |
| 0          | 0001   | 00011  |
| 1          | 0010   | 00101  |
| 2          | 0100   | 01010  |
| 3          | 1000   | 10100  |
| 4          | -      | 11000  |

我们可以看到，如果在 $cnt$ 个“0”之后只有一个“1”，那么只需要 $cnt$ 秒，如果有连续的“1”，则需要额外的 $1$ 秒。

因此，对于字符串中的每一个“1”，我们计算 $ans=max(ans+1, cnt)$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def secondsToRemoveOccurrences(self, s: str) -> int:
        ans = 0
        while s.count('01'):
            s = s.replace('01', '10')
            ans += 1
        return ans
```

```python
class Solution:
    def secondsToRemoveOccurrences(self, s: str) -> int:
        ans = cnt = 0
        for c in s:
            if c == '0':
                cnt += 1
            elif cnt:
                ans = max(ans + 1, cnt)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int secondsToRemoveOccurrences(String s) {
        char[] cs = s.toCharArray();
        boolean find = true;
        int ans = 0;
        while (find) {
            find = false;
            for (int i = 0; i < cs.length - 1; ++i) {
                if (cs[i] == '0' && cs[i + 1] == '1') {
                    char t = cs[i];
                    cs[i] = cs[i + 1];
                    cs[i + 1] = t;
                    ++i;
                    find = true;
                }
            }
            if (find) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int secondsToRemoveOccurrences(String s) {
        int ans = 0, cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                ++cnt;
            } else if (cnt > 0) {
                ans = Math.max(ans + 1, cnt);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int secondsToRemoveOccurrences(string s) {
        bool find = true;
        int ans = 0;
        while (find) {
            find = false;
            for (int i = 0; i < s.size() - 1; ++i) {
                if (s[i] == '0' && s[i + 1] == '1') {
                    swap(s[i], s[i + 1]);
                    ++i;
                    find = true;
                }
            }
            if (find) {
                ++ans;
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int secondsToRemoveOccurrences(string s) {
        int ans = 0, cnt = 0;
        for (char c : s) {
            if (c == '0') {
                ++cnt;
            } else if (cnt) {
                ans = max(ans + 1, cnt);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func secondsToRemoveOccurrences(s string) int {
	cs := []byte(s)
	ans := 0
	find := true
	for find {
		find = false
		for i := 0; i < len(cs)-1; i++ {
			if cs[i] == '0' && cs[i+1] == '1' {
				cs[i], cs[i+1] = cs[i+1], cs[i]
				i++
				find = true
			}
		}
		if find {
			ans++
		}
	}
	return ans
}
```

```go
func secondsToRemoveOccurrences(s string) int {
	ans, cnt := 0, 0
	for _, c := range s {
		if c == '0' {
			cnt++
		} else if cnt > 0 {
			ans = max(ans+1, cnt)
		}
	}
	return ans
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

```

### **...**

```


```

<!-- tabs:end -->
