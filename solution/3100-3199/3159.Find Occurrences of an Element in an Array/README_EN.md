---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3159.Find%20Occurrences%20of%20an%20Element%20in%20an%20Array/README_EN.md
---

<!-- problem:start -->

# [3159. Find Occurrences of an Element in an Array](https://leetcode.com/problems/find-occurrences-of-an-element-in-an-array)

[中文文档](/solution/3100-3199/3159.Find%20Occurrences%20of%20an%20Element%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>, an integer array <code>queries</code>, and an integer <code>x</code>.</p>

<p>For each <code>queries[i]</code>, you need to find the index of the <code>queries[i]<sup>th</sup></code> occurrence of <code>x</code> in the <code>nums</code> array. If there are fewer than <code>queries[i]</code> occurrences of <code>x</code>, the answer should be -1 for that query.</p>

<p>Return an integer array <code>answer</code> containing the answers to all queries.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,1,7], queries = [1,3,2,4], x = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,-1,2,-1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For the 1<sup>st</sup> query, the first occurrence of 1 is at index 0.</li>
	<li>For the 2<sup>nd</sup> query, there are only two occurrences of 1 in <code>nums</code>, so the answer is -1.</li>
	<li>For the 3<sup>rd</sup> query, the second occurrence of 1 is at index 2.</li>
	<li>For the 4<sup>th</sup> query, there are only two occurrences of 1 in <code>nums</code>, so the answer is -1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], queries = [10], x = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For the 1<sup>st</sup> query, 5 doesn&#39;t exist in <code>nums</code>, so the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], x &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

According to the problem description, we can first traverse the array `nums` to find the indices of all elements with a value of $x$, and record them in the array `ids`.

Next, we traverse the array `queries`. For each query $i$, if $i - 1$ is less than the length of `ids`, then the answer is `ids[i - 1]`, otherwise, the answer is $-1$.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$. Where $n$ and $m$ are the lengths of the arrays `nums` and `queries` respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def occurrencesOfElement(
        self, nums: List[int], queries: List[int], x: int
    ) -> List[int]:
        ids = [i for i, v in enumerate(nums) if v == x]
        return [ids[i - 1] if i - 1 < len(ids) else -1 for i in queries]
```

#### Java

```java
class Solution {
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == x) {
                ids.add(i);
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int j = queries[i] - 1;
            ans[i] = j < ids.size() ? ids.get(j) : -1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> occurrencesOfElement(vector<int>& nums, vector<int>& queries, int x) {
        vector<int> ids;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == x) {
                ids.push_back(i);
            }
        }
        vector<int> ans;
        for (int& i : queries) {
            ans.push_back(i - 1 < ids.size() ? ids[i - 1] : -1);
        }
        return ans;
    }
};
```

#### Go

```go
func occurrencesOfElement(nums []int, queries []int, x int) (ans []int) {
	ids := []int{}
	for i, v := range nums {
		if v == x {
			ids = append(ids, i)
		}
	}
	for _, i := range queries {
		if i-1 < len(ids) {
			ans = append(ans, ids[i-1])
		} else {
			ans = append(ans, -1)
		}
	}
	return
}
```

#### TypeScript

```ts
function occurrencesOfElement(nums: number[], queries: number[], x: number): number[] {
    const ids: number[] = nums.map((v, i) => (v === x ? i : -1)).filter(v => v !== -1);
    return queries.map(i => ids[i - 1] ?? -1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
