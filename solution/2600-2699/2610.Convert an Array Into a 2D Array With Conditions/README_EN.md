---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2610.Convert%20an%20Array%20Into%20a%202D%20Array%20With%20Conditions/README_EN.md
rating: 1373
source: Weekly Contest 339 Q2
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [2610. Convert an Array Into a 2D Array With Conditions](https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions)

[中文文档](/solution/2600-2699/2610.Convert%20an%20Array%20Into%20a%202D%20Array%20With%20Conditions/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. You need to create a 2D array from <code>nums</code> satisfying the following conditions:</p>

<ul>
	<li>The 2D array should contain <strong>only</strong> the elements of the array <code>nums</code>.</li>
	<li>Each row in the 2D array contains <strong>distinct</strong> integers.</li>
	<li>The number of rows in the 2D array should be <strong>minimal</strong>.</li>
</ul>

<p>Return <em>the resulting array</em>. If there are multiple answers, return any of them.</p>

<p><strong>Note</strong> that the 2D array can have a different number of elements on each row.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,4,1,2,3,1]
<strong>Output:</strong> [[1,3,4,2],[1,3],[1]]
<strong>Explanation:</strong> We can create a 2D array that contains the following rows:
- 1,3,4,2
- 1,3
- 1
All elements of nums were used, and each row of the 2D array contains distinct integers, so it is a valid answer.
It can be shown that we cannot have less than 3 rows in a valid array.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> [[4,3,2,1]]
<strong>Explanation:</strong> All elements of the array are distinct, so we can keep all of them in the first row of the 2D array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i] &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Array or Hash Table

We first use an array or hash table $\textit{cnt}$ to count the frequency of each element in the array $\textit{nums}$.

Then we iterate through $\textit{cnt}$. For each element $x$, we add it to the 0th row, 1st row, 2nd row, ..., and $(cnt[x]-1)$th row of the answer list.

Finally, we return the answer list.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMatrix(self, nums: List[int]) -> List[List[int]]:
        cnt = Counter(nums)
        ans = []
        for x, v in cnt.items():
            for i in range(v):
                if len(ans) <= i:
                    ans.append([])
                ans[i].append(x)
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        int[] cnt = new int[n + 1];
        for (int x : nums) {
            ++cnt[x];
        }
        for (int x = 1; x <= n; ++x) {
            int v = cnt[x];
            for (int j = 0; j < v; ++j) {
                if (ans.size() <= j) {
                    ans.add(new ArrayList<>());
                }
                ans.get(j).add(x);
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
    vector<vector<int>> findMatrix(vector<int>& nums) {
        vector<vector<int>> ans;
        int n = nums.size();
        vector<int> cnt(n + 1);
        for (int& x : nums) {
            ++cnt[x];
        }
        for (int x = 1; x <= n; ++x) {
            int v = cnt[x];
            for (int j = 0; j < v; ++j) {
                if (ans.size() <= j) {
                    ans.push_back({});
                }
                ans[j].push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findMatrix(nums []int) (ans [][]int) {
	n := len(nums)
	cnt := make([]int, n+1)
	for _, x := range nums {
		cnt[x]++
	}
	for x, v := range cnt {
		for j := 0; j < v; j++ {
			if len(ans) <= j {
				ans = append(ans, []int{})
			}
			ans[j] = append(ans[j], x)
		}
	}
	return
}
```

#### TypeScript

```ts
function findMatrix(nums: number[]): number[][] {
    const ans: number[][] = [];
    const n = nums.length;
    const cnt: number[] = Array(n + 1).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    for (let x = 1; x <= n; ++x) {
        for (let j = 0; j < cnt[x]; ++j) {
            if (ans.length <= j) {
                ans.push([]);
            }
            ans[j].push(x);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_matrix(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let n = nums.len();
        let mut cnt = vec![0; n + 1];
        let mut ans: Vec<Vec<i32>> = Vec::new();

        for &x in &nums {
            cnt[x as usize] += 1;
        }

        for x in 1..=n as i32 {
            for j in 0..cnt[x as usize] {
                if ans.len() <= j {
                    ans.push(Vec::new());
                }
                ans[j].push(x);
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
