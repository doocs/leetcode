---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1762.Buildings%20With%20an%20Ocean%20View/README_EN.md
tags:
    - Stack
    - Array
    - Monotonic Stack
---

<!-- problem:start -->

# [1762. Buildings With an Ocean View 🔒](https://leetcode.com/problems/buildings-with-an-ocean-view)

[中文文档](/solution/1700-1799/1762.Buildings%20With%20an%20Ocean%20View/README.md)

## Description

<p>There are <code>n</code> buildings in a line. You are given an integer array <code>heights</code> of size <code>n</code> that represents the heights of the buildings in the line.</p>

<p>The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a <strong>smaller</strong> height.</p>

<p>Return a list of indices <strong>(0-indexed)</strong> of buildings that have an ocean view, sorted in increasing order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> heights = [4,2,3,1]
<strong>Output:</strong> [0,2,3]
<strong>Explanation:</strong> Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> heights = [4,3,2,1]
<strong>Output:</strong> [0,1,2,3]
<strong>Explanation:</strong> All the buildings have an ocean view.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> heights = [1,3,2,4]
<strong>Output:</strong> [3]
<strong>Explanation:</strong> Only building 3 has an ocean view.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= heights[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def findBuildings(self, heights: List[int]) -> List[int]:
        ans = []
        mx = 0
        for i in range(len(heights) - 1, -1, -1):
            if heights[i] > mx:
                ans.append(i)
                mx = heights[i]
        return ans[::-1]
```

```java
class Solution {
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        List<Integer> ans = new ArrayList<>();
        int mx = 0;
        for (int i = heights.length - 1; i >= 0; --i) {
            if (heights[i] > mx) {
                ans.add(i);
                mx = heights[i];
            }
        }
        Collections.reverse(ans);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
```

```cpp
class Solution {
public:
    vector<int> findBuildings(vector<int>& heights) {
        vector<int> ans;
        int mx = 0;
        for (int i = heights.size() - 1; ~i; --i) {
            if (heights[i] > mx) {
                ans.push_back(i);
                mx = heights[i];
            }
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

```go
func findBuildings(heights []int) (ans []int) {
	mx := 0
	for i := len(heights) - 1; i >= 0; i-- {
		if v := heights[i]; v > mx {
			ans = append(ans, i)
			mx = v
		}
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return
}
```

```ts
function findBuildings(heights: number[]): number[] {
    const ans: number[] = [];
    let mx = 0;
    for (let i = heights.length - 1; ~i; --i) {
        if (heights[i] > mx) {
            ans.push(i);
            mx = heights[i];
        }
    }
    return ans.reverse();
}
```

```js
/**
 * @param {number[]} heights
 * @return {number[]}
 */
var findBuildings = function (heights) {
    const ans = [];
    let mx = 0;
    for (let i = heights.length - 1; ~i; --i) {
        if (heights[i] > mx) {
            ans.push(i);
            mx = heights[i];
        }
    }
    return ans.reverse();
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
