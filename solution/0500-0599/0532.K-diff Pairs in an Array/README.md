# [532. 数组中的 k-diff 数对](https://leetcode.cn/problems/k-diff-pairs-in-an-array)

[English Version](/solution/0500-0599/0532.K-diff%20Pairs%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code> 和一个整数&nbsp;<code>k</code>，请你在数组中找出<strong> 不同的&nbsp;</strong>k-diff 数对，并返回不同的 <strong>k-diff 数对</strong> 的数目。</p>

<p><strong>k-diff</strong>&nbsp;数对定义为一个整数对 <code>(nums[i], nums[j])</code><strong> </strong>，并满足下述全部条件：</p>

<ul>
	<li><code>0 &lt;= i, j &lt; nums.length</code></li>
	<li><code>i != j</code></li>
	<li><code>nums[i] - nums[j] == k</code></li>
</ul>

<p><strong>注意</strong>，<code>|val|</code> 表示 <code>val</code> 的绝对值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3, 1, 4, 1, 5], k = 2
<strong>输出：</strong>2
<strong>解释：</strong>数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
尽管数组中有两个 1 ，但我们只应返回不同的数对的数量。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1, 2, 3, 4, 5], k = 1
<strong>输出：</strong>4
<strong>解释：</strong>数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5) 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1, 3, 1, 5, 4], k = 0
<strong>输出：</strong>1
<strong>解释：</strong>数组中只有一个 0-diff 数对，(1, 1) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>7</sup> &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>7</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

由于 $k$ 是一个定值，因此用哈希表 $ans$ 记录数对的较小值，就能够确定较大的值。最后返回 ans 的大小作为答案。

遍历数组 $nums$，当前遍历到的数 $nums[j]$，我们记为 $v$，用哈希表 $vis$ 记录此前遍历到的所有数字。若 $v-k$ 在 $vis$ 中，则将 $v-k$ 添加至 $ans$；若 $v+k$ 在 $vis$ 中，则将 $v$ 添加至 $ans$。

时间复杂度 $O(n)$，其中 $n$ 表示数组 $nums$ 的长度。

**方法二：排序 + 双指针**

只需要统计组合的数量，因此可以改动原数组，对其排序，使用双指针来统计。

声明 `left` 与 `right` 指针，初始化为 0 和 1。根据 `abs(nums[left] - nums[right])` 与 `k` 值对比结果移动指针。

需要注意的是，**不能出现重复的组合**，所以移动指针时，不能仅仅是 `+1`，需要到一个不等于当前值的位置。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findPairs(self, nums: List[int], k: int) -> int:
        vis, ans = set(), set()
        for v in nums:
            if v - k in vis:
                ans.add(v - k)
            if v + k in vis:
                ans.add(v)
            vis.add(v)
        return len(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findPairs(int[] nums, int k) {
        Set<Integer> vis = new HashSet<>();
        Set<Integer> ans = new HashSet<>();
        for (int v : nums) {
            if (vis.contains(v - k)) {
                ans.add(v - k);
            }
            if (vis.contains(v + k)) {
                ans.add(v);
            }
            vis.add(v);
        }
        return ans.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findPairs(vector<int>& nums, int k) {
        unordered_set<int> vis;
        unordered_set<int> ans;
        for (int& v : nums) {
            if (vis.count(v - k)) ans.insert(v - k);
            if (vis.count(v + k)) ans.insert(v);
            vis.insert(v);
        }
        return ans.size();
    }
};
```

### **Go**

```go
func findPairs(nums []int, k int) int {
	vis := map[int]bool{}
	ans := map[int]bool{}
	for _, v := range nums {
		if vis[v-k] {
			ans[v-k] = true
		}
		if vis[v+k] {
			ans[v] = true
		}
		vis[v] = true
	}
	return len(ans)
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_pairs(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let n = nums.len();
        let mut res = 0;
        let mut left = 0;
        let mut right = 1;
        while right < n {
            let num = i32::abs(nums[left] - nums[right]);
            if num == k {
                res += 1;
            }
            if num <= k {
                right += 1;
                while right < n && nums[right - 1] == nums[right] {
                    right += 1;
                }
            } else {
                left += 1;
                while left < right && nums[left - 1] == nums[left] {
                    left += 1;
                }
                if left == right {
                    right += 1;
                }
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
