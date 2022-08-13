# [1691. 堆叠长方体的最大高度](https://leetcode.cn/problems/maximum-height-by-stacking-cuboids)

[English Version](/solution/1600-1699/1691.Maximum%20Height%20by%20Stacking%20Cuboids/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你 <code>n</code> 个长方体 <code>cuboids</code> ，其中第 <code>i</code> 个长方体的长宽高表示为 <code>cuboids[i] = [width<sub>i</sub>, length<sub>i</sub>, height<sub>i</sub>]</code>（<strong>下标从 0 开始</strong>）。请你从 <code>cuboids</code> 选出一个 <strong>子集</strong> ，并将它们堆叠起来。</p>

<p>如果 <code>width<sub>i</sub> <= width<sub>j</sub></code> 且 <code>length<sub>i</sub> <= length<sub>j</sub></code> 且 <code>height<sub>i</sub> <= height<sub>j</sub></code> ，你就可以将长方体 <code>i</code> 堆叠在长方体 <code>j</code> 上。你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。</p>

<p>返回 <strong>堆叠长方体</strong> <code>cuboids</code> 可以得到的 <strong>最大高度</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1691.Maximum%20Height%20by%20Stacking%20Cuboids/images/image.jpg" style="width: 420px; height: 299px;" /></strong></p>

<pre>
<strong>输入：</strong>cuboids = [[50,45,20],[95,37,53],[45,23,12]]
<strong>输出：</strong>190
<strong>解释：</strong>
第 1 个长方体放在底部，53x37 的一面朝下，高度为 95 。
第 0 个长方体放在中间，45x20 的一面朝下，高度为 50 。
第 2 个长方体放在上面，23x12 的一面朝下，高度为 45 。
总高度是 95 + 50 + 45 = 190 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>cuboids = [[38,25,45],[76,35,3]]
<strong>输出：</strong>76
<strong>解释：</strong>
无法将任何长方体放在另一个上面。
选择第 1 个长方体然后旋转它，使 35x3 的一面朝下，其高度为 76 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
<strong>输出：</strong>102
<strong>解释：</strong>
重新排列长方体后，可以看到所有长方体的尺寸都相同。
你可以把 11x7 的一面朝下，这样它们的高度就是 17 。
堆叠长方体的最大高度为 6 * 17 = 102 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == cuboids.length</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>1 <= width<sub>i</sub>, length<sub>i</sub>, height<sub>i</sub> <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

考虑两个长方体，假设其三边分别是 `(a1, b1, c2)` 和 `(a2, b2, c2)`。这里不妨设 `a1≤b1≤c1`, `a2≤b2≤c2`。我们可以发现，假设两个长方体能够拼接到一起（假设第一个长方体较小），则必然有：`a1≤a2, b1≤b2, c1≤c2`。

直观上我们可以发现，如果两个长方体能够拼接到一起，则他们可以从任何一个面进行拼接。本题允许我们任意旋转长方体，看起来情况比较复杂，需要讨论 6 种排列情况，但实际上，因为我们希望高度尽可能高，所以根据上面的观察，我们应该选择**从较短的两条边组成的面**进行拼接。

因此，我们进行两次排序：

1. 将每个长方体的三条边按升序排列；
1. 将每个长方体升序排列。

之后，问题转换为最长上升子序列问题。对于第 i 个长方体，我们依次考虑第 `[1...i-1]` 个长方体，看能否将第 i 个长方体拼接在它的下方，然后更新当前的最大值。

时间复杂度 O(n²)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxHeight(self, cuboids: List[List[int]]) -> int:
        for c in cuboids:
            c.sort()
        cuboids.sort()
        n = len(cuboids)
        dp = [0] * n
        for i in range(n):
            for j in range(i):
                if cuboids[j][1] <= cuboids[i][1] and cuboids[j][2] <= cuboids[i][2]:
                    dp[i] = max(dp[i], dp[j])
            dp[i] += cuboids[i][2]
        return max(dp)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxHeight(int[][] cuboids) {
        for (int[] c : cuboids) {
            Arrays.sort(c);
        }
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        int n = cuboids.length;
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += cuboids[i][2];
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxHeight(vector<vector<int>>& cuboids) {
        for (auto& c : cuboids) sort(c.begin(), c.end());
        sort(cuboids.begin(), cuboids.end());
        int n = cuboids.size();
        vector<int> dp(n);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]) {
                    dp[i] = max(dp[i], dp[j]);
                }
            }
            dp[i] += cuboids[i][2];
            ans = max(ans, dp[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxHeight(cuboids [][]int) int {
	for _, c := range cuboids {
		sort.Ints(c)
	}
	sort.Slice(cuboids, func(i, j int) bool {
		if cuboids[i][0] != cuboids[j][0] {
			return cuboids[i][0] < cuboids[j][0]
		}
		if cuboids[i][1] != cuboids[j][1] {
			return cuboids[i][1] < cuboids[j][1]
		}
		return cuboids[i][2] < cuboids[j][2]
	})
	n := len(cuboids)
	dp := make([]int, n)
	ans := 0
	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			if cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2] {
				dp[i] = max(dp[i], dp[j])
			}
		}
		dp[i] += cuboids[i][2]
		ans = max(ans, dp[i])
	}
	return ans
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
