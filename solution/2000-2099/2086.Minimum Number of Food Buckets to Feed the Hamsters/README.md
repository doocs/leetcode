# [2086. 从房屋收集雨水需要的最少水桶数](https://leetcode.cn/problems/minimum-number-of-food-buckets-to-feed-the-hamsters)

[English Version](/solution/2000-2099/2086.Minimum%20Number%20of%20Food%20Buckets%20to%20Feed%20the%20Hamsters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>street</code>&nbsp;。<code>street</code>&nbsp;中每个字符要么是表示房屋的&nbsp;<code>'H'</code> ，要么是表示空位的&nbsp;<code>'.'</code>&nbsp;。</p>

<p>你可以在 <strong>空位</strong>&nbsp;放置水桶，从相邻的房屋收集雨水。位置在 <code>i - 1</code>&nbsp;<strong>或者</strong> <code>i + 1</code>&nbsp;的水桶可以收集位置为 <code>i</code>&nbsp;处房屋的雨水。一个水桶如果相邻两个位置都有房屋，那么它可以收集 <strong>两个</strong> 房屋的雨水。</p>

<p>在确保 <strong>每个</strong>&nbsp;房屋旁边都 <strong>至少</strong>&nbsp;有一个水桶的前提下，请你返回需要的 <strong>最少</strong>&nbsp;水桶数。如果无解请返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>street = "H..H"
<b>输出：</b>2
<strong>解释：</strong>
我们可以在下标为 1 和 2 处放水桶。
"H..H" -&gt; "HBBH"（'B' 表示放置水桶）。
下标为 0 处的房屋右边有水桶，下标为 3 处的房屋左边有水桶。
所以每个房屋旁边都至少有一个水桶收集雨水。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>street = ".H.H."
<b>输出：</b>1
<strong>解释：</strong>
我们可以在下标为 2 处放置一个水桶。
".H.H." -&gt; ".HBH."（'B' 表示放置水桶）。
下标为 1 处的房屋右边有水桶，下标为 3 处的房屋左边有水桶。
所以每个房屋旁边都至少有一个水桶收集雨水。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>street = ".HHH."
<b>输出：</b>-1
<strong>解释：</strong>
没有空位可以放置水桶收集下标为 2 处的雨水。
所以没有办法收集所有房屋的雨水。
</pre>

<p><strong>示例 4：</strong></p>

<pre><b>输入：</b>street = "H"
<b>输出：</b>-1
<strong>解释：</strong>
没有空位放置水桶。
所以没有办法收集所有房屋的雨水。
</pre>

<p><strong>示例 5：</strong></p>

<pre><b>输入：</b>street = "."
<b>输出：</b>0
<strong>解释：</strong>
没有房屋需要收集雨水。
所以需要 0 个水桶。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= street.length &lt;= 10<sup>5</sup></code></li>
	<li><code>street[i]</code>&nbsp;要么是&nbsp;<code>'H'</code>&nbsp;，要么是&nbsp;<code>'.'</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

从左到右遍历字符串，遇到 `H` 时，优先考虑右边是否有空位，如果有则放置水桶，并且跳过水桶的下一个位置；如果右边没有空位，则考虑左边是否有空位，如果有则放置水桶，否则无解。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 `street` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumBuckets(self, street: str) -> int:
        ans = 0
        i, n = 0, len(street)
        while i < n:
            if street[i] == 'H':
                if i + 1 < n and street[i + 1] == '.':
                    i += 2
                    ans += 1
                elif i and street[i - 1] == '.':
                    ans += 1
                else:
                    return -1
            i += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumBuckets(String street) {
        int n = street.length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (street.charAt(i) == 'H') {
                if (i + 1 < n && street.charAt(i + 1) == '.') {
                    ++ans;
                    i += 2;
                } else if (i > 0 && street.charAt(i - 1) == '.') {
                    ++ans;
                } else {
                    return -1;
                }
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
    int minimumBuckets(string street) {
        int n = street.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (street[i] == 'H') {
                if (i + 1 < n && street[i + 1] == '.') {
                    ++ans;
                    i += 2;
                } else if (i && street[i - 1] == '.') {
                    ++ans;
                } else {
                    return -1;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumBuckets(street string) int {
	ans, n := 0, len(street)
	for i := 0; i < n; i++ {
		if street[i] == 'H' {
			if i+1 < n && street[i+1] == '.' {
				ans++
				i += 2
			} else if i > 0 && street[i-1] == '.' {
				ans++
			} else {
				return -1
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
