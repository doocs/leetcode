# [1753. 移除石子的最大得分](https://leetcode.cn/problems/maximum-score-from-removing-stones)

[English Version](/solution/1700-1799/1753.Maximum%20Score%20From%20Removing%20Stones/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你正在玩一个单人游戏，面前放置着大小分别为 <code>a</code>​​​​​​、<code>b</code> 和 <code>c</code>​​​​​​ 的 <strong>三堆</strong> 石子。</p>

<p>每回合你都要从两个 <strong>不同的非空堆</strong> 中取出一颗石子，并在得分上加 <code>1</code> 分。当存在 <strong>两个或更多</strong> 的空堆时，游戏停止。</p>

<p>给你三个整数 <code>a</code> 、<code>b</code> 和 <code>c</code> ，返回可以得到的 <strong>最大分数</strong> 。</p>
 

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = 2, b = 4, c = 6
<strong>输出：</strong>6
<strong>解释：</strong>石子起始状态是 (2, 4, 6) ，最优的一组操作是：
- 从第一和第三堆取，石子状态现在是 (1, 4, 5)
- 从第一和第三堆取，石子状态现在是 (0, 4, 4)
- 从第二和第三堆取，石子状态现在是 (0, 3, 3)
- 从第二和第三堆取，石子状态现在是 (0, 2, 2)
- 从第二和第三堆取，石子状态现在是 (0, 1, 1)
- 从第二和第三堆取，石子状态现在是 (0, 0, 0)
总分：6 分 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = 4, b = 4, c = 6
<strong>输出：</strong>7
<strong>解释：</strong>石子起始状态是 (4, 4, 6) ，最优的一组操作是：
- 从第一和第二堆取，石子状态现在是 (3, 3, 6)
- 从第一和第三堆取，石子状态现在是 (2, 3, 5)
- 从第一和第三堆取，石子状态现在是 (1, 3, 4)
- 从第一和第三堆取，石子状态现在是 (0, 3, 3)
- 从第二和第三堆取，石子状态现在是 (0, 2, 2)
- 从第二和第三堆取，石子状态现在是 (0, 1, 1)
- 从第二和第三堆取，石子状态现在是 (0, 0, 0)
总分：7 分 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>a = 1, b = 8, c = 8
<strong>输出：</strong>8
<strong>解释：</strong>最优的一组操作是连续从第二和第三堆取 8 回合，直到将它们取空。
注意，由于第二和第三堆已经空了，游戏结束，不能继续从第一堆中取石子。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= a, b, c <= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 模拟**

每次贪心地从最大的两堆石子中取石头，直到至少有两堆石子为空。

时间复杂度 $O(n)$，其中 $n$ 为石子总数。

**方法二：贪心 + 数学**

我们不妨设 $a \le b \le c$，那么：

-   当 $a + b \le c$ 时，我们可以先从 $a$, $c$ 两堆中取石头，得到分数 $a$；再从 $b$, $c$ 两堆中取石头，得到分数 $b$，总分数为 $a + b$；
-   当 $a + b \gt c$ 时，这时我们每次会从 $c$ 以及 $a$ 和 $b$ 中较大的那一堆中取石头，最终将 $c$ 取空。此时 $a$ 和 $b$ 的大小差最多为 $1$。我们再从 $a$, $b$ 两堆中取石头，直到不能取为止，总分数为 $\left \lfloor \frac{a + b + c}{2}  \right \rfloor$。

时间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumScore(self, a: int, b: int, c: int) -> int:
        s = sorted([a, b, c])
        ans = 0
        while s[1]:
            ans += 1
            s[1] -= 1
            s[2] -= 1
            s.sort()
        return ans
```

```python
class Solution:
    def maximumScore(self, a: int, b: int, c: int) -> int:
        a, b, c = sorted([a, b, c])
        if a + b < c:
            return a + b
        return (a + b + c) >> 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumScore(int a, int b, int c) {
        int[] s = new int[] {a, b, c};
        Arrays.sort(s);
        int ans = 0;
        while (s[1] > 0) {
            ++ans;
            s[1]--;
            s[2]--;
            Arrays.sort(s);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maximumScore(int a, int b, int c) {
        int[] s = new int[] {a, b, c};
        Arrays.sort(s);
        if (s[0] + s[1] < s[2]) {
            return s[0] + s[1];
        }
        return (a + b + c) >> 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumScore(int a, int b, int c) {
        vector<int> s = {a, b, c};
        sort(s.begin(), s.end());
        int ans = 0;
        while (s[1]) {
            ++ans;
            s[1]--;
            s[2]--;
            sort(s.begin(), s.end());
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maximumScore(int a, int b, int c) {
        vector<int> s = {a, b, c};
        sort(s.begin(), s.end());
        if (s[0] + s[1] < s[2]) return s[0] + s[1];
        return (a + b + c) >> 1;
    }
};
```

### **Go**

```go
func maximumScore(a int, b int, c int) (ans int) {
	s := []int{a, b, c}
	sort.Ints(s)
	for s[1] > 0 {
		ans++
		s[1]--
		s[2]--
		sort.Ints(s)
	}
	return
}
```

```go
func maximumScore(a int, b int, c int) int {
	s := []int{a, b, c}
	sort.Ints(s)
	if s[0]+s[1] < s[2] {
		return s[0] + s[1]
	}
	return (a + b + c) >> 1
}
```

### **...**

```

```

<!-- tabs:end -->
