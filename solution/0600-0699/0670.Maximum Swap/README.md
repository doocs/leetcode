# [670. 最大交换](https://leetcode.cn/problems/maximum-swap)

[English Version](/solution/0600-0699/0670.Maximum%20Swap/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非负整数，你<strong>至多</strong>可以交换一次数字中的任意两位。返回你能得到的最大值。</p>

<p><strong>示例 1 :</strong></p>

<pre>
<strong>输入:</strong> 2736
<strong>输出:</strong> 7236
<strong>解释:</strong> 交换数字2和数字7。
</pre>

<p><strong>示例 2 :</strong></p>

<pre>
<strong>输入:</strong> 9973
<strong>输出:</strong> 9973
<strong>解释:</strong> 不需要交换。
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>给定数字的范围是&nbsp;[0, 10<sup>8</sup>]</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumSwap(self, num: int) -> int:
        chars = list(str(num))
        n = len(chars)
        for i in range(n - 1):
            mx = i + 1
            for j in range(i + 1, n):
                if ord(chars[j]) >= ord(chars[mx]):
                    mx = j
            if ord(chars[i]) < ord(chars[mx]):
                chars[i], chars[mx] = chars[mx], chars[i]
                break
        return int(''.join(chars))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int n = chars.length;
        for (int i = 0; i < n - 1; ++i) {
            int mx = i + 1;
            for (int j = i + 1; j < n; ++j) {
                if (chars[j] >= chars[mx]) {
                    mx = j;
                }
            }
            if (chars[i] < chars[mx]) {
                char t = chars[i];
                chars[i] = chars[mx];
                chars[mx] = t;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumSwap(int num) {
        string s = to_string(num);
        int n = s.size();
        for (int i = 0; i < n - 1; ++i) {
            int mx = i + 1;
            for (int j = i + 1; j < n; ++j) {
                if (s[j] >= s[mx]) mx = j;
            }
            if (s[i] < s[mx]) {
                swap(s[i], s[mx]);
                break;
            }
        }
        return stoi(s);
    }
};
```

### **Go**

```go
func maximumSwap(num int) int {
	s := strconv.Itoa(num)
	chars := []byte(s)
	n := len(chars)
	for i := range chars[:n-1] {
		mx := i + 1
		for j := i + 1; j < n; j++ {
			if chars[j] >= chars[mx] {
				mx = j
			}
		}
		if chars[i] < chars[mx] {
			chars[i], chars[mx] = chars[mx], chars[i]
			break
		}
	}
	ans, _ := strconv.Atoi(string(chars))
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
