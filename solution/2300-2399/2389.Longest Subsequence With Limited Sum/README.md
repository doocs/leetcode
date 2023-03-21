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

根据题目描述，对于每个 $queries[i]$，我们需要找到一个子序列，使得该子序列的元素和不超过 $queries[i]$，且该子序列的长度最大化。显然，我们应该选择尽可能小的元素，这样才能使得子序列的长度最大化。

因此，我们可以先将数组 $nums$ 进行升序排序，然后对于每个 $queries[i]$，我们可以使用二分查找，找到最小的下标 $j$，使得 $nums[0] + nums[1] + \cdots + nums[j] \gt queries[i]$。此时 $nums[0] + nums[1] + \cdots + nums[j - 1]$ 就是满足条件的子序列的元素和，且该子序列的长度为 $j$。因此，我们可以将 $j$ 加入答案数组中。

时间复杂度 $O((n + m) \times \log n)$，空间复杂度 $O(n)$ 或 $O(\log n)$。其中 $n$ 和 $m$ 分别是数组 $nums$ 和 $queries$ 的长度。

**方法二：排序 + 离线查询 + 双指针**

与方法一类似，我们可以先对数组 $nums$ 进行升序排列。

接下来，我们定义一个长度与 $queries$ 相同的下标数组 $idx$，其中 $idx[i]=i$，然后我们对数组 $idx$ 按照 $queries$ 中的元素值进行升序排序。这样，我们就可以按照 $queries$ 中的元素值从小到大的顺序进行处理。

我们使用一个变量 $s$ 记录当前已经选择的元素的和，使用一个变量 $j$ 记录当前已经选择的元素的个数。初始时 $s = j = 0$。

我们遍历下标数组 $idx$，对于其中的每个下标 $i$，我们循环地将数组 $nums$ 中的元素加入到当前的子序列中，直到 $s + nums[j] \gt queries[i]$，此时 $j$ 即为满足条件的子序列的长度，我们将 $ans[i]$ 的值设为 $j$，然后继续处理下一个下标。

遍历完下标数组 $idx$ 后，我们即可得到答案数组 $ans$，其中 $ans[i]$ 即为满足 $queries[i]$ 的子序列的长度。

时间复杂度 $O(n \times \log n + m)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别是数组 $nums$ 和 $queries$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def answerQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        nums.sort()
        s = list(accumulate(nums))
        return [bisect_right(s, q) for q in queries]
```

```python
class Solution:
    def answerQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        nums.sort()
        m = len(queries)
        ans = [0] * m
        idx = sorted(range(m), key=lambda i: queries[i])
        s = j = 0
        for i in idx:
            while j < len(nums) and s + nums[j] <= queries[i]:
                s += nums[j]
                j += 1
            ans[i] = j
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i - 1];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            ans[i] = search(nums, queries[i]);
        }
        return ans;
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```java
class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int m = queries.length;
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[i] - queries[j]);
        int[] ans = new int[m];
        int s = 0, j = 0;
        for (int i : idx) {
            while (j < nums.length && s + nums[j] <= queries[i]) {
                s += nums[j++];
            }
            ans[i] = j;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> answerQueries(vector<int>& nums, vector<int>& queries) {
        sort(nums.begin(), nums.end());
        for (int i = 1; i < nums.size(); i++) {
            nums[i] += nums[i - 1];
        }
        vector<int> ans;
        for (auto& q : queries) {
            ans.push_back(upper_bound(nums.begin(), nums.end(), q) - nums.begin());
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> answerQueries(vector<int>& nums, vector<int>& queries) {
        sort(nums.begin(), nums.end());
        int m = queries.size();
        vector<int> idx(m);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return queries[i] < queries[j];
        });
        vector<int> ans(m);
        int s = 0, j = 0;
        for (int i : idx) {
            while (j < nums.size() && s + nums[j] <= queries[i]) {
                s += nums[j++];
            }
            ans[i] = j;
        }
        return ans;
    }
};
```

### **Go**

```go
func answerQueries(nums []int, queries []int) (ans []int) {
	sort.Ints(nums)
	for i := 1; i < len(nums); i++ {
		nums[i] += nums[i-1]
	}
	for _, q := range queries {
		ans = append(ans, sort.SearchInts(nums, q+1))
	}
	return
}
```

```go
func answerQueries(nums []int, queries []int) (ans []int) {
	sort.Ints(nums)
	m := len(queries)
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return queries[idx[i]] < queries[idx[j]] })
	ans = make([]int, m)
	s, j := 0, 0
	for _, i := range idx {
		for j < len(nums) && s+nums[j] <= queries[i] {
			s += nums[j]
			j++
		}
		ans[i] = j
	}
	return
}
```

### **TypeScript**

```ts
function answerQueries(nums: number[], queries: number[]): number[] {
    nums.sort((a, b) => a - b);
    for (let i = 1; i < nums.length; i++) {
        nums[i] += nums[i - 1];
    }
    const ans: number[] = [];
    const search = (nums: number[], x: number) => {
        let l = 0;
        let r = nums.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (const q of queries) {
        ans.push(search(nums, q));
    }
    return ans;
}
```

```ts
function answerQueries(nums: number[], queries: number[]): number[] {
    nums.sort((a, b) => a - b);
    const m = queries.length;
    const idx: number[] = new Array(m);
    for (let i = 0; i < m; i++) {
        idx[i] = i;
    }
    idx.sort((i, j) => queries[i] - queries[j]);
    const ans: number[] = new Array(m);
    let s = 0;
    let j = 0;
    for (const i of idx) {
        while (j < nums.length && s + nums[j] <= queries[i]) {
            s += nums[j++];
        }
        ans[i] = j;
    }
    return ans;
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
