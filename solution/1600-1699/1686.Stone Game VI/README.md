# [1686. 石子游戏 VI](https://leetcode.cn/problems/stone-game-vi)

[English Version](/solution/1600-1699/1686.Stone%20Game%20VI/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和 Bob 轮流玩一个游戏，Alice 先手。</p>

<p>一堆石子里总共有 <code>n</code> 个石子，轮到某个玩家时，他可以 <strong>移出</strong> 一个石子并得到这个石子的价值。Alice 和 Bob 对石子价值有 <strong>不一样的的评判标准</strong> 。双方都知道对方的评判标准。</p>

<p>给你两个长度为 <code>n</code> 的整数数组 <code>aliceValues</code> 和 <code>bobValues</code> 。<code>aliceValues[i]</code> 和 <code>bobValues[i]</code> 分别表示 Alice 和 Bob 认为第 <code>i</code> 个石子的价值。</p>

<p>所有石子都被取完后，得分较高的人为胜者。如果两个玩家得分相同，那么为平局。两位玩家都会采用 <b>最优策略</b> 进行游戏。</p>

<p>请你推断游戏的结果，用如下的方式表示：</p>

<ul>
	<li>如果 Alice 赢，返回 <code>1</code> 。</li>
	<li>如果 Bob 赢，返回 <code>-1</code> 。</li>
	<li>如果游戏平局，返回 <code>0</code> 。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>aliceValues = [1,3], bobValues = [2,1]
<b>输出：</b>1
<strong>解释：</strong>
如果 Alice 拿石子 1 （下标从 0开始），那么 Alice 可以得到 3 分。
Bob 只能选择石子 0 ，得到 2 分。
Alice 获胜。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>aliceValues = [1,2], bobValues = [3,1]
<b>输出：</b>0
<strong>解释：</strong>
Alice 拿石子 0 ， Bob 拿石子 1 ，他们得分都为 1 分。
打平。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>aliceValues = [2,4,3], bobValues = [1,6,7]
<b>输出：</b>-1
<strong>解释：</strong>
不管 Alice 怎么操作，Bob 都可以得到比 Alice 更高的得分。
比方说，Alice 拿石子 1 ，Bob 拿石子 2 ， Alice 拿石子 0 ，Alice 会得到 6 分而 Bob 得分为 7 分。
Bob 会获胜。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == aliceValues.length == bobValues.length</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= aliceValues[i], bobValues[i] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 排序**

选取石头的最优化的策略是，让自己得分最高，同时让对手失分最多。因此，我们创建一个数组 `arr`，其中 `arr[i] = aliceValues[i] + bobValues[i]`，然后对 `arr` 进行降序排序。然后，我们从 `arr` 中取出石头，每次取出两个石头，分别给 Alice 和 Bob，直到 `arr` 中没有石头为止。最后，我们比较 Alice 和 Bob 的得分，得分高的人获胜。

时间复杂度 $O(n\log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `aliceValues` 和 `bobValues` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def stoneGameVI(self, aliceValues: List[int], bobValues: List[int]) -> int:
        arr = [(a + b, i)
               for i, (a, b) in enumerate(zip(aliceValues, bobValues))]
        arr.sort(reverse=True)
        a = sum(aliceValues[v[1]] for i, v in enumerate(arr) if i % 2 == 0)
        b = sum(bobValues[v[1]] for i, v in enumerate(arr) if i % 2 == 1)
        if a > b:
            return 1
        if a < b:
            return -1
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {aliceValues[i] + bobValues[i], i};
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        int a = 0, b = 0;
        for (int i = 0; i < n; ++i) {
            int j = arr[i][1];
            if (i % 2 == 0) {
                a += aliceValues[j];
            } else {
                b += bobValues[j];
            }
        }
        if (a == b) {
            return 0;
        }
        return a > b ? 1 : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int stoneGameVI(vector<int>& aliceValues, vector<int>& bobValues) {
        int n = aliceValues.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {aliceValues[i] + bobValues[i], i};
        }
        sort(arr.rbegin(), arr.rend());
        int a = 0, b = 0;
        for (int i = 0; i < n; ++i) {
            int j = arr[i].second;
            if (i % 2 == 0) {
                a += aliceValues[j];
            } else {
                b += bobValues[j];
            }
        }
        if (a == b) return 0;
        return a > b ? 1 : -1;
    }
};
```

### **Go**

```go
func stoneGameVI(aliceValues []int, bobValues []int) int {
	arr := make([][]int, len(aliceValues))
	for i, a := range aliceValues {
		b := bobValues[i]
		arr[i] = []int{a + b, i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] > arr[j][0] })
	a, b := 0, 0
	for i, v := range arr {
		if i%2 == 0 {
			a += aliceValues[v[1]]
		} else {
			b += bobValues[v[1]]
		}
	}
	if a == b {
		return 0
	}
	if a > b {
		return 1
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
