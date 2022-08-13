# [1518. 换酒问题](https://leetcode.cn/problems/water-bottles)

[English Version](/solution/1500-1599/1518.Water%20Bottles/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>小区便利店正在促销，用 <code>numExchange</code> 个空酒瓶可以兑换一瓶新酒。你购入了 <code>numBottles</code> 瓶酒。</p>

<p>如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。</p>

<p>请你计算 <strong>最多</strong> 能喝到多少瓶酒。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1518.Water%20Bottles/images/sample_1_1875.png" style="height: 240px; width: 480px;"></strong></p>

<pre><strong>输入：</strong>numBottles = 9, numExchange = 3
<strong>输出：</strong>13
<strong>解释：</strong>你可以用 <code>3</code> 个空酒瓶兑换 1 瓶酒。
所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1518.Water%20Bottles/images/sample_2_1875.png" style="height: 240px; width: 790px;"></p>

<pre><strong>输入：</strong>numBottles = 15, numExchange = 4
<strong>输出：</strong>19
<strong>解释：</strong>你可以用 <code>4</code> 个空酒瓶兑换 1 瓶酒。
所以最多能喝到 15 + 3 + 1 = 19 瓶酒。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>numBottles = 5, numExchange = 5
<strong>输出：</strong>6
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>numBottles = 2, numExchange = 3
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;numBottles &lt;= 100</code></li>
	<li><code>2 &lt;=&nbsp;numExchange &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

直接模拟空瓶兑新酒即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numWaterBottles(self, numBottles: int, numExchange: int) -> int:
        ans = numBottles
        while numBottles >= numExchange:
            numBottles -= numExchange - 1
            ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            numBottles -= (numExchange - 1);
            ++ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            numBottles -= (numExchange - 1);
            ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func numWaterBottles(numBottles int, numExchange int) int {
	ans := numBottles
	for numBottles >= numExchange {
		numBottles -= (numExchange - 1)
		ans++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
