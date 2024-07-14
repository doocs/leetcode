---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2951.Find%20the%20Peaks/README_EN.md
rating: 1189
source: Weekly Contest 374 Q1
tags:
    - Array
    - Enumeration
---

<!-- problem:start -->

# [2951. Find the Peaks](https://leetcode.com/problems/find-the-peaks)

[中文文档](/solution/2900-2999/2951.Find%20the%20Peaks/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>mountain</code>. Your task is to find all the <strong>peaks</strong> in the <code>mountain</code> array.</p>

<p>Return <em>an array that consists of </em>indices<!-- notionvc: c9879de8-88bd-43b0-8224-40c4bee71cd6 --><em> of <strong>peaks</strong> in the given array in <strong>any order</strong>.</em></p>

<p><strong>Notes:</strong></p>

<ul>
	<li>A <strong>peak</strong> is defined as an element that is <strong>strictly greater</strong> than its neighboring elements.</li>
	<li>The first and last elements of the array are <strong>not</strong> a peak.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> mountain = [2,4,4]
<strong>Output:</strong> []
<strong>Explanation:</strong> mountain[0] and mountain[2] can not be a peak because they are first and last elements of the array.
mountain[1] also can not be a peak because it is not strictly greater than mountain[2].
So the answer is [].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mountain = [1,4,3,8,5]
<strong>Output:</strong> [1,3]
<strong>Explanation:</strong> mountain[0] and mountain[4] can not be a peak because they are first and last elements of the array.
mountain[2] also can not be a peak because it is not strictly greater than mountain[3] and mountain[1].
But mountain [1] and mountain[3] are strictly greater than their neighboring elements.
So the answer is [1,3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= mountain.length &lt;= 100</code></li>
	<li><code>1 &lt;= mountain[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Direct Traversal

We directly traverse the index $i \in [1, n-2]$. For each index $i$, if $mountain[i-1] < mountain[i]$ and $mountain[i + 1] < mountain[i]$, then $mountain[i]$ is a peak, and we add index $i$ to the answer array.

After the traversal ends, we return the answer array.

The time complexity is $O(n)$, where $n$ is the length of the array. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findPeaks(self, mountain: List[int]) -> List[int]:
        return [
            i
            for i in range(1, len(mountain) - 1)
            if mountain[i - 1] < mountain[i] > mountain[i + 1]
        ]
```

#### Java

```java
class Solution {
    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < mountain.length - 1; ++i) {
            if (mountain[i - 1] < mountain[i] && mountain[i + 1] < mountain[i]) {
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
    vector<int> findPeaks(vector<int>& mountain) {
        vector<int> ans;
        for (int i = 1; i < mountain.size() - 1; ++i) {
            if (mountain[i - 1] < mountain[i] && mountain[i + 1] < mountain[i]) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findPeaks(mountain []int) (ans []int) {
	for i := 1; i < len(mountain)-1; i++ {
		if mountain[i-1] < mountain[i] && mountain[i+1] < mountain[i] {
			ans = append(ans, i)
		}
	}
	return
}
```

#### TypeScript

```ts
function findPeaks(mountain: number[]): number[] {
    const ans: number[] = [];
    for (let i = 1; i < mountain.length - 1; ++i) {
        if (mountain[i - 1] < mountain[i] && mountain[i + 1] < mountain[i]) {
            ans.push(i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
