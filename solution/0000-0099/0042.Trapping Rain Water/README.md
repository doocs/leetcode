# [42. 接雨水](https://leetcode.cn/problems/trapping-rain-water)

[English Version](/solution/0000-0099/0042.Trapping%20Rain%20Water/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定&nbsp;<code>n</code> 个非负整数表示每个宽度为 <code>1</code> 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0042.Trapping%20Rain%20Water/images/rainwatertrap.png" style="height: 161px; width: 412px;" /></p>

<pre>
<strong>输入：</strong>height = [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>输出：</strong>6
<strong>解释：</strong>上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>height = [4,2,0,3,2,5]
<strong>输出：</strong>9
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划法。

对于下标 i，水能达到的最大高度等于下标 i 左右两侧的最大高度的最小值，再减去 `height[i]` 就能得到当前柱子所能存的水量。

同[面试题 17.21. 直方图的水量](/lcci/17.21.Volume%20of%20Histogram/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def trap(self, height: List[int]) -> int:
        n = len(height)
        if n < 3:
            return 0

        lmx, rmx = [height[0]] * n, [height[n - 1]] * n
        for i in range(1, n):
            lmx[i] = max(lmx[i - 1], height[i])
            rmx[n - 1 - i] = max(rmx[n - i], height[n - 1 - i])

        res = 0
        for i in range(n):
            res += min(lmx[i], rmx[i]) - height[i]
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }

        int[] lmx = new int[n];
        int[] rmx = new int[n];
        lmx[0] = height[0];
        rmx[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            lmx[i] = Math.max(lmx[i - 1], height[i]);
            rmx[n - 1 - i] = Math.max(rmx[n - i], height[n - i - 1]);
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += Math.min(lmx[i], rmx[i]) - height[i];
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function trap(height: number[]): number {
    let ans = 0;
    let left = 0,
        right = height.length - 1;
    let maxLeft = 0,
        maxRight = 0;
    while (left < right) {
        if (height[left] < height[right]) {
            // move left
            if (height[left] >= maxLeft) {
                maxLeft = height[left];
            } else {
                ans += maxLeft - height[left];
            }
            ++left;
        } else {
            // move right
            if (height[right] >= maxRight) {
                maxRight = height[right];
            } else {
                ans += maxRight - height[right];
            }
            --right;
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int trap(vector<int>& height) {
        int n = height.size();
        if (n < 3) {
            return 0;
        }

        vector<int> lmx(n, height[0]);
        vector<int> rmx(n, height[n - 1]);
        for (int i = 1; i < n; ++i) {
            lmx[i] = max(lmx[i - 1], height[i]);
            rmx[n - 1 - i] = max(rmx[n - i], height[n - 1 - i]);
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += min(lmx[i], rmx[i]) - height[i];
        }
        return res;
    }
};
```

### **Go**

```go
func trap(height []int) int {
	n := len(height)
	if n < 3 {
		return 0
	}

	lmx, rmx := make([]int, n), make([]int, n)
	lmx[0], rmx[n-1] = height[0], height[n-1]
	for i := 1; i < n; i++ {
		lmx[i] = max(lmx[i-1], height[i])
		rmx[n-1-i] = max(rmx[n-i], height[n-1-i])
	}

	res := 0
	for i := 0; i < n; i++ {
		res += min(lmx[i], rmx[i]) - height[i]
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
