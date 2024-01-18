# [2548. 填满背包的最大价格](https://leetcode.cn/problems/maximum-price-to-fill-a-bag)

[English Version](/solution/2500-2599/2548.Maximum%20Price%20to%20Fill%20a%20Bag/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二维整数数组 <code>items</code> ，其中 <code>items[i] = [price<sub>i</sub>, weight<sub>i</sub>]</code> 表示第 <code>i</code> 个物品的价格和重量。</p>

<p>还给定一个 <strong>正</strong> 整数容量 <code>capacity</code> 。</p>

<p>每个物品可以分成两个部分，比率为 <code>part1</code> 和 <code>part2</code> ，其中 <code>part1 + part2 == 1</code> 。</p>

<ul>
	<li>第一个物品的重量是 <code>weighti * part1</code> ，价格是 <code>pricei * part1</code> 。</li>
	<li>同样，第二个物品的重量是 <code>weighti * part2</code>&nbsp;，价格是 <code>pricei * part2</code>&nbsp;。</li>
</ul>

<p>使用给定的物品，返回填满容量为 <code>capacity</code> 的背包所需的 <strong>最大总价格</strong> 。如果无法填满背包，则返回 <code>-1</code> 。与实际答案的差距在&nbsp;<code>10<sup>-5</sup></code>&nbsp;以内的 <strong>实际答案</strong> 将被视为接受。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<b>输入：</b>items = [[50,1],[10,8]], capacity = 5
<b>输出：</b>55.00000
<b>解释：</b>
我们将第二个物品分成两个部分，part1 = 0.5，part2 = 0.5。 
第一个物品的价格和重量分别为 5 和 4 。同样地，第二个物品的价格和重量也是 5 和 4 。 
经过操作后，数组 items 变为 [[50,1],[5,4],[5,4]] 。 
为了填满容量为 5 的背包，我们取价格为 50 的第一个元素和价格为 5 的第二个元素。 
可以证明，55.0 是我们可以达到的最大总价值。
</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<b>输入：</b>items = [[100,30]], capacity = 50
<b>输出：</b>-1.00000
<b>解释：</b>无法用给定的物品装满背包。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= items.length &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i].length == 2</code></li>
	<li><code>1 &lt;= price<sub>i</sub>, weight<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= capacity &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：贪心 + 排序

我们将物品按照单位价格从大到小排序，然后依次取出物品，直到背包装满。

若最后背包未装满，则返回 $-1$，否则返回总价格。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为物品数量。

<!-- tabs:start -->

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

```cpp
class Solution {
public:
    double maxPrice(vector<vector<int>>& items, int capacity) {
        sort(items.begin(), items.end(), [&](const auto& a, const auto& b) { return a[1] * b[0] < a[0] * b[1]; });
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
```

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

<!-- tabs:end -->

<!-- end -->
