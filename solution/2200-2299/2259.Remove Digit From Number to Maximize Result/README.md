# [2259. 移除指定数字得到的最大结果](https://leetcode.cn/problems/remove-digit-from-number-to-maximize-result)

[English Version](/solution/2200-2299/2259.Remove%20Digit%20From%20Number%20to%20Maximize%20Result/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个表示某个正整数的字符串 <code>number</code> 和一个字符 <code>digit</code> 。</p>

<p>从 <code>number</code> 中 <strong>恰好</strong> 移除 <strong>一个</strong> 等于&nbsp;<code>digit</code> 的字符后，找出并返回按 <strong>十进制</strong> 表示 <strong>最大</strong> 的结果字符串。生成的测试用例满足 <code>digit</code> 在 <code>number</code> 中出现至少一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>number = "123", digit = "3"
<strong>输出：</strong>"12"
<strong>解释：</strong>"123" 中只有一个 '3' ，在移除 '3' 之后，结果为 "12" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>number = "1231", digit = "1"
<strong>输出：</strong>"231"
<strong>解释：</strong>可以移除第一个 '1' 得到 "231" 或者移除第二个 '1' 得到 "123" 。
由于 231 &gt; 123 ，返回 "231" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>number = "551", digit = "5"
<strong>输出：</strong>"51"
<strong>解释：</strong>可以从 "551" 中移除第一个或者第二个 '5' 。
两种方案的结果都是 "51" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= number.length &lt;= 100</code></li>
	<li><code>number</code> 由数字 <code>'1'</code> 到 <code>'9'</code> 组成</li>
	<li><code>digit</code> 是 <code>'1'</code> 到 <code>'9'</code> 中的一个数字</li>
	<li><code>digit</code> 在 <code>number</code> 中出现至少一次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力枚举**

我们可以枚举字符串 $number$ 的所有位置 $i$，如果 $number[i] = digit$，那么我们取 $number$ 的前缀 $number[0:i]$ 和后缀 $number[i+1:]$ 拼接起来，即为移除 $number[i]$ 后的结果。我们取所有可能的结果中最大的即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $number$ 的长度。

**方法二：贪心**

我们可以枚举字符串 $number$ 的所有位置 $i$，如果 $number[i] = digit$，记录 $digit$ 最后一次出现的位置 $last$，并且如果 $i + 1 \lt n$ 且 $number[i] \lt number[i + 1]$，那么我们可以直接返回 $number[0:i] + number[i+1:]$，即为移除 $number[i]$ 后的结果。这是因为如果 $number[i] < number[i + 1]$，那么移除 $number[i]$ 后，结果一定会更大。

遍历结束，我们返回 $number[0:last] + number[last+1:]$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $number$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeDigit(self, number: str, digit: str) -> str:
        return max(
            number[:i] + number[i + 1 :] for i, d in enumerate(number) if d == digit
        )
```

```python
class Solution:
    def removeDigit(self, number: str, digit: str) -> str:
        last = -1
        n = len(number)
        for i, d in enumerate(number):
            if d == digit:
                last = i
                if i + 1 < n and d < number[i + 1]:
                    break
        return number[:last] + number[last + 1:]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String removeDigit(String number, char digit) {
        String ans = "0";
        for (int i = 0, n = number.length(); i < n; ++i) {
            char d = number.charAt(i);
            if (d == digit) {
                String t = number.substring(0, i) + number.substring(i + 1);
                if (ans.compareTo(t) < 0) {
                    ans = t;
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public String removeDigit(String number, char digit) {
        int last = -1;
        int n = number.length();
        for (int i = 0; i < n; ++i) {
            char d = number.charAt(i);
            if (d == digit) {
                last = i;
                if (i + 1 < n && d < number.charAt(i + 1)) {
                    break;
                }
            }
        }
        return number.substring(0, last) + number.substring(last + 1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string removeDigit(string number, char digit) {
        string ans = "0";
        for (int i = 0, n = number.size(); i < n; ++i) {
            char d = number[i];
            if (d == digit) {
                string t = number.substr(0, i) + number.substr(i + 1, n - i);
                if (ans < t) {
                    ans = t;
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    string removeDigit(string number, char digit) {
        int n = number.size();
        int last = -1;
        for (int i = 0; i < n; ++i) {
            char d = number[i];
            if (d == digit) {
                last = i;
                if (i + 1 < n && number[i] < number[i + 1]) {
                    break;
                }
            }
        }
        return number.substr(0, last) + number.substr(last + 1);
    }
};
```

### **Go**

```go
func removeDigit(number string, digit byte) string {
	ans := "0"
	for i, d := range number {
		if d == rune(digit) {
			t := number[:i] + number[i+1:]
			if strings.Compare(ans, t) < 0 {
				ans = t
			}
		}
	}
	return ans
}
```

```go
func removeDigit(number string, digit byte) string {
	last := -1
	n := len(number)
	for i := range number {
		if number[i] == digit {
			last = i
			if i+1 < n && number[i] < number[i+1] {
				break
			}
		}
	}
	return number[:last] + number[last+1:]
}
```

### **TypeScript**

```ts
function removeDigit(number: string, digit: string): string {
    const n = number.length;
    let last = -1;
    for (let i = 0; i < n; ++i) {
        if (number[i] === digit) {
            last = i;
            if (i + 1 < n && number[i] < number[i + 1]) {
                break;
            }
        }
    }
    return number.substring(0, last) + number.substring(last + 1);
}
```

### **...**

```

```

<!-- tabs:end -->
