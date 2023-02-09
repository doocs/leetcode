# [991. 坏了的计算器](https://leetcode.cn/problems/broken-calculator)

[English Version](/solution/0900-0999/0991.Broken%20Calculator/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在显示着数字&nbsp;<code>startValue</code>&nbsp;的坏计算器上，我们可以执行以下两种操作：</p>

<ul>
	<li><strong>双倍（Double）：</strong>将显示屏上的数字乘 2；</li>
	<li><strong>递减（Decrement）：</strong>将显示屏上的数字减 <code>1</code> 。</li>
</ul>

<p>给定两个整数&nbsp;<code>startValue</code>&nbsp;和&nbsp;<code>target</code>&nbsp;。返回显示数字&nbsp;<code>target</code>&nbsp;所需的最小操作数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>startValue = 2, target = 3
<strong>输出：</strong>2
<strong>解释：</strong>先进行双倍运算，然后再进行递减运算 {2 -&gt; 4 -&gt; 3}.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>startValue = 5, target = 8
<strong>输出：</strong>2
<strong>解释：</strong>先递减，再双倍 {5 -&gt; 4 -&gt; 8}.
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>startValue = 3, target = 10
<strong>输出：</strong>3
<strong>解释：</strong>先双倍，然后递减，再双倍 {3 -&gt; 6 -&gt; 5 -&gt; 10}.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= startValue, target &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：逆向计算**

我们可以采用逆向计算的方式，从 `target` 开始，如果 `target` 是奇数，则 `target++`，否则 `target >>= 1`，累加操作次数，直到 `target` 小于等于 `startValue`，此时的操作次数加上 `startValue - target` 即为最终结果。

时间复杂度 $O(\log n)$，其中 $n$ 为 `target`。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def brokenCalc(self, startValue: int, target: int) -> int:
        ans = 0
        while startValue < target:
            if target & 1:
                target += 1
            else:
                target >>= 1
            ans += 1
        ans += startValue - target
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int brokenCalc(int startValue, int target) {
        int ans = 0;
        while (startValue < target) {
            if ((target & 1) == 1) {
                target++;
            } else {
                target >>= 1;
            }
            ans += 1;
        }
        ans += startValue - target;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int brokenCalc(int startValue, int target) {
        int ans = 0;
        while (startValue < target) {
            if (target & 1) {
                target++;
            } else {
                target >>= 1;
            }
            ++ans;
        }
        ans += startValue - target;
        return ans;
    }
};
```

### **Go**

```go
func brokenCalc(startValue int, target int) (ans int) {
	for startValue < target {
		if target&1 == 1 {
			target++
		} else {
			target >>= 1
		}
		ans++
	}
	ans += startValue - target
	return
}
```

### **...**

```

```

<!-- tabs:end -->
