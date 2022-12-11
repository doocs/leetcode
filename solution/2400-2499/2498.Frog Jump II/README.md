# [2498. 青蛙过河 II](https://leetcode.cn/problems/frog-jump-ii)

[English Version](/solution/2400-2499/2498.Frog%20Jump%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>stones</code>&nbsp;，数组中的元素&nbsp;<strong>严格递增</strong>&nbsp;，表示一条河中石头的位置。</p>

<p>一只青蛙一开始在第一块石头上，它想到达最后一块石头，然后回到第一块石头。同时每块石头 <strong>至多</strong> 到达 <strong>一次。</strong></p>

<p>一次跳跃的 <strong>长度</strong>&nbsp;是青蛙跳跃前和跳跃后所在两块石头之间的距离。</p>

<ul>
	<li>更正式的，如果青蛙从&nbsp;<code>stones[i]</code>&nbsp;跳到&nbsp;<code>stones[j]</code>&nbsp;，跳跃的长度为&nbsp;<code>|stones[i] - stones[j]|</code>&nbsp;。</li>
</ul>

<p>一条路径的 <b>代价</b>&nbsp;是这条路径里的&nbsp;<b>最大跳跃长度</b>&nbsp;。</p>

<p>请你返回这只青蛙的 <strong>最小代价</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2498.Frog%20Jump%20II/images/example-1.png" style="width: 600px; height: 219px;" /></p>

<pre>
<b>输入：</b>stones = [0,2,5,6,7]
<b>输出：</b>5
<b>解释：</b>上图展示了一条最优路径。
这条路径的代价是 5 ，是这条路径中的最大跳跃长度。
无法得到一条代价小于 5 的路径，我们返回 5 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2498.Frog%20Jump%20II/images/example-2.png" style="width: 500px; height: 171px;" /></p>

<pre>
<b>输入：</b>stones = [0,3,9]
<b>输出：</b>9
<b>解释：</b>
青蛙可以直接跳到最后一块石头，然后跳回第一块石头。
在这条路径中，每次跳跃长度都是 9 。所以路径代价是 max(9, 9) = 9 。
这是可行路径中的最小代价。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= stones.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= stones[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>stones[0] == 0</code></li>
	<li><code>stones</code>&nbsp;中的元素严格递增。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：脑筋急转弯**

要使得跳跃过程中的每一步的最大跳跃长度最小，我们应该将跳跃过程切分成尽可能多的连续的步骤。通过观察，间隔跳跃可以获取最优解。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `stones` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxJump(self, stones: List[int]) -> int:
        ans = stones[1] - stones[0]
        for i in range(2, len(stones)):
            ans = max(ans, stones[i] - stones[i - 2])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxJump(int[] stones) {
        int ans = stones[1] - stones[0];
        for (int i = 2; i < stones.length; ++i) {
            ans = Math.max(ans, stones[i] - stones[i - 2]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxJump(vector<int>& stones) {
        int ans = stones[1] - stones[0];
        for (int i = 2; i < stones.size(); ++i) ans = max(ans, stones[i] - stones[i - 2]);
        return ans;
    }
};
```

### **Go**

```go
func maxJump(stones []int) int {
	ans := stones[1] - stones[0]
	for i := 2; i < len(stones); i++ {
		ans = max(ans, stones[i]-stones[i-2])
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

### **...**

```

```

<!-- tabs:end -->
