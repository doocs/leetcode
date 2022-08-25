# [277. 搜寻名人](https://leetcode.cn/problems/find-the-celebrity)

[English Version](/solution/0200-0299/0277.Find%20the%20Celebrity/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设你是一个专业的狗仔，参加了一个 <code>n</code> 人派对，其中每个人被从 <code>0</code> 到 <code>n - 1</code> 标号。在这个派对人群当中可能存在一位 “名人”。所谓 “名人” 的定义是：其他所有 <code>n - 1</code> 个人都认识他/她，而他/她并不认识其他任何人。</p>

<p>现在你想要确认这个 “名人” 是谁，或者确定这里没有 “名人”。而你唯一能做的就是问诸如 “A 你好呀，请问你认不认识 B呀？” 的问题，以确定 A 是否认识 B。你需要在（渐近意义上）尽可能少的问题内来确定这位 “名人” 是谁（或者确定这里没有 “名人”）。</p>

<p>在本题中，你可以使用辅助函数 <code>bool knows(a, b)</code> 获取到 A 是否认识 B。请你来实现一个函数 <code>int findCelebrity(n)</code>。</p>

<p>派对最多只会有一个 “名人” 参加。若 “名人” 存在，请返回他/她的编号；若 “名人” 不存在，请返回 <code>-1</code>。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0277.Find%20the%20Celebrity/images/277_example_1_bold.png" style="height: 181px; width: 186px;" /></p>

<pre>
<strong>输入: </strong>graph = [
  [1,1,0],
  [0,1,0],
  [1,1,1]
]
<strong>输出: </strong>1
<strong>解释: </strong>有编号分别为 0、1 和 2 的三个人。graph[i][j] = 1 代表编号为 i 的人认识编号为 j 的人，而 graph[i][j] = 0 则代表编号为 i 的人不认识编号为 j 的人。“名人” 是编号 1 的人，因为 0 和 2 均认识他/她，但 1 不认识任何人。
</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0277.Find%20the%20Celebrity/images/277_example_2.png" style="height: 192px; width: 193px;" /></p>

<pre>
<strong>输入: </strong>graph = [
  [1,0,1],
  [1,1,0],
  [0,1,1]
]
<strong>输出: </strong>-1
<strong>解释: </strong>没有 “名人”
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == graph.length</code></li>
	<li><code>n == graph[i].length</code></li>
	<li><code>2 <= n <= 100</code></li>
	<li><code>graph[i][j]</code> 是 <code>0</code> 或 <code>1</code>.</li>
	<li><code>graph[i][i] == 1</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>如果允许调用 API <code>knows</code> 的最大次数为 <code>3 * n</code> ，你可以设计一个不超过最大调用次数的解决方案吗？</p>

<ol>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：O(n) 遍历**

经过验证，若暴力遍历，调用 $O(n^2)$ 次 $knows$ 方法，会报 TLE 错误。因此，我们需要寻找更优的解法。

要找出 $n$ 个人中的名人，题目给我们的关键信息是：1. 名人不认识其他所有人；2. 其他所有人都认识名人。

那么，我们初始时假定名人 $ans=0$。然后在 $[1,n)$ 范围内遍历 $i$，若 $ans$ 认识 $i$，说明 $ans$ 不是我们要找的名人，此时我们可以直接将 $ans$ 更新为 $i$。

为什么呢？我们来举个实际的例子。

```bash
ans = 0
for i in [1,n) {
	if (ans knows i) {
		ans = i
	}
}

ans = 0

ans not knows 1
ans not knows 2
ans knows 3
ans = 3

ans not knows 4
ans not knows 5
ans not knows 6
ans = 6
```

这里 $ans$ 认识 $3$，说明 $ans$ 不是名人（即 $0$ 不是名人），那么名人会是 $1$ 或者 $2$ 吗？不会！因为若 $1$ 或者 $2$ 是名人，那么 $0$ 应该认识 $1$ 或者 $2$ 才对，与前面的例子冲突。因此，我们可以直接将 $ans$ 更新为 $i$。

我们找出 $ans$ 之后，接下来再遍历一遍，判断 $ans$ 是否满足名人的条件。若不满足，返回 $-1$。

否则遍历结束，返回 $ans$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# The knows API is already defined for you.
# return a bool, whether a knows b
# def knows(a: int, b: int) -> bool:

class Solution:
    def findCelebrity(self, n: int) -> int:
        ans = 0
        for i in range(1, n):
            if knows(ans, i):
                ans = i
        for i in range(n):
            if ans != i:
                if knows(ans, i) or not knows(i, ans):
                    return -1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            if (knows(ans, i)) {
                ans = i;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (ans != i) {
                if (knows(ans, i) || !knows(i, ans)) {
                    return -1;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
/* The knows API is defined for you.
      bool knows(int a, int b); */

class Solution {
public:
    int findCelebrity(int n) {
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            if (knows(ans, i)) {
                ans = i;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (ans != i) {
                if (knows(ans, i) || !knows(i, ans)) {
                    return -1;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
/**
 * The knows API is already defined for you.
 *     knows := func(a int, b int) bool
 */
func solution(knows func(a int, b int) bool) func(n int) int {
	return func(n int) int {
		ans := 0
		for i := 1; i < n; i++ {
			if knows(ans, i) {
				ans = i
			}
		}
		for i := 0; i < n; i++ {
			if ans != i {
				if knows(ans, i) || !knows(i, ans) {
					return -1
				}
			}
		}
		return ans
	}
}
```

### **...**

```

```

<!-- tabs:end -->
