# [面试题 17.16. 按摩师](https://leetcode.cn/problems/the-masseuse-lcci)

[English Version](/lcci/17.16.The%20Masseuse/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。</p>

<p><strong>注意：</strong>本题相对原题稍作改动</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong> [1,2,3,1]
<strong>输出：</strong> 4
<strong>解释：</strong> 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong> [2,7,9,3,1]
<strong>输出：</strong> 12
<strong>解释：</strong> 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong> [2,1,4,5,3,1,1,3]
<strong>输出：</strong> 12
<strong>解释：</strong> 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义状态 $f[i]$ 表示考虑前 $i$ 个预约，且第 $i$ 个预约被接受的情况下，最长的预约时长；定义状态 $g[i]$ 表示考虑前 $i$ 个预约，且第 $i$ 个预约被拒绝的情况下，最长的预约时长。

考虑第 $i$ 个预约，如果第 $i$ 个预约被接受，那么第 $i-1$ 个预约一定不能被接受，即 $f[i]=g[i-1]+nums[i]$；如果第 $i$ 个预约被拒绝，那么第 $i-1$ 个预约可以被接受，也可以被拒绝，即 $g[i]=max(f[i-1],g[i-1])$。

所以，我们可以写出状态转移方程：

$$
\begin{aligned}
f[i] &= g[i-1]+nums[i] \\
g[i] &= max(f[i-1],g[i-1])
\end{aligned}
$$

最终的答案即为 $max(f[n-1],g[n-1])$，其中 $n$ 为预约的数量。

我们可以将空间复杂度优化至 $O(1)$，即使用两个变量 $f$ 和 $g$ 来代替数组 $f$ 和 $g$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def massage(self, nums: List[int]) -> int:
        f = g = 0
        for x in nums:
            f, g = g + x, max(f, g)
        return max(f, g)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int massage(int[] nums) {
        int f = 0, g = 0;
        for (int x : nums) {
            int ff = g + x;
            int gg = Math.max(f, g);
            f = ff;
            g = gg;
        }
        return Math.max(f, g);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int massage(vector<int>& nums) {
        int f = 0, g = 0;
        for (int& x : nums) {
            int ff = g + x;
            int gg = max(f, g);
            f = ff;
            g = gg;
        }
        return max(f, g);
    }
};
```

### **Go**

```go
func massage(nums []int) int {
	f, g := 0, 0
	for _, x := range nums {
		f, g = g+x, max(f, g)
	}
	return max(f, g)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function massage(nums: number[]): number {
    let f = 0,
        g = 0;
    for (const x of nums) {
        const ff = g + x;
        const gg = Math.max(f, g);
        f = ff;
        g = gg;
    }
    return Math.max(f, g);
}
```

### **...**

```

```

<!-- tabs:end -->
