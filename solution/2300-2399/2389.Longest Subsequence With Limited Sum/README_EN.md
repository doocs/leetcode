# [2389. Longest Subsequence With Limited Sum](https://leetcode.com/problems/longest-subsequence-with-limited-sum)

[中文文档](/solution/2300-2399/2389.Longest%20Subsequence%20With%20Limited%20Sum/README.md)

## Description

<p>You are given an integer array <code>nums</code> of length <code>n</code>, and an integer array <code>queries</code> of length <code>m</code>.</p>

<p>Return <em>an array </em><code>answer</code><em> of length </em><code>m</code><em> where </em><code>answer[i]</code><em> is the <strong>maximum</strong> size of a <strong>subsequence</strong> that you can take from </em><code>nums</code><em> such that the <strong>sum</strong> of its elements is less than or equal to </em><code>queries[i]</code>.</p>

<p>A <strong>subsequence</strong> is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,5,2,1], queries = [3,10,21]
<strong>Output:</strong> [2,3,4]
<strong>Explanation:</strong> We answer the queries as follows:
- The subsequence [2,1] has a sum less than or equal to 3. It can be proven that 2 is the maximum size of such a subsequence, so answer[0] = 2.
- The subsequence [4,5,1] has a sum less than or equal to 10. It can be proven that 3 is the maximum size of such a subsequence, so answer[1] = 3.
- The subsequence [4,5,2,1] has a sum less than or equal to 21. It can be proven that 4 is the maximum size of such a subsequence, so answer[2] = 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,4,5], queries = [1]
<strong>Output:</strong> [0]
<strong>Explanation:</strong> The empty subsequence is the only subsequence that has a sum less than or equal to 1, so answer[0] = 0.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == queries.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], queries[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def answerQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        nums.sort()
        s = list(accumulate(nums))
        return [bisect_right(s, v) for v in queries]
```

### **Java**

```java
class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            ans[i] = search(s, queries[i]);
        }
        return ans;
    }

    private int search(int[] s, int v) {
        int left = 1, right = s.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (s[mid] > v) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> answerQueries(vector<int>& nums, vector<int>& queries) {
        sort(nums.begin(), nums.end());
        int n = nums.size(), m = queries.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        vector<int> ans(m);
        for (int i = 0; i < m; ++i) {
            ans[i] = upper_bound(s.begin() + 1, s.end(), queries[i]) - s.begin() - 1;
        }
        return ans;
    }
};
```

### **Go**

```go
func answerQueries(nums []int, queries []int) []int {
	sort.Ints(nums)
	n, m := len(nums), len(queries)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	ans := make([]int, m)
	for i, v := range queries {
		left, right := 1, len(s)
		for left < right {
			mid := (left + right) >> 1
			if s[mid] > v {
				right = mid
			} else {
				left = mid + 1
			}
		}
		ans[i] = left - 1
	}
	return ans
}
```

### **TypeScript**

```ts
function answerQueries(nums: number[], queries: number[]): number[] {
    const n = nums.length;
    nums.sort((a, b) => a - b);
    return queries.map(query => {
        let sum = 0;
        for (let i = 0; i < n; i++) {
            sum += nums[i];
            if (sum > query) {
                return i;
            }
        }
        return n;
    });
}
```

### **Rust**

```rust
impl Solution {
    pub fn answer_queries(mut nums: Vec<i32>, queries: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        nums.sort();
        queries
            .into_iter()
            .map(|query| {
                let mut sum = 0;
                for i in 0..n {
                    sum += nums[i];
                    if sum > query {
                        return i as i32;
                    }
                }
                n as i32
            })
            .collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
