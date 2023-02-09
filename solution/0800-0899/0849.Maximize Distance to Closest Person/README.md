# [849. 到最近的人的最大距离](https://leetcode.cn/problems/maximize-distance-to-closest-person)

[English Version](/solution/0800-0899/0849.Maximize%20Distance%20to%20Closest%20Person/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>seats</code> 表示一排座位，其中 <code>seats[i] = 1</code> 代表有人坐在第 <code>i</code> 个座位上，<code>seats[i] = 0</code> 代表座位 <code>i</code> 上是空的（<strong>下标从 0 开始</strong>）。</p>

<p>至少有一个空座位，且至少有一人已经坐在座位上。</p>

<p>亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。</p>

<p>返回他到离他最近的人的最大距离。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0849.Maximize%20Distance%20to%20Closest%20Person/images/distance.jpg" style="width: 650px; height: 257px;" />
<pre>
<strong>输入：</strong>seats = [1,0,0,0,1,0,1]
<strong>输出：</strong>2
<strong>解释：
</strong>如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
因此，他到离他最近的人的最大距离是 2 。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>seats = [1,0,0,0]
<strong>输出：</strong>3
<strong>解释：</strong>
如果亚历克斯坐在最后一个座位上，他离最近的人有 3 个座位远。
这是可能的最大距离，所以答案是 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>seats = [0,1]
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= seats.length <= 2 * 10<sup>4</sup></code></li>
	<li><code>seats[i]</code> 为 <code>0</code> 或 <code>1</code></li>
	<li>至少有一个 <strong>空座位</strong></li>
	<li>至少有一个 <strong>座位上有人</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：一次遍历**

我们定义两个变量 `first` 和 `last` 分别表示第一个人和最后一个人的位置，用变量 `d` 表示两个人之间的最大距离。

然后遍历数组 `seats`，如果当前位置有人，如果此前 `last` 更新过，说明此前有人，此时更新 $d = \max(d, i - last)$；如果此前 `first` 没有更新过，说明此前没有人，此时更新 `first = i`，然后更新 `last = i`。

最后返回 $\max(first, n - last - 1, d / 2)$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `seats` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxDistToClosest(self, seats: List[int]) -> int:
        first = last = None
        d = 0
        for i, c in enumerate(seats):
            if c:
                if last is not None:
                    d = max(d, i - last)
                if first is None:
                    first = i
                last = i
        return max(first, len(seats) - last - 1, d // 2)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxDistToClosest(int[] seats) {
        int first = -1, last = -1;
        int d = 0, n = seats.length;
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                if (last != -1) {
                    d = Math.max(d, i - last);
                }
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }
        return Math.max(d / 2, Math.max(first, n - last - 1));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxDistToClosest(vector<int>& seats) {
        int first = -1, last = -1;
        int d = 0, n = seats.size();
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                if (last != -1) {
                    d = max(d, i - last);
                }
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }
        return max({d / 2, max(first, n - last - 1)});
    }
};
```

### **Go**

```go
func maxDistToClosest(seats []int) int {
	first, last := -1, -1
	d := 0
	for i, c := range seats {
		if c == 1 {
			if last != -1 {
				d = max(d, i-last)
			}
			if first == -1 {
				first = i
			}
			last = i
		}
	}
	return max(d/2, max(first, len(seats)-last-1))
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
