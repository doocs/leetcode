# [1906. 查询差绝对值的最小值](https://leetcode.cn/problems/minimum-absolute-difference-queries)

[English Version](/solution/1900-1999/1906.Minimum%20Absolute%20Difference%20Queries/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个数组 <code>a</code> 的 <strong>差绝对值的最小值</strong> 定义为：<code>0 <= i < j < a.length</code> 且 <code>a[i] != a[j]</code> 的<strong> </strong><code><span style="">|a[i] - a[j]|</span></code> 的 <strong>最小值</strong>。如果 <code>a</code> 中所有元素都 <strong>相同</strong> ，那么差绝对值的最小值为 <code>-1</code> 。</p>

<ul>
	<li>比方说，数组 <code>[5,<strong>2</strong>,<strong>3</strong>,7,2]</code> 差绝对值的最小值是 <code>|2 - 3| = 1</code> 。注意答案不为 <code>0</code> ，因为 <code>a[i]</code> 和 <code>a[j]</code> 必须不相等。</li>
</ul>

<p>给你一个整数数组 <code>nums</code> 和查询数组 <code>queries</code> ，其中 <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> 。对于每个查询 <code>i</code> ，计算 <strong>子数组</strong> <code>nums[l<sub>i</sub>...r<sub>i</sub>]</code> 中 <strong>差绝对值的最小值</strong> ，子数组 <code>nums[l<sub>i</sub>...r<sub>i</sub>]</code> 包含 <code>nums</code> 数组（下标从 <strong>0</strong> 开始）中下标在 <code>l<sub>i</sub></code> 和 <code>r<sub>i</sub></code> 之间的所有元素（包含 <code>l<sub>i</sub></code> 和 <code>r<sub>i</sub></code> 在内）。</p>

<p>请你返回 <code>ans</code> <strong>数组</strong>，其中 <code>ans[i]</code> 是第 <code>i</code> 个查询的答案。</p>

<p><strong>子数组</strong> 是一个数组中连续的一段元素。</p>

<p><code>|x|</code> 的值定义为：</p>

<ul>
	<li>如果 <code>x >= 0</code> ，那么值为 <code>x</code> 。</li>
	<li>如果 <code>x < 0</code> ，那么值为 <code>-x</code> 。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,3,4,8], queries = [[0,1],[1,2],[2,3],[0,3]]
<b>输出：</b>[2,1,4,1]
<b>解释：</b>查询结果如下：
- queries[0] = [0,1]：子数组是 [<strong>1</strong>,<strong>3</strong>] ，差绝对值的最小值为 |1-3| = 2 。
- queries[1] = [1,2]：子数组是 [<strong>3</strong>,<strong>4</strong>] ，差绝对值的最小值为 |3-4| = 1 。
- queries[2] = [2,3]：子数组是 [<strong>4</strong>,<strong>8</strong>] ，差绝对值的最小值为 |4-8| = 4 。
- queries[3] = [0,3]：子数组是 [1,<strong>3</strong>,<strong>4</strong>,8] ，差的绝对值的最小值为 |3-4| = 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [4,5,2,2,7,10], queries = [[2,3],[0,2],[0,5],[3,5]]
<b>输出：</b>[-1,1,1,3]
<strong>解释：</strong>查询结果如下：
- queries[0] = [2,3]：子数组是 [2,2] ，差绝对值的最小值为 -1 ，因为所有元素相等。
- queries[1] = [0,2]：子数组是 [<strong>4</strong>,<strong>5</strong>,2] ，差绝对值的最小值为 |4-5| = 1 。
- queries[2] = [0,5]：子数组是 [<strong>4</strong>,<strong>5</strong>,2,2,7,10] ，差绝对值的最小值为 |4-5| = 1 。
- queries[3] = [3,5]：子数组是 [2,<strong>7</strong>,<strong>10</strong>] ，差绝对值的最小值为 |7-10| = 3 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 100</code></li>
	<li><code>1 <= queries.length <= 2 * 10<sup>4</sup></code></li>
	<li><code>0 <= l<sub>i</sub> < r<sub>i</sub> < nums.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

数组元素范围在 `[1,100]` 之间，对于每个区间 `[left, right]`，可以遍历整数 `1~100`，判断每个整数是否出现，求得差绝对值的最小值。

