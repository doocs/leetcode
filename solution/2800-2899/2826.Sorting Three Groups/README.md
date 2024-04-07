# [2826. 将三个组排序](https://leetcode.cn/problems/sorting-three-groups)

[English Version](/solution/2800-2899/2826.Sorting%20Three%20Groups/README_EN.md)

<!-- tags:数组,二分查找,动态规划 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;。<code>nums</code>&nbsp;的每个元素是 1，2 或 3。在每次操作中，你可以删除&nbsp;<code>nums</code>&nbsp;中的一个元素。返回使 nums 成为 <strong>非递减</strong>&nbsp;顺序所需操作数的 <strong>最小值</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,3,2,1]
<b>输出：</b>3
<b>解释：</b>
其中一个最优方案是删除 nums[0]，nums[2] 和 nums[3]。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,3,2,1,3,3]
<b>输出：</b>2
<b>解释：</b>
其中一个最优方案是删除 nums[1] 和 nums[2]。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [2,2,2,2,3,3]
<b>输出：</b>0
<b>解释：</b>
nums 已是非递减顺序的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 3</code></li>
</ul>

<p><strong>进阶：</strong>你可以使用&nbsp;<code>O(n)</code>&nbsp;时间复杂度以内的算法解决吗？</p>

## 解法

### 方法一：动态规划

我们定义 $f[i][j]$ 表示将前 $i$ 个数变成美丽数组，并且第 $i$ 个数变成 $j+1$ 的最少操作次数。那么答案就是 $\min(f[n][0], f[n][1], f[n][2])$。

我们可以枚举第 $i$ 个数变成 $j+1$ 的所有情况，然后取最小值。这里我们可以用滚动数组优化空间复杂度。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        f = g = h = 0
        for x in nums:
            ff = gg = hh = 0
            if x == 1:
                ff = f
                gg = min(f, g) + 1
                hh = min(f, g, h) + 1
            elif x == 2:
                ff = f + 1
                gg = min(f, g)
                hh = min(f, g, h) + 1
            else:
                ff = f + 1
                gg = min(f, g) + 1
                hh = min(f, g, h)
            f, g, h = ff, gg, hh
        return min(f, g, h)
```

```java
class Solution {
    public int minimumOperations(List<Integer> nums) {
        int[] f = new int[3];
        for (int x : nums) {
            int[] g = new int[3];
            if (x == 1) {
                g[0] = f[0];
                g[1] = Math.min(f[0], f[1]) + 1;
                g[2] = Math.min(f[0], Math.min(f[1], f[2])) + 1;
            } else if (x == 2) {
                g[0] = f[0] + 1;
                g[1] = Math.min(f[0], f[1]);
                g[2] = Math.min(f[0], Math.min(f[1], f[2])) + 1;
            } else {
                g[0] = f[0] + 1;
                g[1] = Math.min(f[0], f[1]) + 1;
                g[2] = Math.min(f[0], Math.min(f[1], f[2]));
            }
            f = g;
        }
        return Math.min(f[0], Math.min(f[1], f[2]));
    }
}
```

```cpp
class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        vector<int> f(3);
        for (int x : nums) {
            vector<int> g(3);
            if (x == 1) {
                g[0] = f[0];
                g[1] = min(f[0], f[1]) + 1;
                g[2] = min({f[0], f[1], f[2]}) + 1;
            } else if (x == 2) {
                g[0] = f[0] + 1;
                g[1] = min(f[0], f[1]);
                g[2] = min(f[0], min(f[1], f[2])) + 1;
            } else {
                g[0] = f[0] + 1;
                g[1] = min(f[0], f[1]) + 1;
                g[2] = min(f[0], min(f[1], f[2]));
            }
            f = move(g);
        }
        return min({f[0], f[1], f[2]});
    }
};
```

```go
func minimumOperations(nums []int) int {
	f := make([]int, 3)
	for _, x := range nums {
		g := make([]int, 3)
		if x == 1 {
			g[0] = f[0]
			g[1] = min(f[0], f[1]) + 1
			g[2] = min(f[0], min(f[1], f[2])) + 1
		} else if x == 2 {
			g[0] = f[0] + 1
			g[1] = min(f[0], f[1])
			g[2] = min(f[0], min(f[1], f[2])) + 1
		} else {
			g[0] = f[0] + 1
			g[1] = min(f[0], f[1]) + 1
			g[2] = min(f[0], min(f[1], f[2]))
		}
		f = g
	}
	return min(f[0], min(f[1], f[2]))
}
```

```ts
function minimumOperations(nums: number[]): number {
    let f: number[] = new Array(3).fill(0);
    for (const x of nums) {
        const g: number[] = new Array(3).fill(0);
        if (x === 1) {
            g[0] = f[0];
            g[1] = Math.min(f[0], f[1]) + 1;
            g[2] = Math.min(f[0], Math.min(f[1], f[2])) + 1;
        } else if (x === 2) {
            g[0] = f[0] + 1;
            g[1] = Math.min(f[0], f[1]);
            g[2] = Math.min(f[0], Math.min(f[1], f[2])) + 1;
        } else {
            g[0] = f[0] + 1;
            g[1] = Math.min(f[0], f[1]) + 1;
            g[2] = Math.min(f[0], Math.min(f[1], f[2]));
        }
        f = g;
    }
    return Math.min(...f);
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        f = [0] * 3
        for x in nums:
            g = [0] * 3
            if x == 1:
                g[0] = f[0]
                g[1] = min(f[:2]) + 1
                g[2] = min(f) + 1
            elif x == 2:
                g[0] = f[0] + 1
                g[1] = min(f[:2])
                g[2] = min(f) + 1
            else:
                g[0] = f[0] + 1
                g[1] = min(f[:2]) + 1
                g[2] = min(f)
            f = g
        return min(f)
```

<!-- tabs:end -->

<!-- end -->
