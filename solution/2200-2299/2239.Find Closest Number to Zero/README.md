# [2239. 找到最接近 0 的数字](https://leetcode.cn/problems/find-closest-number-to-zero)

[English Version](/solution/2200-2299/2239.Find%20Closest%20Number%20to%20Zero/README_EN.md)

<!-- tags:数组 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，请你返回 <code>nums</code>&nbsp;中最 <strong>接近</strong>&nbsp;<code>0</code>&nbsp;的数字。如果有多个答案，请你返回它们中的 <strong>最大值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [-4,-2,1,4,8]
<b>输出：</b>1
<strong>解释：</strong>
-4 到 0 的距离为 |-4| = 4 。
-2 到 0 的距离为 |-2| = 2 。
1 到 0 的距离为 |1| = 1 。
4 到 0 的距离为 |4| = 4 。
8 到 0 的距离为 |8| = 8 。
所以，数组中距离 0 最近的数字为 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,-1,1]
<b>输出：</b>1
<b>解释：</b>1 和 -1 都是距离 0 最近的数字，所以返回较大值 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：一次遍历

我们定义一个变量 $d$ 来记录当前最小的距离，初始时 $d=\infty$。然后我们遍历数组，对于每个元素 $x$，我们计算 $y=|x|$，如果 $y \lt d$ 或者 $y=d$ 且 $x \gt ans$，我们就更新答案 $ans=x$ 和 $d=y$。

遍历结束后返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findClosestNumber(self, nums: List[int]) -> int:
        ans, d = 0, inf
        for x in nums:
            if (y := abs(x)) < d or (y == d and x > ans):
                ans, d = x, y
        return ans
```

```java
class Solution {
    public int findClosestNumber(int[] nums) {
        int ans = 0, d = 1 << 30;
        for (int x : nums) {
            int y = Math.abs(x);
            if (y < d || (y == d && x > ans)) {
                ans = x;
                d = y;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findClosestNumber(vector<int>& nums) {
        int ans = 0, d = 1 << 30;
        for (int x : nums) {
            int y = abs(x);
            if (y < d || (y == d && x > ans)) {
                ans = x;
                d = y;
            }
        }
        return ans;
    }
};
```

```go
func findClosestNumber(nums []int) int {
	ans, d := 0, 1<<30
	for _, x := range nums {
		if y := abs(x); y < d || (y == d && x > ans) {
			ans, d = x, y
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function findClosestNumber(nums: number[]): number {
    let [ans, d] = [0, 1 << 30];
    for (const x of nums) {
        const y = Math.abs(x);
        if (y < d || (y == d && x > ans)) {
            [ans, d] = [x, y];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
