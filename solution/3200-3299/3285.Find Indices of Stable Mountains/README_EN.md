---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3285.Find%20Indices%20of%20Stable%20Mountains/README_EN.md
rating: 1166
source: Biweekly Contest 139 Q1
tags:
    - Array
---

<!-- problem:start -->

# [3285. Find Indices of Stable Mountains](https://leetcode.com/problems/find-indices-of-stable-mountains)

[中文文档](/solution/3200-3299/3285.Find%20Indices%20of%20Stable%20Mountains/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> mountains in a row, and each mountain has a height. You are given an integer array <code>height</code> where <code>height[i]</code> represents the height of mountain <code>i</code>, and an integer <code>threshold</code>.</p>

<p>A mountain is called <strong>stable</strong> if the mountain just before it (<strong>if it exists</strong>) has a height <strong>strictly greater</strong> than <code>threshold</code>. <strong>Note</strong> that mountain 0 is <strong>not</strong> stable.</p>

<p>Return an array containing the indices of <em>all</em> <strong>stable</strong> mountains in <strong>any</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">height = [1,2,3,4,5], threshold = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,4]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Mountain 3 is stable because <code>height[2] == 3</code> is greater than <code>threshold == 2</code>.</li>
	<li>Mountain 4 is stable because <code>height[3] == 4</code> is greater than <code>threshold == 2</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">height = [10,1,10,1,10], threshold = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,3]</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">height = [10,1,10,1,10], threshold = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == height.length &lt;= 100</code></li>
	<li><code>1 &lt;= height[i] &lt;= 100</code></li>
	<li><code>1 &lt;= threshold &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

We directly traverse the mountains starting from index $1$. If the height of the mountain to its left is greater than $threshold$, we add its index to the result array.

After the traversal, we return the result array.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{height}$. Ignoring the space consumption of the result array, the space complexity is $O(1)$.

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
