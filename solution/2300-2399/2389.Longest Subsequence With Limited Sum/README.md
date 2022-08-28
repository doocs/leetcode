# [2389. 和有限的最长子序列](https://leetcode.cn/problems/longest-subsequence-with-limited-sum)

[English Version](/solution/2300-2399/2389.Longest%20Subsequence%20With%20Limited%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组 <code>nums</code> ，和一个长度为 <code>m</code> 的整数数组 <code>queries</code> 。</p>

<p>返回一个长度为 <code>m</code> 的数组<em> </em><code>answer</code><em> </em>，其中<em> </em><code>answer[i]</code><em> </em>是 <code>nums</code> 中<span style=""> </span>元素之和小于等于 <code>queries[i]</code> 的 <strong>子序列</strong> 的 <strong>最大</strong> 长度<span style="">&nbsp;</span><span style=""> </span>。</p>

<p><strong>子序列</strong> 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,5,2,1], queries = [3,10,21]
<strong>输出：</strong>[2,3,4]
<strong>解释：</strong>queries 对应的 answer 如下：
- 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
- 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
- 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,4,5], queries = [1]
<strong>输出：</strong>[0]
<strong>解释：</strong>空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == queries.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], queries[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 前缀和 + 二分查找**

将 `nums` 排序，对于每个 `queries[i]`，求出 `nums` 中所有元素之和小于等于 `queries[i]` 的子序列的最大长度。

时间复杂度 $O(n\log n + m\log n)$。其中 $n$ 为 `nums` 的长度，$m$ 为 `queries` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def answerQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        nums.sort()
        s = list(accumulate(nums))
        return [bisect_right(s, v) for v in queries]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    return queries.map(querie => {
        let sum = 0;
        for (let i = 0; i < n; i++) {
            sum += nums[i];
            if (sum > querie) {
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
            .map(|querie| {
                let mut sum = 0;
                for i in 0..n {
                    sum += nums[i];
                    if sum > querie {
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
