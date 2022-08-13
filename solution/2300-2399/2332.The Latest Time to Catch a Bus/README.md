# [2332. 坐上公交的最晚时间](https://leetcode.cn/problems/the-latest-time-to-catch-a-bus)

[English Version](/solution/2300-2399/2332.The%20Latest%20Time%20to%20Catch%20a%20Bus/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>buses</code>&nbsp;，其中&nbsp;<code>buses[i]</code>&nbsp;表示第 <code>i</code>&nbsp;辆公交车的出发时间。同时给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>m</code>&nbsp;的整数数组&nbsp;<code>passengers</code>&nbsp;，其中&nbsp;<code>passengers[j]</code>&nbsp;表示第&nbsp;<code>j</code>&nbsp;位乘客的到达时间。所有公交车出发的时间互不相同，所有乘客到达的时间也互不相同。</p>

<p>给你一个整数&nbsp;<code>capacity</code>&nbsp;，表示每辆公交车&nbsp;<strong>最多</strong>&nbsp;能容纳的乘客数目。</p>

<p>每位乘客都会搭乘下一辆有座位的公交车。如果你在 <code>y</code>&nbsp;时刻到达，公交在&nbsp;<code>x</code>&nbsp;时刻出发，满足&nbsp;<code>y &lt;= x</code>&nbsp;&nbsp;且公交没有满，那么你可以搭乘这一辆公交。<strong>最早</strong>&nbsp;到达的乘客优先上车。</p>

<p>返回你可以搭乘公交车的最晚到达公交站时间。你 <strong>不能</strong>&nbsp;跟别的乘客同时刻到达。</p>

<p><strong>注意：</strong>数组&nbsp;<code>buses</code> 和&nbsp;<code>passengers</code>&nbsp;不一定是有序的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>buses = [10,20], passengers = [2,17,18,19], capacity = 2
<b>输出：</b>16
<strong>解释：</strong>
第 1 辆公交车载着第 1 位乘客。
第 2 辆公交车载着你和第 2 位乘客。
注意你不能跟其他乘客同一时间到达，所以你必须在第二位乘客之前到达。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>buses = [20,30,10], passengers = [19,13,26,4,25,11,21], capacity = 2
<b>输出：</b>20
<b>解释：</b>
第 1 辆公交车载着第 4 位乘客。
第 2 辆公交车载着第 6 位和第 2 位乘客。
第 3 辆公交车载着第 1 位乘客和你。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == buses.length</code></li>
	<li><code>m == passengers.length</code></li>
	<li><code>1 &lt;= n, m, capacity &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= buses[i], passengers[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>buses</code>&nbsp;中的元素 <strong>互不相同&nbsp;</strong>。</li>
	<li><code>passengers</code>&nbsp;中的元素 <strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

先排序，然后用双指针模拟乘客上车的过程：遍历公交车 $bus$，乘客遵循“先到先上车”的原则。

模拟结束后，判断最后一班公交车是否还有空位：

-   若有空位，我们可以在公交车发车时 $bus[bus.length-1]$ 到达公交站；若此时有人，可以顺着往前找到没人到达的时刻；
-   若无空位，我们可以找到上一个上车的乘客，顺着他往前找到没人到达的时刻。

时间复杂度 $O(nlogn+mlogm)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def latestTimeCatchTheBus(
        self, buses: List[int], passengers: List[int], capacity: int
    ) -> int:
        buses.sort()
        passengers.sort()
        j = 0
        for t in buses:
            c = capacity
            while c and j < len(passengers) and passengers[j] <= t:
                c, j = c - 1, j + 1
        j -= 1
        ans = buses[-1] if c else passengers[j]
        while ~j and passengers[j] == ans:
            ans, j = ans - 1, j - 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int j = 0, c = 0;
        for (int t : buses) {
            c = capacity;
            while (c > 0 && j < passengers.length && passengers[j] <= t) {
                --c;
                ++j;
            }
        }
        --j;
        int ans = c > 0 ? buses[buses.length - 1] : passengers[j];
        while (j >= 0 && ans == passengers[j]) {
            --ans;
            --j;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int latestTimeCatchTheBus(vector<int>& buses, vector<int>& passengers, int capacity) {
        sort(buses.begin(), buses.end());
        sort(passengers.begin(), passengers.end());
        int j = 0, c = 0;
        for (int t : buses) {
            c = capacity;
            while (c && j < passengers.size() && passengers[j] <= t) --c, ++j;
        }
        --j;
        int ans = c ? buses[buses.size() - 1] : passengers[j];
        while (~j && ans == passengers[j]) --j, --ans;
        return ans;
    }
};
```

### **Go**

```go
func latestTimeCatchTheBus(buses []int, passengers []int, capacity int) int {
	sort.Ints(buses)
	sort.Ints(passengers)
	j, c := 0, 0
	for _, t := range buses {
		c = capacity
		for c > 0 && j < len(passengers) && passengers[j] <= t {
			j++
			c--
		}
	}
	j--
	ans := buses[len(buses)-1]
	if c == 0 {
		ans = passengers[j]
	}
	for j >= 0 && ans == passengers[j] {
		ans--
		j--
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
