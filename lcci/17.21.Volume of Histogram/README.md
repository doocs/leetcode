# [面试题 17.21. 直方图的水量](https://leetcode.cn/problems/volume-of-histogram-lcci)

[English Version](/lcci/17.21.Volume%20of%20Histogram/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。</p>

![](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcci/17.21.Volume%20of%20Histogram/images/rainwatertrap.png)

<p><small>上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。&nbsp;<strong>感谢 Marcos</strong> 贡献此图。</small></p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>输出:</strong> 6</pre>

## 解法

### 方法一：动态规划

我们定义 $left[i]$ 表示下标 $i$ 位置及其左边的最高柱子的高度，定义 $right[i]$ 表示下标 $i$ 位置及其右边的最高柱子的高度。那么下标 $i$ 位置能接的雨水量为 $min(left[i], right[i]) - height[i]$。我们遍历数组，计算出 $left[i]$ 和 $right[i]$，最后答案为 $\sum_{i=0}^{n-1} min(left[i], right[i]) - height[i]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

相似题目：

-   [42. 接雨水](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0042.Trapping%20Rain%20Water/README.md)

<!-- tabs:start -->

```python
class Solution:
    def trap(self, height: List[int]) -> int:
        n = len(height)
        if n < 3:
            return 0
        left = [height[0]] * n
        right = [height[-1]] * n
        for i in range(1, n):
            left[i] = max(left[i - 1], height[i])
            right[n - i - 1] = max(right[n - i], height[n - i - 1])
        return sum(min(l, r) - h for l, r, h in zip(left, right, height))
```

```java
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = Math.max(left[i - 1], height[i]);
            right[n - i - 1] = Math.max(right[n - i], height[n - i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(left[i], right[i]) - height[i];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int trap(vector<int>& height) {
        int n = height.size();
        if (n < 3) {
            return 0;
        }
        int left[n], right[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = max(left[i - 1], height[i]);
            right[n - i - 1] = max(right[n - i], height[n - i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += min(left[i], right[i]) - height[i];
        }
        return ans;
    }
};
```

```go
func trap(height []int) (ans int) {
	n := len(height)
	if n < 3 {
		return 0
	}
	left := make([]int, n)
	right := make([]int, n)
	left[0], right[n-1] = height[0], height[n-1]
	for i := 1; i < n; i++ {
		left[i] = max(left[i-1], height[i])
		right[n-i-1] = max(right[n-i], height[n-i-1])
	}
	for i, h := range height {
		ans += min(left[i], right[i]) - h
	}
	return
}
```

```ts
function trap(height: number[]): number {
    const n = height.length;
    if (n < 3) {
        return 0;
    }
    const left: number[] = new Array(n).fill(height[0]);
    const right: number[] = new Array(n).fill(height[n - 1]);
    for (let i = 1; i < n; ++i) {
        left[i] = Math.max(left[i - 1], height[i]);
        right[n - i - 1] = Math.max(right[n - i], height[n - i - 1]);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += Math.min(left[i], right[i]) - height[i];
    }
    return ans;
}
```

```cs
public class Solution {
    public int Trap(int[] height) {
        int n = height.Length;
        if (n < 3) {
            return 0;
        }
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = Math.Max(left[i - 1], height[i]);
            right[n - i - 1] = Math.Max(right[n - i], height[n - i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.Min(left[i], right[i]) - height[i];
        }
        return ans;
    }
}
```

```swift
class Solution {
    func trap(_ height: [Int]) -> Int {
        let n = height.count
        if n < 3 {
            return 0
        }

        var left = [Int](repeating: 0, count: n)
        var right = [Int](repeating: 0, count: n)

        left[0] = height[0]
        right[n - 1] = height[n - 1]

        for i in 1..<n {
            left[i] = max(left[i - 1], height[i])
        }

        for i in stride(from: n - 2, through: 0, by: -1) {
            right[i] = max(right[i + 1], height[i])
        }

        var ans = 0
        for i in 0..<n {
            ans += min(left[i], right[i]) - height[i]
        }

        return ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
