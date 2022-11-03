# [754. 到达终点数字](https://leetcode.cn/problems/reach-a-number)

[English Version](/solution/0700-0799/0754.Reach%20a%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一根无限长的数轴上，你站在<code>0</code>的位置。终点在<code>target</code>的位置。</p>

<p>你可以做一些数量的移动 <code>numMoves</code> :</p>

<ul>
	<li>每次你可以选择向左或向右移动。</li>
	<li>第 <code>i</code>&nbsp;次移动（从 &nbsp;<code>i == 1</code>&nbsp;开始，到&nbsp;<code>i == numMoves</code> ），在选择的方向上走 <code>i</code>&nbsp;步。</li>
</ul>

<p>给定整数&nbsp;<code>target</code> ，返回 <em>到达目标所需的 <strong>最小&nbsp;</strong>移动次数(即最小 <code>numMoves</code> )&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> target = 2
<strong>输出:</strong> 3
<strong>解释:</strong>
第一次移动，从 0 到 1 。
第二次移动，从 1 到 -1 。
第三次移动，从 -1 到 2 。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> target = 3
<strong>输出:</strong> 2
<strong>解释:</strong>
第一次移动，从 0 到 1 。
第二次移动，从 1 到 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><code>target != 0</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学分析**

由于对称性，每次可以选择向左或向右移动，因此，我们可以将 $target$ 统一取绝对值。

定义 $s$ 表示当前所处的位置，用变量 $k$ 记录移动的次数。初始时 $s$ 和 $k$ 均为 $0$。

我们将 $s$ 一直循环累加，直到满足 $s\ge target$ 并且 $(s-target)\mod 2 = 0$，此时的移动次数 $k$ 就是答案，直接返回。

为什么？因为如果 $s\ge target$ 且 $(s-target)\mod 2 = 0$，我们只需要把前面 $\frac{s-target}{2}$ 这个正整数变为负数，就能使得 $s$ 与 $target$ 相等。正整数变负数的过程，实际上是将移动的方向改变，但实际移动次数仍然不变。

时间复杂度 $O(\sqrt{\left | target \right | })$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reachNumber(self, target: int) -> int:
        target = abs(target)
        s = k = 0
        while 1:
            if s >= target and (s - target) % 2 == 0:
                return k
            k += 1
            s += k
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int s = 0, k = 0;
        while (true) {
            if (s >= target && (s - target) % 2 == 0) {
                return k;
            }
            ++k;
            s += k;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int reachNumber(int target) {
        target = abs(target);
        int s = 0, k = 0;
        while (1) {
            if (s >= target && (s - target) % 2 == 0) return k;
            ++k;
            s += k;
        }
    }
};
```

### **Go**

```go
func reachNumber(target int) int {
	if target < 0 {
		target = -target
	}
	var s, k int
	for {
		if s >= target && (s-target)%2 == 0 {
			return k
		}
		k++
		s += k
	}
}
```

### **JavaScript**

```js
/**
 * @param {number} target
 * @return {number}
 */
var reachNumber = function (target) {
    target = Math.abs(target);
    let [s, k] = [0, 0];
    while (1) {
        if (s >= target && (s - target) % 2 == 0) {
            return k;
        }
        ++k;
        s += k;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
