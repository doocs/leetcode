# [1208. 尽可能使字符串相等](https://leetcode.cn/problems/get-equal-substrings-within-budget)

[English Version](/solution/1200-1299/1208.Get%20Equal%20Substrings%20Within%20Budget/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个长度相同的字符串，<code>s</code> 和 <code>t</code>。</p>

<p>将 <code>s</code> 中的第 <code>i</code> 个字符变到 <code>t</code> 中的第 <code>i</code> 个字符需要 <code>|s[i] - t[i]|</code> 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。</p>

<p>用于变更字符串的最大预算是 <code>maxCost</code>。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。</p>

<p>如果你可以将 <code>s</code> 的子字符串转化为它在 <code>t</code> 中对应的子字符串，则返回可以转化的最大长度。</p>

<p>如果 <code>s</code> 中没有子字符串可以转化成 <code>t</code> 中对应的子字符串，则返回 <code>0</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", t = "bcdf", maxCost = 3
<strong>输出：</strong>3
<strong>解释：</strong>s<strong> </strong>中的<strong> </strong>"abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", t = "cdef", maxCost = 3
<strong>输出：</strong>1
<strong>解释：</strong>s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为<code> 1。</code>
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", t = "acde", maxCost = 0
<strong>输出：</strong>1
<strong>解释：</strong>a -> a, cost = 0，字符串未发生变化，所以最大长度为 1。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length, t.length <= 10^5</code></li>
	<li><code>0 <= maxCost <= 10^6</code></li>
	<li><code>s</code> 和 <code>t</code> 都只含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和 + 二分查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def equalSubstring(self, s: str, t: str, maxCost: int) -> int:
        n = len(s)
        presum = [0] * (n + 1)
        for i in range(n):
            presum[i + 1] = presum[i] + abs(ord(s[i]) - ord(t[i]))
        left, right = 0, n

        def check(l):
            i = 0
            while i + l - 1 < n:
                j = i + l - 1
                if presum[j + 1] - presum[i] <= maxCost:
                    return True
                i += 1
            return False

        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + Math.abs(s.charAt(i) - t.charAt(i));
        }
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (check(mid, presum, maxCost, n)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int l, int[] s, int maxCost, int n) {
        int i = 0;
        while (i + l - 1 < n) {
            int j = i + l - 1;
            if (s[j + 1] - s[i] <= maxCost) {
                return true;
            }
            ++i;
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int equalSubstring(string s, string t, int maxCost) {
        int n = s.size();
        vector<int> presum(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + abs(s[i] - t[i]);
        int left = 0, right = n;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (check(mid, presum, maxCost, n))
                left = mid;
            else
                right = mid - 1;
        }
        return left;
    }

    bool check(int l, vector<int>& s, int maxCost, int n) {
        int i = 0;
        while (i + l - 1 < n) {
            int j = i + l - 1;
            if (s[j + 1] - s[i] <= maxCost) return true;
            ++i;
        }
        return false;
    }
};
```

### **Go**

```go
func equalSubstring(s string, t string, maxCost int) int {
	n := len(s)
	presum := make([]int, n+1)
	for i, c := range s {
		presum[i+1] = presum[i] + abs(int(c)-int(t[i]))
	}

	left, right := 0, n
	check := func(l int) bool {
		i := 0
		for i+l-1 < n {
			j := i + l - 1
			if presum[j+1]-presum[i] <= maxCost {
				return true
			}
			i++
		}
		return false
	}
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}
```

### **...**

```

```

<!-- tabs:end -->
