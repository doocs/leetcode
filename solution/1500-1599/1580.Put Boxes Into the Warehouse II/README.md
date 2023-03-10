# [1580. 把箱子放进仓库里 II](https://leetcode.cn/problems/put-boxes-into-the-warehouse-ii)

[English Version](/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个正整数数组 <code>boxes</code> 和 <code>warehouse</code> ，分别包含单位宽度的箱子的高度，以及仓库中<code>n</code>个房间各自的高度。仓库的房间分别从<code>0</code> 到 <code>n - 1</code>自左向右编号，<code>warehouse[i]</code>（索引从 0 开始）是第 <code>i</code> 个房间的高度。</p>

<p>箱子放进仓库时遵循下列规则：</p>

<ul>
	<li>箱子不可叠放。</li>
	<li>你可以重新调整箱子的顺序。</li>
	<li>箱子可以从任意方向（左边或右边）推入仓库中。</li>
	<li>如果仓库中某房间的高度小于某箱子的高度，则这个箱子和之后的箱子都会停在这个房间的前面。</li>
</ul>

<p>你最多可以在仓库中放进多少个箱子？</p>

<p> </p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/images/22.png" style="width: 401px; height: 202px;" />
<pre>
<strong>输入:</strong> boxes = [1,2,2,3,4], warehouse = [3,4,1,2]
<strong>输出:</strong> 4
<strong>解释:
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/images/22-1.png" style="width: 240px; height: 202px;" />
</strong>我们可以按如下顺序推入箱子:
1- 从左边或右边把黄色箱子推入2号房间；
2- 从右边把橙色箱子推入3号房间；
3- 从左边把绿色箱子推入1号房间；
4- 从左边把红色箱子推入0号房间；
还有其他方式推入4个箱子，比如交换红色与绿色箱子，或者交换红色与橙色箱子。
</pre>

<p><strong>示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/images/22-2.png" style="width: 401px; height: 242px;" />
<pre>
<strong>输入:</strong> boxes = [3,5,5,2], warehouse = [2,1,3,4,5]
<strong>输出:</strong> 3
<strong>解释:
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/images/22-3.png" style="width: 280px; height: 242px;" />
</strong>因为只有一个高度大于等于5的房间，所以无法将两个高度为5的箱子都推入仓库。
还有其他方式推入箱子，比如将绿色箱子推入2号房间，或者在绿色及红色箱子之前将橙色箱子推入2号房间。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> boxes = [1,2,3], warehouse = [1,2,3,4]
<strong>输出:</strong> 3
</pre>

<p><strong>示例 4:</strong></p>

<pre>
<strong>输入:</strong> boxes = [4,5,6], warehouse = [3,3,3,3,3]
<strong>输出:</strong> 0
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == warehouse.length</code></li>
	<li><code>1 <= boxes.length, warehouse.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= boxes[i], warehouse[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：预处理 + 排序 + 贪心**

我们先对仓库进行预处理，得到每个房间的最大高度，然后对箱子和仓库进行排序，从最小的箱子开始，从最小的房间开始，如果当前房间的高度大于等于当前箱子的高度，则可以将当前箱子放入当前房间，否则继续寻找下一个房间。

最后返回可以放入的箱子数量。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为仓库的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxBoxesInWarehouse(self, boxes: List[int], warehouse: List[int]) -> int:
        n = len(warehouse)
        left = [0] * n
        right = [0] * n
        left[0] = right[-1] = inf
        for i in range(1, n):
            left[i] = min(left[i - 1], warehouse[i - 1])
        for i in range(n - 2, -1, -1):
            right[i] = min(right[i + 1], warehouse[i + 1])
        for i in range(n):
            warehouse[i] = min(warehouse[i], max(left[i], right[i]))
        boxes.sort()
        warehouse.sort()
        ans = i = 0
        for x in boxes:
            while i < n and warehouse[i] < x:
                i += 1
            if i == n:
                break
            ans, i = ans + 1, i + 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        int n = warehouse.length;
        int[] left = new int[n];
        int[] right = new int[n];
        final int inf = 1 << 30;
        left[0] = inf;
        right[n - 1] = inf;
        for (int i = 1; i < n; ++i) {
            left[i] = Math.min(left[i - 1], warehouse[i - 1]);
        }
        for (int i = n - 2; i >= 0; --i) {
            right[i] = Math.min(right[i + 1], warehouse[i + 1]);
        }
        for (int i = 0; i < n; ++i) {
            warehouse[i] = Math.min(warehouse[i], Math.max(left[i], right[i]));
        }
        Arrays.sort(boxes);
        Arrays.sort(warehouse);
        int ans = 0, i = 0;
        for (int x : boxes) {
            while (i < n && warehouse[i] < x) {
                ++i;
            }
            if (i == n) {
                break;
            }
            ++ans;
            ++i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxBoxesInWarehouse(vector<int>& boxes, vector<int>& warehouse) {
        int n = warehouse.size();
        const int inf = 1 << 30;
        vector<int> left(n, inf);
        vector<int> right(n, inf);
        for (int i = 1; i < n; ++i) {
            left[i] = min(left[i - 1], warehouse[i - 1]);
        }
        for (int i = n - 2; ~i; --i) {
            right[i] = min(right[i + 1], warehouse[i + 1]);
        }
        for (int i = 0; i < n; ++i) {
            warehouse[i] = min(warehouse[i], max(left[i], right[i]));
        }
        sort(boxes.begin(), boxes.end());
        sort(warehouse.begin(), warehouse.end());
        int ans = 0;
        int i = 0;
        for (int x : boxes) {
            while (i < n && warehouse[i] < x) {
                ++i;
            }
            if (i == n) {
                break;
            }
            ++ans;
            ++i;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxBoxesInWarehouse(boxes []int, warehouse []int) (ans int) {
	n := len(warehouse)
	left := make([]int, n)
	right := make([]int, n)
	const inf = 1 << 30
	left[0] = inf
	right[n-1] = inf
	for i := 1; i < n; i++ {
		left[i] = min(left[i-1], warehouse[i-1])
	}
	for i := n - 2; i >= 0; i-- {
		right[i] = min(right[i+1], warehouse[i+1])
	}
	for i := 0; i < n; i++ {
		warehouse[i] = min(warehouse[i], max(left[i], right[i]))
	}
	sort.Ints(boxes)
	sort.Ints(warehouse)
	i := 0
	for _, x := range boxes {
		for i < n && warehouse[i] < x {
			i++
		}
		if i == n {
			break
		}
		ans++
		i++
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
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
