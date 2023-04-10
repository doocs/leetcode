# [1029. 两地调度](https://leetcode.cn/problems/two-city-scheduling)

[English Version](/solution/1000-1099/1029.Two%20City%20Scheduling/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>公司计划面试 <code>2n</code> 人。给你一个数组 <code>costs</code> ，其中 <code>costs[i] = [aCost<sub>i</sub>, bCost<sub>i</sub>]</code> 。第 <code>i</code> 人飞往 <code>a</code> 市的费用为 <code>aCost<sub>i</sub></code> ，飞往 <code>b</code> 市的费用为 <code>bCost<sub>i</sub></code> 。</p>

<p>返回将每个人都飞到 <code>a</code> 、<code>b</code> 中某座城市的最低费用，要求每个城市都有 <code>n</code> 人抵达<strong>。</strong></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>costs = [[10,20],[30,200],[400,50],[30,20]]
<strong>输出：</strong>110
<strong>解释：</strong>
第一个人去 a 市，费用为 10。
第二个人去 a 市，费用为 30。
第三个人去 b 市，费用为 50。
第四个人去 b 市，费用为 20。

最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
<strong>输出：</strong>1859
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
<strong>输出：</strong>3086
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 * n == costs.length</code></li>
	<li><code>2 <= costs.length <= 100</code></li>
	<li><code>costs.length</code> 为偶数</li>
	<li><code>1 <= aCost<sub>i</sub>, bCost<sub>i</sub> <= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心**

我们不妨先假设所有人都去 $b$ 市，然后我们要从中选出 $n$ 个人去 $a$ 市，使得总费用最小。如果一个人去 $a$ 市的费用比去 $b$ 市的费用小，我们把这个人从 $b$ 市调到 $a$ 市，这样总费用就会减少。因此，我们可以将所有人按照去 $a$ 市的费用与去 $b$ 市的费用的差值从小到大排序，然后选出前 $n$ 个人去 $a$ 市，剩下的人去 $b$ 市，这样总费用就是最小的。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `costs` 的长度。

相似题目：

-   [2611. 老鼠和奶酪](/solution/2600-2699/2611.Mice%20and%20Cheese/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def twoCitySchedCost(self, costs: List[List[int]]) -> int:
        costs.sort(key=lambda x: x[0] - x[1])
        n = len(costs) >> 1
        return sum(costs[i][0] + costs[i + n][1] for i in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b) -> { return a[0] - a[1] - (b[0] - b[1]); });
        int ans = 0;
        int n = costs.length >> 1;
        for (int i = 0; i < n; ++i) {
            ans += costs[i][0] + costs[i + n][1];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int twoCitySchedCost(vector<vector<int>>& costs) {
        sort(costs.begin(), costs.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[0] - a[1] < b[0] - b[1];
        });
        int n = costs.size() / 2;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += costs[i][0] + costs[i + n][1];
        }
        return ans;
    }
};
```

### **Go**

```go
func twoCitySchedCost(costs [][]int) (ans int) {
	sort.Slice(costs, func(i, j int) bool {
		return costs[i][0]-costs[i][1] < costs[j][0]-costs[j][1]
	})
	n := len(costs) >> 1
	for i, a := range costs[:n] {
		ans += a[0] + costs[i+n][1]
	}
	return
}
```

### **TypeScript**

```ts
function twoCitySchedCost(costs: number[][]): number {
    costs.sort((a, b) => a[0] - a[1] - (b[0] - b[1]));
    const n = costs.length >> 1;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += costs[i][0] + costs[i + n][1];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
