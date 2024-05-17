---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2848.Points%20That%20Intersect%20With%20Cars/README.md
rating: 1229
source: 第 362 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 前缀和
---

<!-- problem:start -->

# [2848. 与车相交的点](https://leetcode.cn/problems/points-that-intersect-with-cars)

[English Version](/solution/2800-2899/2848.Points%20That%20Intersect%20With%20Cars/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的二维整数数组 <code>nums</code> 表示汽车停放在数轴上的坐标。对于任意下标 <code>i</code>，<code>nums[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> ，其中 <code>start<sub>i</sub></code> 是第 <code>i</code> 辆车的起点，<code>end<sub>i</sub></code> 是第 <code>i</code> 辆车的终点。</p>

<p>返回数轴上被车 <strong>任意部分</strong> 覆盖的整数点的数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [[3,6],[1,5],[4,7]]
<strong>输出：</strong>7
<strong>解释：</strong>从 1 到 7 的所有点都至少与一辆车相交，因此答案为 7 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [[1,3],[5,8]]
<strong>输出：</strong>7
<strong>解释：</strong>1、2、3、5、6、7、8 共计 7 个点满足至少与一辆车相交，因此答案为 7 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums[i].length == 2</code></li>
	<li><code><font face="monospace">1 &lt;= start<sub>i</sub>&nbsp;&lt;= end<sub>i</sub>&nbsp;&lt;= 100</font></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分数组

我们创建一个长度为 $110$ 的差分数组 $d$，然后遍历给定的数组，对于每个区间 $[a, b]$，我们令 $d[a]$ 增加 $1$，$d[b + 1]$ 减少 $1$。最后我们遍历差分数组 $d$，求每个位置的前缀和 $s$，如果 $s > 0$，则说明该位置被覆盖，我们将答案增加 $1$。

时间复杂度 $O(n)$，空间复杂度 $O(M)$。其中 $n$ 是给定数组的长度，而 $M$ 是数组中元素的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfPoints(self, nums: List[List[int]]) -> int:
        d = [0] * 110
        for a, b in nums:
            d[a] += 1
            d[b + 1] -= 1
        return sum(s > 0 for s in accumulate(d))
```

#### Java

```java
class Solution {
    public int numberOfPoints(List<List<Integer>> nums) {
        int[] d = new int[110];
        for (var e : nums) {
            d[e.get(0)]++;
            d[e.get(1) + 1]--;
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            if (s > 0) {
                ans++;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfPoints(vector<vector<int>>& nums) {
        int d[110]{};
        for (auto& e : nums) {
            d[e[0]]++;
            d[e[1] + 1]--;
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            ans += s > 0;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfPoints(nums [][]int) (ans int) {
	d := [110]int{}
	for _, e := range nums {
		d[e[0]]++
		d[e[1]+1]--
	}
	s := 0
	for _, x := range d {
		s += x
		if s > 0 {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function numberOfPoints(nums: number[][]): number {
    const d: number[] = Array(110).fill(0);
    for (const [a, b] of nums) {
        d[a]++;
        d[b + 1]--;
    }
    let ans = 0;
    let s = 0;
    for (const x of d) {
        s += x;
        if (s > 0) {
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
