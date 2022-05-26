# [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water)

[中文文档](/solution/0000-0099/0042.Trapping%20Rain%20Water/README.md)

## Description

<p>Given <code>n</code> non-negative integers representing an elevation map where the width of each bar is <code>1</code>, compute how much water it can trap after raining.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0042.Trapping%20Rain%20Water/images/rainwatertrap.png" style="width: 412px; height: 161px;" />
<pre>
<strong>Input:</strong> height = [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> height = [4,2,0,3,2,5]
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
