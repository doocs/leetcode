# [1701. 平均等待时间](https://leetcode.cn/problems/average-waiting-time)

[English Version](/solution/1700-1799/1701.Average%20Waiting%20Time/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个餐厅，只有一位厨师。你有一个顾客数组 <code>customers</code> ，其中 <code>customers[i] = [arrival<sub>i</sub>, time<sub>i</sub>]</code> ：</p>

<ul>
	<li><code>arrival<sub>i</sub></code> 是第 <code>i</code> 位顾客到达的时间，到达时间按 <strong>非递减</strong> 顺序排列。</li>
	<li><code>time<sub>i</sub></code> 是给第 <code>i</code> 位顾客做菜需要的时间。</li>
</ul>

<p>当一位顾客到达时，他将他的订单给厨师，厨师一旦空闲的时候就开始做这位顾客的菜。每位顾客会一直等待到厨师完成他的订单。厨师同时只能做一个人的订单。厨师会严格按照 <strong>订单给他的顺序</strong> 做菜。</p>

<p>请你返回所有顾客需要等待的 <strong>平均 </strong>时间。与标准答案误差在 <code>10<sup>-5</sup></code> 范围以内，都视为正确结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>customers = [[1,2],[2,5],[4,3]]
<b>输出：</b>5.00000
<strong>解释：
</strong>1) 第一位顾客在时刻 1 到达，厨师拿到他的订单并在时刻 1 立马开始做菜，并在时刻 3 完成，第一位顾客等待时间为 3 - 1 = 2 。
2) 第二位顾客在时刻 2 到达，厨师在时刻 3 开始为他做菜，并在时刻 8 完成，第二位顾客等待时间为 8 - 2 = 6 。
3) 第三位顾客在时刻 4 到达，厨师在时刻 8 开始为他做菜，并在时刻 11 完成，第三位顾客等待时间为 11 - 4 = 7 。
平均等待时间为 (2 + 6 + 7) / 3 = 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>customers = [[5,2],[5,4],[10,3],[20,1]]
<b>输出：</b>3.25000
<strong>解释：
</strong>1) 第一位顾客在时刻 5 到达，厨师拿到他的订单并在时刻 5 立马开始做菜，并在时刻 7 完成，第一位顾客等待时间为 7 - 5 = 2 。
2) 第二位顾客在时刻 5 到达，厨师在时刻 7 开始为他做菜，并在时刻 11 完成，第二位顾客等待时间为 11 - 5 = 6 。
3) 第三位顾客在时刻 10 到达，厨师在时刻 11 开始为他做菜，并在时刻 14 完成，第三位顾客等待时间为 14 - 10 = 4 。
4) 第四位顾客在时刻 20 到达，厨师拿到他的订单并在时刻 20 立马开始做菜，并在时刻 21 完成，第四位顾客等待时间为 21 - 20 = 1 。
平均等待时间为 (2 + 6 + 4 + 1) / 4 = 3.25 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= customers.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= arrival<sub>i</sub>, time<sub>i</sub> <= 10<sup>4</sup></code></li>
	<li><code>arrival<sub>i </sub><= arrival<sub>i+1</sub></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们用变量 `tot` 记录顾客的总等待时间，用变量 `t` 记录做完每个顾客的订单的时间，初始值均为 $0$。

遍历顾客数组 `customers`，对于每个顾客：

如果当前时间 `t` 小于等于顾客的到达时间 `customers[i][0]`，说明厨师没有在做菜，那么厨师可以立即开始做菜，做完这道菜的时间为 `t = customers[i][0] + customers[i][1]`，顾客的等待时间为 `customers[i][1]`。

否则，说明厨师正在做菜，那么顾客需要等待厨师做完此前的菜，才能开始做自己的菜，做完这道菜的时间为 `t = t + customers[i][1]`，顾客的等待时间为 `t - customers[i][0]`。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为顾客数组 `customers` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def averageWaitingTime(self, customers: List[List[int]]) -> float:
        tot = t = 0
        for a, b in customers:
            t = max(t, a) + b
            tot += t - a
        return tot / len(customers)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double averageWaitingTime(int[][] customers) {
        double tot = 0;
        int t = 0;
        for (var e : customers) {
            int a = e[0], b = e[1];
            t = Math.max(t, a) + b;
            tot += t - a;
        }
        return tot / customers.length;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double averageWaitingTime(vector<vector<int>>& customers) {
        double tot = 0;
        int t = 0;
        for (auto& e : customers) {
            int a = e[0], b = e[1];
            t = max(t, a) + b;
            tot += t - a;
        }
        return tot / customers.size();
    }
};
```

### **Go**

```go
func averageWaitingTime(customers [][]int) float64 {
	tot, t := 0, 0
	for _, e := range customers {
		a, b := e[0], e[1]
		t = max(t, a) + b
		tot += t - a
	}
	return float64(tot) / float64(len(customers))
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
