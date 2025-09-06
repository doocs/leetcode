---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3410.Maximize%20Subarray%20Sum%20After%20Removing%20All%20Occurrences%20of%20One%20Element/README_EN.md
rating: 2843
source: Biweekly Contest 147 Q4
tags:
    - Segment Tree
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3410. Maximize Subarray Sum After Removing All Occurrences of One Element](https://leetcode.com/problems/maximize-subarray-sum-after-removing-all-occurrences-of-one-element)

[中文文档](/solution/3400-3499/3410.Maximize%20Subarray%20Sum%20After%20Removing%20All%20Occurrences%20of%20One%20Element/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You can do the following operation on the array <strong>at most</strong> once:</p>

<ul>
	<li>Choose <strong>any</strong> integer <code>x</code> such that <code>nums</code> remains <strong>non-empty</strong> on removing all occurrences of <code>x</code>.</li>
	<li>Remove&nbsp;<strong>all</strong> occurrences of <code>x</code> from the array.</li>
</ul>

<p>Return the <strong>maximum</strong> <span data-keyword="subarray-nonempty">subarray</span> sum across <strong>all</strong> possible resulting arrays.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-3,2,-2,-1,3,-2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>We can have the following arrays after at most one operation:</p>

<ul>
	<li>The original array is <code>nums = [<span class="example-io">-3, 2, -2, -1, <u><strong>3, -2, 3</strong></u></span>]</code>. The maximum subarray sum is <code>3 + (-2) + 3 = 4</code>.</li>
	<li>Deleting all occurences of <code>x = -3</code> results in <code>nums = [2, -2, -1, <strong><u><span class="example-io">3, -2, 3</span></u></strong>]</code>. The maximum subarray sum is <code>3 + (-2) + 3 = 4</code>.</li>
	<li>Deleting all occurences of <code>x = -2</code> results in <code>nums = [<span class="example-io">-3, <strong><u>2, -1, 3, 3</u></strong></span>]</code>. The maximum subarray sum is <code>2 + (-1) + 3 + 3 = 7</code>.</li>
	<li>Deleting all occurences of <code>x = -1</code> results in <code>nums = [<span class="example-io">-3, 2, -2, <strong><u>3, -2, 3</u></strong></span>]</code>. The maximum subarray sum is <code>3 + (-2) + 3 = 4</code>.</li>
	<li>Deleting all occurences of <code>x = 3</code> results in <code>nums = [<span class="example-io">-3, <u><strong>2</strong></u>, -2, -1, -2</span>]</code>. The maximum subarray sum is 2.</li>
</ul>

<p>The output is <code>max(4, 4, 7, 4, 2) = 7</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>It is optimal to not perform any operations.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSubarraySum(self, nums: List[int]) -> int:
        max_sum = nums[0]
        min_sum = curr_sum = acc_min = 0
        dp = defaultdict(int)
        for num in nums:
            curr_sum += num
            curr = curr_sum - acc_min
            if max_sum < curr: max_sum = curr
            if num >= 0: continue
            if dp[num] > min_sum: dp[num] = min_sum
            dp[num] += num
            if acc_min > dp[num]: acc_min = dp[num]
            if min_sum > curr_sum: min_sum = curr_sum
        return max_sum
```

#### Java

```java

```

#### C++

```cpp
struct T {
  long sum;
  long maxSubarraySumPrefix;
  long maxSubarraySumSuffix;
  long maxSubarraySum;
  T() = default;
  T(int num)
      : sum(num),
        maxSubarraySumPrefix(num),
        maxSubarraySumSuffix(num),
        maxSubarraySum(num) {}
  T(long sum, long prefix, long suffix, long maxSum)
      : sum(sum),
        maxSubarraySumPrefix(prefix),
        maxSubarraySumSuffix(suffix),
        maxSubarraySum(maxSum) {}
};

class SegmentTree {
 public:
  SegmentTree(const vector<int>& nums) : n(nums.size()), tree(nums.size() * 4) {
    build(nums, 0, 0, n - 1);
  }

  // Updates nums[i] to val.
  void update(int i, int val) {
    update(0, 0, n - 1, i, val);
  }

  long getMaxSubarraySum() const {
    return tree[0].maxSubarraySum;
  }

 private:
  const int n;     // the size of the input array
  vector<T> tree;  // the segment tree

  void build(const vector<int>& nums, int treeIndex, int lo, int hi) {
    if (lo == hi) {
      tree[treeIndex] = T(nums[lo]);
      return;
    }
    const int mid = (lo + hi) / 2;
    build(nums, 2 * treeIndex + 1, lo, mid);
    build(nums, 2 * treeIndex + 2, mid + 1, hi);
    tree[treeIndex] = merge(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
  }

  void update(int treeIndex, int lo, int hi, int i, int val) {
    if (lo == hi) {
      tree[treeIndex] = T(val);
      return;
    }
    const int mid = (lo + hi) / 2;
    if (i <= mid)
      update(2 * treeIndex + 1, lo, mid, i, val);
    else
      update(2 * treeIndex + 2, mid + 1, hi, i, val);
    tree[treeIndex] = merge(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
  }

  T merge(const T& left, const T& right) const {
    return T(
        left.sum + right.sum,
        max(left.maxSubarraySumPrefix, left.sum + right.maxSubarraySumPrefix),
        max(right.maxSubarraySumSuffix, right.sum + left.maxSubarraySumSuffix),
        max({left.maxSubarraySum, right.maxSubarraySum,
             left.maxSubarraySumSuffix + right.maxSubarraySumPrefix}));
  }
};

class Solution {
 public:
  long long maxSubarraySum(vector<int>& nums) {
    const bool allPositives =
        ranges::all_of(nums, [](int num) { return num >= 0; });
    const long sum = accumulate(nums.begin(), nums.end(), 0L);
    if (allPositives)
      return sum;
    const int maxNum = ranges::max(nums);
    if (maxNum < 0)
      return maxNum;

    long ans = LONG_MIN;
    unordered_map<int, vector<int>> numToIndices;
    SegmentTree tree(nums);

    for (int i = 0; i < nums.size(); ++i)
      numToIndices[nums[i]].push_back(i);

    for (const auto& [num, indices] : numToIndices) {
      for (const int index : indices)
        tree.update(index, 0);
      ans = max(ans, tree.getMaxSubarraySum());
      for (const int index : indices)
        tree.update(index, num);
    }

    return ans;
  }
};
```

#### Go

```go
func maxSubarraySum(nums []int) int64 {
	ans := int64(nums[0])
	prefix := int64(0)
	minPrefix := int64(0)

	modifiedMinPrefix := int64(0)
	count := make(map[int]int64)
	minPrefixPlusRemoval := make(map[int]int64)

	for _, num := range nums {
		n64 := int64(num)
		prefix += n64
		if prefix-modifiedMinPrefix > ans {
			ans = prefix - modifiedMinPrefix
		}
		if n64 < 0 {
			count[num]++
			prev := minPrefixPlusRemoval[num] // по умолчанию 0
			minPrefixPlusRemoval[num] = min(prev, minPrefix) + n64
			modifiedMinPrefix = min(modifiedMinPrefix, count[num]*n64)
			modifiedMinPrefix = min(modifiedMinPrefix, minPrefixPlusRemoval[num])
		}
		minPrefix = min(minPrefix, prefix)
		modifiedMinPrefix = min(modifiedMinPrefix, minPrefix)
	}

	return ans
}

func min(a, b int64) int64 {
	if a < b {
		return a
	}
	return b
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn max_subarray_sum(nums: Vec<i32>) -> i64 {
        let mut ans: i64 = *nums.iter().max().unwrap() as i64;
        let mut prefix: i64 = 0;
        let mut min_prefix: i64 = 0;

        let mut modified_min_prefix: i64 = 0;
        let mut count: HashMap<i32, i64> = HashMap::new();
        let mut min_prefix_plus_removal: HashMap<i32, i64> = HashMap::new();

        for &num in nums.iter() {
            let n64 = num as i64;
            prefix += n64;
            ans = ans.max(prefix - modified_min_prefix);

            if n64 < 0 {
                let entry = count.entry(num).or_insert(0);
                *entry += 1;

                let prev = *min_prefix_plus_removal.get(&num).unwrap_or(&0);
                min_prefix_plus_removal.insert(num, prev.min(min_prefix) + n64);

                modified_min_prefix = modified_min_prefix.min(*entry * n64);
                modified_min_prefix = modified_min_prefix.min(*min_prefix_plus_removal.get(&num).unwrap());
            }

            min_prefix = min_prefix.min(prefix);
            modified_min_prefix = modified_min_prefix.min(min_prefix);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
