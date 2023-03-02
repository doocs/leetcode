# [1518. 换水问题](https://leetcode.cn/problems/water-bottles)

[English Version](/solution/1500-1599/1518.Water%20Bottles/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>超市正在促销，你可以用 <code>numExchange</code> 个空水瓶从超市兑换一瓶水。最开始，你一共购入了 <code>numBottles</code> 瓶水。</p>

<p>如果喝掉了水瓶中的水，那么水瓶就会变成空的。</p>

<p>给你两个整数 <code>numBottles</code> 和 <code>numExchange</code> ，返回你 <strong>最多</strong> 可以喝到多少瓶水。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1518.Water%20Bottles/images/sample_1_1875.png" style="height: 240px; width: 480px;" /></strong></p>

<pre>
<strong>输入：</strong>numBottles = 9, numExchange = 3
<strong>输出：</strong>13
<strong>解释：</strong>你可以用 <code>3</code> 个空瓶兑换 1 瓶水。
所以最多能喝到 9 + 3 + 1 = 13 瓶水。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1518.Water%20Bottles/images/sample_2_1875.png" style="height: 240px; width: 790px;" /></p>

<pre>
<strong>输入：</strong>numBottles = 15, numExchange = 4
<strong>输出：</strong>19
<strong>解释：</strong>你可以用 <code>4</code> 个空瓶兑换 1 瓶水。
所以最多能喝到 15 + 3 + 1 = 19 瓶水。
</pre>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= numBottles &lt;= 100</code></li>
	<li><code>2 &lt;= numExchange &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们可以直接模拟整个过程。

初始时，我们有 `numBottles` 瓶水，因此可以喝到 `ans = numBottles` 瓶水，然后得到 `numBottles` 个空瓶子。

接下来，如果我们有 `numExchange` 个空瓶子，那么我们可以用它们兑换一瓶水并喝掉，此时我们剩余的空瓶子数量为 `numBottles - numExchange + 1`，然后我们累加喝到的水的数量，即 $ans = ans + 1$。

最后，返回 `ans` 即可。

时间复杂度 $(\frac{numBottles}{numExchange})$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numWaterBottles(self, numBottles: int, numExchange: int) -> int:
        ans = numBottles
        while numBottles >= numExchange:
            numBottles -= (numExchange - 1)
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