用前缀和 `preSum[i][j]` 表示数组前 i 个元素中包含整数 j 的个数，那么对于区间 `[left, right]`，如果 `preSum[right + 1][j] - preSum[left][j] > 0`，那么表示此区间存在整数 j。j 从 `1~100` 进行遍历，可以判断每个递增整数是否在区间中存在。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minDifference(self, nums: List[int], queries: List[List[int]]) -> List[int]:
        m, n = len(nums), len(queries)
        pre_sum = [[0] * 101 for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, 101):
                t = 1 if nums[i - 1] == j else 0
                pre_sum[i][j] = pre_sum[i - 1][j] + t

        ans = []
        for i in range(n):
            left, right = queries[i][0], queries[i][1] + 1
            t = inf
            last = -1
            for j in range(1, 101):
                if pre_sum[right][j] - pre_sum[left][j] > 0:
                    if last != -1:
                        t = min(t, j - last)
                    last = j
            if t == inf:
                t = -1
            ans.append(t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] minDifference(int[] nums, int[][] queries) {
        int m = nums.length, n = queries.length;
        int[][] preSum = new int[m + 1][101];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= 100; ++j) {
                int t = nums[i - 1] == j ? 1 : 0;
                preSum[i][j] = preSum[i - 1][j] + t;
            }
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int left = queries[i][0], right = queries[i][1] + 1;
            int t = Integer.MAX_VALUE;
            int last = -1;
            for (int j = 1; j <= 100; ++j) {
                if (preSum[right][j] > preSum[left][j]) {
                    if (last != -1) {
                        t = Math.min(t, j - last);
                    }
                    last = j;
                }
            }
            if (t == Integer.MAX_VALUE) {
                t = -1;
            }
            ans[i] = t;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function minDifference(nums: number[], queries: number[][]): number[] {
    let m = nums.length,
        n = queries.length;
    let max = 100;
    // let max = Math.max(...nums);
    let pre: number[][] = [];
    pre.push(new Array(max + 1).fill(0));
    for (let i = 0; i < m; ++i) {
        let num = nums[i];
        pre.push(pre[i].slice());
        pre[i + 1][num] += 1;
    }

    let ans = [];
    for (let [left, right] of queries) {
        let last = -1;
        let min = Infinity;
        for (let j = 1; j < max + 1; ++j) {
            if (pre[left][j] < pre[right + 1][j]) {
                if (last != -1) {
                    min = Math.min(min, j - last);
                }
                last = j;
            }
        }
        ans.push(min == Infinity ? -1 : min);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> minDifference(vector<int>& nums, vector<vector<int>>& queries) {
        int m = nums.size(), n = queries.size();
        int preSum[m + 1][101];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= 100; ++j) {
                int t = nums[i - 1] == j ? 1 : 0;
                preSum[i][j] = preSum[i - 1][j] + t;
            }
        }

        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int left = queries[i][0], right = queries[i][1] + 1;
            int t = 101;
            int last = -1;
            for (int j = 1; j <= 100; ++j) {
                if (preSum[right][j] > preSum[left][j]) {
                    if (last != -1) {
                        t = min(t, j - last);
                    }
                    last = j;
                }
            }
            if (t == 101) {
                t = -1;
            }
            ans[i] = t;
        }
        return ans;
    }
};
```

### **Go**

```go
func minDifference(nums []int, queries [][]int) []int {
	m, n := len(nums), len(queries)
	preSum := make([][101]int, m+1)
	for i := 1; i <= m; i++ {
		for j := 1; j <= 100; j++ {
			t := 0
			if nums[i-1] == j {
				t = 1
			}
			preSum[i][j] = preSum[i-1][j] + t
		}
	}

	ans := make([]int, n)
	for i := 0; i < n; i++ {
		left, right := queries[i][0], queries[i][1]+1
		t, last := 101, -1
		for j := 1; j <= 100; j++ {
			if preSum[right][j]-preSum[left][j] > 0 {
				if last != -1 {
					if t > j-last {
						t = j - last
					}
				}
				last = j
			}
		}
		if t == 101 {
			t = -1
		}
		ans[i] = t
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
