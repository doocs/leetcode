# [1025. 除数博弈](https://leetcode.cn/problems/divisor-game)

[English Version](/solution/1000-1099/1025.Divisor%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。</p>

<p>最初，黑板上有一个数字&nbsp;<code>n</code>&nbsp;。在每个玩家的回合，玩家需要执行以下操作：</p>

<ul>
	<li>选出任一&nbsp;<code>x</code>，满足&nbsp;<code>0 &lt; x &lt; n</code>&nbsp;且&nbsp;<code>n % x == 0</code>&nbsp;。</li>
	<li>用 <code>n - x</code>&nbsp;替换黑板上的数字&nbsp;<code>n</code> 。</li>
</ul>

<p>如果玩家无法执行这些操作，就会输掉游戏。</p>

<p><em>只有在爱丽丝在游戏中取得胜利时才返回&nbsp;<code>true</code>&nbsp;。假设两个玩家都以最佳状态参与游戏。</em></p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>true
<strong>解释：</strong>爱丽丝选择 1，鲍勃无法进行操作。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>false
<strong>解释：</strong>爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学归纳法**

-   当 $n=1$，先手败
-   当 $n=2$，先手拿 $1$，剩下 $1$，后手败，先手胜
-   当 $n=3$，先手拿 $1$，剩下 $2$，后手胜，先手败
-   当 $n=4$，先手拿 $1$，剩下 $3$，后手败，先手胜
-   ...

猜想，当 $n$ 为奇数时，先手败；当 $n$ 为偶数时，先手胜。

证明：

1. 若 $n=1$ 或 $n=2$，结论成立；
1. 若 $n \gt 2$，假设 $n \le k$ 时，该结论成立，则 $n=k+1$ 时：
    - 若 $k+1$ 为奇数，由于 $x$ 是 $k+1$ 的因数，那么 $x$ 只可能是奇数，因此 $k+1-x$ 为偶数，后手胜，先手败；
    - 若 $k+1$ 为偶数，此时 $x$ 既可以是奇数 $1$，也可以是偶数，若 $x$ 取奇数，那么 $k+1-x$ 为奇数，后手败，先手胜。

综上，当 $n$ 为奇数时，先手败；当 $n$ 为偶数时，先手胜。结论正确。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def divisorGame(self, n: int) -> bool:
        return n % 2 == 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool divisorGame(int n) {
        return n % 2 == 0;
    }
};
```

### **Go**

```go
func divisorGame(n int) bool {
	return n%2 == 0
}
```

### **...**

```

```

<!-- tabs:end -->
