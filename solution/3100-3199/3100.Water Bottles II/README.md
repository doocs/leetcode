---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3100.Water%20Bottles%20II/README.md
rating: 1366
source: 第 391 场周赛 Q2
tags:
    - 数学
    - 模拟
---

<!-- problem:start -->

# [3100. 换水问题 II](https://leetcode.cn/problems/water-bottles-ii)

[English Version](/solution/3100-3199/3100.Water%20Bottles%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>numBottles</code> 和 <code>numExchange</code> 。</p>

<p><code>numBottles</code> 代表你最初拥有的满水瓶数量。在一次操作中，你可以执行以下操作之一：</p>

<ul>
	<li>喝掉任意数量的满水瓶，使它们变成空水瓶。</li>
	<li>用 <code>numExchange</code> 个空水瓶交换一个满水瓶。然后，将 <code>numExchange</code> 的值增加 1 。</li>
</ul>

<p>注意，你不能使用相同的 <code>numExchange</code> 值交换多批空水瓶。例如，如果 <code>numBottles == 3</code> 并且 <code>numExchange == 1</code> ，则不能用 <code>3</code> 个空水瓶交换成 <code>3</code> 个满水瓶。</p>

<p>返回你 <strong>最多</strong> 可以喝到多少瓶水。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3100.Water%20Bottles%20II/images/exampleone1.png" style="width: 948px; height: 482px; padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>输入：</strong>numBottles = 13, numExchange = 6
<strong>输出：</strong>15
<strong>解释：</strong>上表显示了满水瓶的数量、空水瓶的数量、numExchange 的值，以及累计喝掉的水瓶数量。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3100.Water%20Bottles%20II/images/example231.png" style="width: 990px; height: 642px; padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>输入：</strong>numBottles = 10, numExchange = 3
<strong>输出：</strong>13
<strong>解释：</strong>上表显示了满水瓶的数量、空水瓶的数量、numExchange 的值，以及累计喝掉的水瓶数量。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= numBottles &lt;= 100 </code></li>
	<li><code>1 &lt;= numExchange &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以在一开始就喝掉所有的满水瓶，因此初始时我们喝到的水数量为 `numBottles`。然后我们不断地进行以下操作：

-   如果当前有 `numExchange` 个空水瓶，我们就可以用它们换一瓶满水瓶，换完后，`numExchange` 的值增加 1。然后，我们喝掉这瓶水，喝到的水数量增加 $1$，空水瓶数量增加 $1$。
-   如果当前没有 `numExchange` 个空水瓶，那么我们就不能再换水了，此时我们就可以停止操作。

我们不断地进行上述操作，直到我们无法再换水为止。最终我们喝到的水的数量就是答案。

时间复杂度 $O(\sqrt{numBottles})$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def maxBottlesDrunk(self, numBottles: int, numExchange: int) -> int:
        ans = numBottles
        while numBottles >= numExchange:
            numBottles -= numExchange
            numExchange += 1
            ans += 1
            numBottles += 1
        return ans
```

```java
class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            numBottles -= numExchange;
            ++numExchange;
            ++ans;
            ++numBottles;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxBottlesDrunk(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            numBottles -= numExchange;
            ++numExchange;
            ++ans;
            ++numBottles;
        }
        return ans;
    }
};
```

```go
func maxBottlesDrunk(numBottles int, numExchange int) int {
	ans := numBottles
	for numBottles >= numExchange {
		numBottles -= numExchange
		numExchange++
		ans++
		numBottles++
	}
	return ans
}
```

```ts
function maxBottlesDrunk(numBottles: number, numExchange: number): number {
    let ans = numBottles;
    while (numBottles >= numExchange) {
        numBottles -= numExchange;
        ++numExchange;
        ++ans;
        ++numBottles;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
