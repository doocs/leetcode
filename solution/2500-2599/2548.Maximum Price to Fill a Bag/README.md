# [2548. Maximum Price to Fill a Bag](https://leetcode.cn/problems/maximum-price-to-fill-a-bag)

[English Version](/solution/2500-2599/2548.Maximum%20Price%20to%20Fill%20a%20Bag/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a 2D integer array <code>items</code> where <code>items[i] = [price<sub>i</sub>, weight<sub>i</sub>]</code> denotes the price and weight of the <code>i<sup>th</sup></code> item, respectively.</p>

<p>You are also given a <strong>positive</strong> integer <code>capacity</code>.</p>

<p>Each item can be divided into two items with ratios <code>part1</code> and <code>part2</code>, where <code>part1 + part2 == 1</code>.</p>

<ul>
	<li>The weight of the first item is <code>weight<sub>i</sub> * part1</code> and the price of the first item is <code>price<sub>i</sub> * part1</code>.</li>
	<li>Similarly, the weight of the second item is <code>weight<sub>i</sub> * part2</code> and the price of the second item is <code>price<sub>i</sub> * part2</code>.</li>
</ul>

<p>Return <em><strong>the maximum total price</strong> to fill a bag of capacity</em> <code>capacity</code> <em>with given items</em>. If it is impossible to fill a bag return <code>-1</code>. Answers within <code>10<sup>-5</sup></code> of the <strong>actual answer</strong> will be considered accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> items = [[50,1],[10,8]], capacity = 5
<strong>Output:</strong> 55.00000
<strong>Explanation:</strong> 
We divide the 2<sup>nd</sup> item into two parts with part1 = 0.5 and part2 = 0.5.
The price and weight of the 1<sup>st</sup> item are 5, 4. And similarly, the price and the weight of the 2<sup>nd</sup> item are 5, 4.
The array items after operation becomes [[50,1],[5,4],[5,4]]. 
To fill a bag with capacity 5 we take the 1<sup>st</sup> element with a price of 50 and the 2<sup>nd</sup> element with a price of 5.
It can be proved that 55.0 is the maximum total price that we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> items = [[100,30]], capacity = 50
<strong>Output:</strong> -1.00000
<strong>Explanation:</strong> It is impossible to fill a bag with the given item.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= items.length &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i].length == 2</code></li>
	<li><code>1 &lt;= price<sub>i</sub>, weight<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= capacity &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 排序**

将物品按照单位价格从大到小排序，然后依次取出物品，直到背包装满。

若最后背包未装满，则返回 $-1$，否则返回总价格。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为物品数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxPrice(self, items: List[List[int]], capacity: int) -> float:
        ans = 0
        for p, w in sorted(items, key=lambda x: x[1] / x[0]):
            v = min(w, capacity)
            ans += v / w * p
            capacity -= v
        return -1 if capacity else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double maxPrice(int[][] items, int capacity) {
        Arrays.sort(items, (a, b) -> a[1] * b[0] - a[0] * b[1]);
        double ans = 0;
        for (var e : items) {
            int p = e[0], w = e[1];
            int v = Math.min(w, capacity);
            ans += v * 1.0 / w * p;
            capacity -= v;
        }
        return capacity > 0 ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double maxPrice(vector<vector<int>>& items, int capacity) {
        sort(items.begin(), items.end(), [&](const auto& a, const auto& b) { return a[1] * b[0] < a[0] * b[1];});
        double ans = 0;
        for (auto& e : items) {
            int p = e[0], w = e[1];
            int v = min(w, capacity);
            ans += v * 1.0 / w * p;
            capacity -= v;
        }
        return capacity > 0 ? -1 : ans;
    }
};
```

### **Go**

```go
func maxPrice(items [][]int, capacity int) (ans float64) {
	sort.Slice(items, func(i, j int) bool { return items[i][1]*items[j][0] < items[i][0]*items[j][1] })
	for _, e := range items {
		p, w := e[0], e[1]
		v := min(w, capacity)
		ans += float64(v) / float64(w) * float64(p)
		capacity -= v
	}
	if capacity > 0 {
		return -1
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maxPrice(items: number[][], capacity: number): number {
    items.sort((a, b) => a[1] * b[0] - a[0] * b[1]);
    let ans = 0;
    for (const [p, w] of items) {
        const v = Math.min(w, capacity);
        ans += (v / w) * p;
        capacity -= v;
    }
    return capacity ? -1 : ans;
}
```

### **...**

```

```

<!-- tabs:end -->
