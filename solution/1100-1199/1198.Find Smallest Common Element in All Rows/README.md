# [1198. 找出所有行中最小公共元素 🔒](https://leetcode.cn/problems/find-smallest-common-element-in-all-rows)

[English Version](/solution/1100-1199/1198.Find%20Smallest%20Common%20Element%20in%20All%20Rows/README_EN.md)

<!-- tags:数组,哈希表,二分查找,计数,矩阵 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的矩阵&nbsp;<code>mat</code>，其中每一行的元素均符合&nbsp;<strong>严格递增</strong> 。请返回 <em>所有行中的&nbsp;<strong>最小公共元素</strong>&nbsp;</em>。</p>

<p>如果矩阵中没有这样的公共元素，就请返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
<strong>输出：</strong>5
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>mat = [[1,2,3],[2,3,4],[2,3,5]]
<strong>输出：</strong> 2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 10<sup>4</sup></code></li>
	<li><code>mat[i]</code>&nbsp;已按严格递增顺序排列。</li>
</ul>

## 解法

### 方法一：计数

我们用一个长度为 $10001$ 的数组 $cnt$ 统计每个数出现的次数。顺序遍历矩阵中的每个数，将其出现次数加一。当某个数的出现次数等于矩阵的行数时，说明该数在每一行都出现过，即为最小公共元素，返回该数即可。

若遍历结束后没有找到最小公共元素，则返回 $-1$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(10^4)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def smallestCommonElement(self, mat: List[List[int]]) -> int:
        cnt = Counter()
        for row in mat:
            for x in row:
                cnt[x] += 1
                if cnt[x] == len(mat):
                    return x
        return -1
```

```java
class Solution {
    public int smallestCommonElement(int[][] mat) {
        int[] cnt = new int[10001];
        for (var row : mat) {
            for (int x : row) {
                if (++cnt[x] == mat.length) {
                    return x;
                }
            }
        }
        return -1;
    }
}
```

```cpp
class Solution {
public:
    int smallestCommonElement(vector<vector<int>>& mat) {
        int cnt[10001]{};
        for (auto& row : mat) {
            for (int x : row) {
                if (++cnt[x] == mat.size()) {
                    return x;
                }
            }
        }
        return -1;
    }
};
```

```go
func smallestCommonElement(mat [][]int) int {
	cnt := [10001]int{}
	for _, row := range mat {
		for _, x := range row {
			cnt[x]++
			if cnt[x] == len(mat) {
				return x
			}
		}
	}
	return -1
}
```

```ts
function smallestCommonElement(mat: number[][]): number {
    const cnt: number[] = new Array(10001).fill(0);
    for (const row of mat) {
        for (const x of row) {
            if (++cnt[x] == mat.length) {
                return x;
            }
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- end -->
