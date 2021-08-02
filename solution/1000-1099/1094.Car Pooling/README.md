# [1094. 拼车](https://leetcode-cn.com/problems/car-pooling)

[English Version](/solution/1000-1099/1094.Car%20Pooling/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设你是一位顺风车司机，车上最初有&nbsp;<code>capacity</code>&nbsp;个空座位可以用来载客。由于道路的限制，车&nbsp;<strong>只能&nbsp;</strong>向一个方向行驶（也就是说，<strong>不允许掉头或改变方向</strong>，你可以将其想象为一个向量）。</p>

<p>这儿有一份乘客行程计划表&nbsp;<code>trips[][]</code>，其中&nbsp;<code>trips[i] = [num_passengers, start_location, end_location]</code>&nbsp;包含了第 <code>i</code> 组乘客的行程信息：</p>

<ul>
	<li>必须接送的乘客数量；</li>
	<li>乘客的上车地点；</li>
	<li>以及乘客的下车地点。</li>
</ul>

<p>这些给出的地点位置是从你的&nbsp;<strong>初始&nbsp;</strong>出发位置向前行驶到这些地点所需的距离（它们一定在你的行驶方向上）。</p>

<p>请你根据给出的行程计划表和车子的座位数，来判断你的车是否可以顺利完成接送所有乘客的任务（当且仅当你可以在所有给定的行程中接送所有乘客时，返回&nbsp;<code>true</code>，否则请返回 <code>false</code>）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>trips = [[2,1,5],[3,3,7]], capacity = 4
<strong>输出：</strong>false
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>trips = [[2,1,5],[3,3,7]], capacity = 5
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>trips = [[2,1,5],[3,5,7]], capacity = 3
<strong>输出：</strong>true
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>你可以假设乘客会自觉遵守 &ldquo;<strong>先下后上</strong>&rdquo; 的良好素质</li>
	<li><code>trips.length &lt;= 1000</code></li>
	<li><code>trips[i].length == 3</code></li>
	<li><code>1 &lt;= trips[i][0] &lt;= 100</code></li>
	<li><code>0 &lt;= trips[i][1] &lt; trips[i][2] &lt;= 1000</code></li>
	<li><code>1 &lt;=&nbsp;capacity &lt;= 100000</code></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

差分数组。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        delta = [0] * 1001
        for num, start, end in trips:
            delta[start] += num
            delta[end] -= num
        cur = 0
        for num in delta:
            cur += num
            if cur > capacity:
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] delta = new int[1001];
        for (int[] trip : trips) {
            int num = trip[0], start = trip[1], end = trip[2];
            delta[start] += num;
            delta[end] -= num;
        }
        int cur = 0;
        for (int num : delta) {
            cur += num;
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} trips
 * @param {number} capacity
 * @return {boolean}
 */
 var carPooling = function(trips, capacity) {
    let delta = new Array();
    for (let trip of trips) {
        let [num, start, end] = trip;
        delta[start] = (delta[start] || 0) + num;
        delta[end] = (delta[end] || 0) - num;
    }
    let total = 0;
    for (let i = 0; i < delta.length; i++) {
        let cur = delta[i];
        if (cur == undefined) continue;
        total += cur;
        if (total > capacity) return false;
    }
    return true;
};
```

### **C++**

```cpp
class Solution {
public:
    bool carPooling(vector<vector<int>>& trips, int capacity) {
        vector<int> delta(1001);
        for (auto &trip : trips) {
            int num = trip[0], start = trip[1], end = trip[2];
            delta[start] += num;
            delta[end] -= num;
        }
        int cur = 0;
        for (auto &num : delta) {
            cur += num;
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func carPooling(trips [][]int, capacity int) bool {
	delta := make([]int, 1010)
	for _, trip := range trips {
		num, start, end := trip[0], trip[1], trip[2]
		delta[start] += num
		delta[end] -= num
	}
	cur := 0
	for _, num := range delta {
		cur += num
		if cur > capacity {
			return false
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
