# [1580. Put Boxes Into the Warehouse II](https://leetcode.com/problems/put-boxes-into-the-warehouse-ii)

[中文文档](/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/README.md)

## Description

<p>You are given two arrays of positive integers, <code>boxes</code> and <code>warehouse</code>, representing the heights of some boxes of unit width and the heights of <code>n</code> rooms in a warehouse respectively. The warehouse&#39;s rooms are labeled from <code>0</code> to <code>n - 1</code> from left to right where <code>warehouse[i]</code> (0-indexed) is the height of the <code>i<sup>th</sup></code> room.</p>

<p>Boxes are put into the warehouse by the following rules:</p>

<ul>
	<li>Boxes cannot be stacked.</li>
	<li>You can rearrange the insertion order of the boxes.</li>
	<li>Boxes can be pushed into the warehouse from <strong>either side</strong> (left or right)</li>
	<li>If the height of some room in the warehouse is less than the height of a box, then that box and all other boxes behind it will be stopped before that room.</li>
</ul>

<p>Return <em>the maximum number of boxes you can put into the warehouse.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/images/22.png" style="width: 401px; height: 202px;" />
<pre>
<strong>Input:</strong> boxes = [1,2,2,3,4], warehouse = [3,4,1,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/images/22-1.png" style="width: 240px; height: 202px;" />
We can store the boxes in the following order:
1- Put the yellow box in room 2 from either the left or right side.
2- Put the orange box in room 3 from the right side.
3- Put the green box in room 1 from the left side.
4- Put the red box in room 0 from the left side.
Notice that there are other valid ways to put 4 boxes such as swapping the red and green boxes or the red and orange boxes.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/images/22-2.png" style="width: 401px; height: 242px;" />
<pre>
<strong>Input:</strong> boxes = [3,5,5,2], warehouse = [2,1,3,4,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/images/22-3.png" style="width: 280px; height: 242px;" />
It is not possible to put the two boxes of height 5 in the warehouse since there&#39;s only 1 room of height &gt;= 5.
Other valid solutions are to put the green box in room 2 or to put the orange box first in room 2 before putting the green and red boxes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == warehouse.length</code></li>
	<li><code>1 &lt;= boxes.length, warehouse.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= boxes[i], warehouse[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
