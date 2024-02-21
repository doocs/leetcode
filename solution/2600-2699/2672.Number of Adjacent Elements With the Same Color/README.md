# [2672. 有相同颜色的相邻元素数目](https://leetcode.cn/problems/number-of-adjacent-elements-with-the-same-color)

[English Version](/solution/2600-2699/2672.Number%20of%20Adjacent%20Elements%20With%20the%20Same%20Color/README_EN.md)

<!-- tags:数组 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始、长度为 <code>n</code>&nbsp;的数组&nbsp;<code>nums</code>&nbsp;。一开始，所有元素都是 <strong>未染色</strong>&nbsp;（值为 <code>0</code>&nbsp;）的。</p>

<p>给你一个二维整数数组&nbsp;<code>queries</code>&nbsp;，其中&nbsp;<code>queries[i] = [index<sub>i</sub>, color<sub>i</sub>]</code>&nbsp;。</p>

<p>对于每个操作，你需要将数组 <code>nums</code>&nbsp;中下标为&nbsp;<code>index<sub>i</sub></code>&nbsp;的格子染色为&nbsp;<code>color<sub>i</sub></code>&nbsp;。</p>

<p>请你返回一个长度与 <code>queries</code>&nbsp;相等的数组<em>&nbsp;</em><code>answer</code><em>&nbsp;</em>，其中<em>&nbsp;</em><code>answer[i]</code>是前 <code>i</code>&nbsp;个操作&nbsp;<strong>之后</strong>&nbsp;，相邻元素颜色相同的数目。</p>

<p>更正式的，<code>answer[i]</code>&nbsp;是执行完前 <code>i</code>&nbsp;个操作后，<code>0 &lt;= j &lt; n - 1</code>&nbsp;的下标 <code>j</code>&nbsp;中，满足&nbsp;<code>nums[j] == nums[j + 1]</code> 且&nbsp;<code>nums[j] != 0</code>&nbsp;的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]]
<b>输出：</b>[0,1,1,0,2]
<b>解释：</b>一开始数组 nums = [0,0,0,0] ，0 表示数组中还没染色的元素。
- 第 1 个操作后，nums = [2,0,0,0] 。相邻元素颜色相同的数目为 0 。
- 第 2 个操作后，nums = [2,2,0,0] 。相邻元素颜色相同的数目为 1 。
- 第 3 个操作后，nums = [2,2,0,1] 。相邻元素颜色相同的数目为 1 。
- 第 4 个操作后，nums = [2,1,0,1] 。相邻元素颜色相同的数目为 0 。
- 第 5 个操作后，nums = [2,1,1,1] 。相邻元素颜色相同的数目为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 1, queries = [[0,100000]]
<b>输出：</b>[0]
<b>解释：</b>一开始数组 nums = [0] ，0 表示数组中还没染色的元素。
- 第 1 个操作后，nums = [100000] 。相邻元素颜色相同的数目为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length&nbsp;== 2</code></li>
	<li><code>0 &lt;= index<sub>i</sub>&nbsp;&lt;= n - 1</code></li>
	<li><code>1 &lt;=&nbsp; color<sub>i</sub>&nbsp;&lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def colorTheArray(self, n: int, queries: List[List[int]]) -> List[int]:
        nums = [0] * n
        ans = [0] * len(queries)
        x = 0
        for k, (i, c) in enumerate(queries):
            if i > 0 and nums[i] and nums[i - 1] == nums[i]:
                x -= 1
            if i < n - 1 and nums[i] and nums[i + 1] == nums[i]:
                x -= 1
            if i > 0 and nums[i - 1] == c:
                x += 1
            if i < n - 1 and nums[i + 1] == c:
                x += 1
            ans[k] = x
            nums[i] = c
        return ans
```

```java
class Solution {
    public int[] colorTheArray(int n, int[][] queries) {
        int m = queries.length;
        int[] nums = new int[n];
        int[] ans = new int[m];
        for (int k = 0, x = 0; k < m; ++k) {
            int i = queries[k][0], c = queries[k][1];
            if (i > 0 && nums[i] > 0 && nums[i - 1] == nums[i]) {
                --x;
            }
            if (i < n - 1 && nums[i] > 0 && nums[i + 1] == nums[i]) {
                --x;
            }
            if (i > 0 && nums[i - 1] == c) {
                ++x;
            }
            if (i < n - 1 && nums[i + 1] == c) {
                ++x;
            }
            ans[k] = x;
            nums[i] = c;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> colorTheArray(int n, vector<vector<int>>& queries) {
        vector<int> nums(n);
        vector<int> ans;
        int x = 0;
        for (auto& q : queries) {
            int i = q[0], c = q[1];
            if (i > 0 && nums[i] > 0 && nums[i - 1] == nums[i]) {
                --x;
            }
            if (i < n - 1 && nums[i] > 0 && nums[i + 1] == nums[i]) {
                --x;
            }
            if (i > 0 && nums[i - 1] == c) {
                ++x;
            }
            if (i < n - 1 && nums[i + 1] == c) {
                ++x;
            }
            ans.push_back(x);
            nums[i] = c;
        }
        return ans;
    }
};
```

```go
func colorTheArray(n int, queries [][]int) (ans []int) {
	nums := make([]int, n)
	x := 0
	for _, q := range queries {
		i, c := q[0], q[1]
		if i > 0 && nums[i] > 0 && nums[i-1] == nums[i] {
			x--
		}
		if i < n-1 && nums[i] > 0 && nums[i+1] == nums[i] {
			x--
		}
		if i > 0 && nums[i-1] == c {
			x++
		}
		if i < n-1 && nums[i+1] == c {
			x++
		}
		ans = append(ans, x)
		nums[i] = c
	}
	return
}
```

```ts
function colorTheArray(n: number, queries: number[][]): number[] {
    const nums: number[] = new Array(n).fill(0);
    const ans: number[] = [];
    let x = 0;
    for (const [i, c] of queries) {
        if (i > 0 && nums[i] > 0 && nums[i - 1] == nums[i]) {
            --x;
        }
        if (i < n - 1 && nums[i] > 0 && nums[i + 1] == nums[i]) {
            --x;
        }
        if (i > 0 && nums[i - 1] == c) {
            ++x;
        }
        if (i < n - 1 && nums[i + 1] == c) {
            ++x;
        }
        ans.push(x);
        nums[i] = c;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
