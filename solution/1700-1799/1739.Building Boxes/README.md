# [1739. 放置盒子](https://leetcode.cn/problems/building-boxes)

[English Version](/solution/1700-1799/1739.Building%20Boxes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个立方体房间，其长度、宽度和高度都等于 <code>n</code> 个单位。请你在房间里放置 <code>n</code> 个盒子，每个盒子都是一个单位边长的立方体。放置规则如下：</p>

<ul>
	<li>你可以把盒子放在地板上的任何地方。</li>
	<li>如果盒子 <code>x</code> 需要放置在盒子 <code>y</code> 的顶部，那么盒子 <code>y</code> 竖直的四个侧面都 <strong>必须</strong> 与另一个盒子或墙相邻。</li>
</ul>

<p>给你一个整数 <code>n</code> ，返回接触地面的盒子的 <strong>最少</strong> 可能数量<em>。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1739.Building%20Boxes/images/3-boxes.png" style="width: 135px; height: 143px;" /></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>3
<strong>解释：</strong>上图是 3 个盒子的摆放位置。
这些盒子放在房间的一角，对应左侧位置。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1739.Building%20Boxes/images/4-boxes.png" style="width: 135px; height: 179px;" /></p>

<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>3
<strong>解释：</strong>上图是 3 个盒子的摆放位置。
这些盒子放在房间的一角，对应左侧位置。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1739.Building%20Boxes/images/10-boxes.png" style="width: 271px; height: 257px;" /></p>

<pre>
<strong>输入：</strong>n = 10
<strong>输出：</strong>6
<strong>解释：</strong>上图是 10 个盒子的摆放位置。
这些盒子放在房间的一角，对应后方位置。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学规律**

根据题目描述，层数最高的盒子需要放在墙角，并且盒子的摆放呈阶梯状，这样可以使得接触地面的盒子数量最少。

假设盒子摆放 $k$ 层，从上到下，每一层如果摆满，那么个数分别是 $1, 1+2, 1+2+3, \cdots, 1+2+\cdots+k$。

如果此时盒子还有剩余，那么可以从最低一层继续摆放，假设摆放 $i$ 个，那么累计可摆放的盒子个数为 $1+2+\cdots+i$。

时间复杂度 $O(\sqrt{n})$，空间复杂度 $O(1)$。其中 $n$ 为题目给定的盒子数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumBoxes(self, n: int) -> int:
        s, k = 0, 1
        while s + k * (k + 1) // 2 <= n:
            s += k * (k + 1) // 2
            k += 1
        k -= 1
        ans = k * (k + 1) // 2
        k = 1
        while s < n:
            ans += 1
            s += k
            k += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumBoxes(int n) {
        int s = 0, k = 1;
        while (s + k * (k + 1) / 2 <= n) {
            s += k * (k + 1) / 2;
            ++k;
        }
        --k;
        int ans = k * (k + 1) / 2;
        k = 1;
        while (s < n) {
            ++ans;
            s += k;
            ++k;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumBoxes(int n) {
        int s = 0, k = 1;
        while (s + k * (k + 1) / 2 <= n) {
            s += k * (k + 1) / 2;
            ++k;
        }
        --k;
        int ans = k * (k + 1) / 2;
        k = 1;
        while (s < n) {
            ++ans;
            s += k;
            ++k;
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumBoxes(n int) int {
	s, k := 0, 1
	for s+k*(k+1)/2 <= n {
		s += k * (k + 1) / 2
		k++
	}
	k--
	ans := k * (k + 1) / 2
	k = 1
	for s < n {
		ans++
		s += k
		k++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
