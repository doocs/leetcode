# [1798. 你能构造出连续值的最大数目](https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make)

[English Version](/solution/1700-1799/1798.Maximum%20Number%20of%20Consecutive%20Values%20You%20Can%20Make/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>coins</code> ，它代表你拥有的 <code>n</code> 个硬币。第 <code>i</code> 个硬币的值为 <code>coins[i]</code> 。如果你从这些硬币中选出一部分硬币，它们的和为 <code>x</code> ，那么称，你可以 <strong>构造</strong> 出 <code>x</code> 。</p>

<p>请返回从 <code>0</code> 开始（<strong>包括</strong> <code>0</code> ），你最多能 <strong>构造</strong> 出多少个连续整数。</p>

<p>你可能有多个相同值的硬币。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>coins = [1,3]
<b>输出：</b>2
<strong>解释：</strong>你可以得到以下这些值：
- 0：什么都不取 []
- 1：取 [1]
从 0 开始，你可以构造出 2 个连续整数。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>coins = [1,1,1,4]
<b>输出：</b>8
<strong>解释：</strong>你可以得到以下这些值：
- 0：什么都不取 []
- 1：取 [1]
- 2：取 [1,1]
- 3：取 [1,1,1]
- 4：取 [4]
- 5：取 [4,1]
- 6：取 [4,1,1]
- 7：取 [4,1,1,1]
从 0 开始，你可以构造出 8 个连续整数。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,10,3,1]
<b>输出：</b>20</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>coins.length == n</code></li>
	<li><code>1 <= n <= 4 * 10<sup>4</sup></code></li>
	<li><code>1 <= coins[i] <= 4 * 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先对 `coins` 数组进行排序。

假设前 i 个数所有构造的的连续整数的个数为 res，初始化为 1。

遍历排序后的 `coins` 数组：

-   若 `coins[i] > res`，说明接下来无法组成 `res + 1` 个连续整数，跳出循环。

    > 对于 `1, 3`，若遍历到 3，此时前面的连续整数个数为 2，即连续整数为：`0, 1`。此时 3 大于 2，无法构成连续整数 `0, 1, 2`，所以最大连续整数个数为 2。

-   若 `coins[i] <= res`，说明有 `coins[i]` 个数也能构成连续整数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getMaximumConsecutive(self, coins: List[int]) -> int:
        res = 1
        for coin in sorted(coins):
            if coin > res:
                break
            res += coin
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int getMaximumConsecutive(int[] coins) {
        int res = 1;
        Arrays.sort(coins);
        for (int coin : coins) {
            if (coin > res) {
                break;
            }
            res += coin;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
