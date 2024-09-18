---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3285.Find%20Indices%20of%20Stable%20Mountains/README.md
tags:
    - 数组
---

<!-- problem:start -->

# [3285. 找到稳定山的下标](https://leetcode.cn/problems/find-indices-of-stable-mountains)

[English Version](/solution/3200-3299/3285.Find%20Indices%20of%20Stable%20Mountains/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有&nbsp;<code>n</code>&nbsp;座山排成一列，每座山都有一个高度。给你一个整数数组&nbsp;<code>height</code>&nbsp;，其中&nbsp;<code>height[i]</code>&nbsp;表示第 <code>i</code>&nbsp;座山的高度，再给你一个整数&nbsp;<code>threshold</code>&nbsp;。</p>

<p>对于下标不为 <code>0</code>&nbsp;的一座山，如果它左侧相邻的山的高度 <strong>严格</strong><strong>大于</strong>&nbsp;<code>threshold</code>&nbsp;，那么我们称它是 <strong>稳定</strong>&nbsp;的。我们定义下标为 <code>0</code>&nbsp;的山 <strong>不是</strong>&nbsp;稳定的。</p>

<p>请你返回一个数组，包含所有 <strong>稳定</strong>&nbsp;山的下标，你可以以 <strong>任意</strong>&nbsp;顺序返回下标数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>height = [1,2,3,4,5], threshold = 2</span></p>

<p><span class="example-io"><b>输出：</b>[3,4]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标为 3 的山是稳定的，因为&nbsp;<code>height[2] == 3</code>&nbsp;大于&nbsp;<code>threshold == 2</code>&nbsp;。</li>
	<li>下标为 4 的山是稳定的，因为&nbsp;<code>height[3] == 4</code> 大于 <code>threshold == 2</code>.</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>height = [10,1,10,1,10], threshold = 3</span></p>

<p><span class="example-io"><b>输出：</b>[1,3]</span></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>height = [10,1,10,1,10], threshold = 10</span></p>

<p><span class="example-io"><b>输出：</b>[]</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == height.length &lt;= 100</code></li>
	<li><code>1 &lt;= height[i] &lt;= 100</code></li>
	<li><code>1 &lt;= threshold &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

我们直接从下标为 $1$ 的山开始遍历，如果它左侧相邻的山的高度大于 $threshold$，那么我们就将它的下标加入到结果数组中。

遍历结束后，返回结果数组即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{height}$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stableMountains(self, height: List[int], threshold: int) -> List[int]:
        return [i for i in range(1, len(height)) if height[i - 1] > threshold]
```

#### Java

```java
class Solution {
    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < height.length; ++i) {
            if (height[i - 1] > threshold) {
                ans.add(i);
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
    vector<int> stableMountains(vector<int>& height, int threshold) {
        vector<int> ans;
        for (int i = 1; i < height.size(); ++i) {
            if (height[i - 1] > threshold) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func stableMountains(height []int, threshold int) (ans []int) {
	for i := 1; i < len(height); i++ {
		if height[i-1] > threshold {
			ans = append(ans, i)
		}
	}
	return
}
```

#### TypeScript

```ts
function stableMountains(height: number[], threshold: number): number[] {
    const ans: number[] = [];
    for (let i = 1; i < height.length; ++i) {
        if (height[i - 1] > threshold) {
            ans.push(i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
