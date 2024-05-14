---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2023.Number%20of%20Pairs%20of%20Strings%20With%20Concatenation%20Equal%20to%20Target/README_EN.md
rating: 1341
tags:
    - Array
    - Hash Table
    - String
    - Counting
---

# [2023. Number of Pairs of Strings With Concatenation Equal to Target](https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target)

[中文文档](/solution/2000-2099/2023.Number%20of%20Pairs%20of%20Strings%20With%20Concatenation%20Equal%20to%20Target/README.md)

## Description

<p>Given an array of <strong>digit</strong> strings <code>nums</code> and a <strong>digit</strong> string <code>target</code>, return <em>the number of pairs of indices </em><code>(i, j)</code><em> (where </em><code>i != j</code><em>) such that the <strong>concatenation</strong> of </em><code>nums[i] + nums[j]</code><em> equals </em><code>target</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;777&quot;,&quot;7&quot;,&quot;77&quot;,&quot;77&quot;], target = &quot;7777&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> Valid pairs are:
- (0, 1): &quot;777&quot; + &quot;7&quot;
- (1, 0): &quot;7&quot; + &quot;777&quot;
- (2, 3): &quot;77&quot; + &quot;77&quot;
- (3, 2): &quot;77&quot; + &quot;77&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;123&quot;,&quot;4&quot;,&quot;12&quot;,&quot;34&quot;], target = &quot;1234&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Valid pairs are:
- (0, 1): &quot;123&quot; + &quot;4&quot;
- (2, 3): &quot;12&quot; + &quot;34&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;1&quot;,&quot;1&quot;,&quot;1&quot;], target = &quot;11&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> Valid pairs are:
- (0, 1): &quot;1&quot; + &quot;1&quot;
- (1, 0): &quot;1&quot; + &quot;1&quot;
- (0, 2): &quot;1&quot; + &quot;1&quot;
- (2, 0): &quot;1&quot; + &quot;1&quot;
- (1, 2): &quot;1&quot; + &quot;1&quot;
- (2, 1): &quot;1&quot; + &quot;1&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i].length &lt;= 100</code></li>
	<li><code>2 &lt;= target.length &lt;= 100</code></li>
	<li><code>nums[i]</code> and <code>target</code> consist of digits.</li>
	<li><code>nums[i]</code> and <code>target</code> do not have leading zeros.</li>
</ul>

## Solutions

### Solution 1: Enumeration

Traverse the array `nums`, for each $i$, enumerate all $j$, if $i \neq j$ and $nums[i] + nums[j] = target$, then increment the answer by one.

The time complexity is $O(n^2 \times m)$, where $n$ and $m$ are the lengths of the array `nums` and the string `target`, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def numOfPairs(self, nums: List[str], target: str) -> int:
        n = len(nums)
        return sum(
            i != j and nums[i] + nums[j] == target for i in range(n) for j in range(n)
        )
```

```java
class Solution {
    public int numOfPairs(String[] nums, String target) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && target.equals(nums[i] + nums[j])) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numOfPairs(vector<string>& nums, string target) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && nums[i] + nums[j] == target) ++ans;
            }
        }
        return ans;
    }
};
```

```go
func numOfPairs(nums []string, target string) (ans int) {
	for i, a := range nums {
		for j, b := range nums {
			if i != j && a+b == target {
				ans++
			}
		}
	}
	return ans
}
```

<!-- tabs:end -->

### Solution 2: Hash Table

We can use a hash table to count the occurrence of each string in the array `nums`, then traverse all prefixes and suffixes of the string `target`. If both the prefix and suffix are in the hash table, then increment the answer by the product of their occurrences.

The time complexity is $O(n + m^2)$, and the space complexity is $O(n)$. Here, $n$ and $m$ are the lengths of the array `nums` and the string `target`, respectively.

<!-- tabs:start -->

```python
class Solution:
    def numOfPairs(self, nums: List[str], target: str) -> int:
        cnt = Counter(nums)
        ans = 0
        for i in range(1, len(target)):
            a, b = target[:i], target[i:]
            if a != b:
                ans += cnt[a] * cnt[b]
            else:
                ans += cnt[a] * (cnt[a] - 1)
        return ans
```

```java
class Solution {
    public int numOfPairs(String[] nums, String target) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int ans = 0;
        for (int i = 1; i < target.length(); ++i) {
            String a = target.substring(0, i);
            String b = target.substring(i);
            int x = cnt.getOrDefault(a, 0);
            int y = cnt.getOrDefault(b, 0);
            if (!a.equals(b)) {
                ans += x * y;
            } else {
                ans += x * (y - 1);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numOfPairs(vector<string>& nums, string target) {
        unordered_map<string, int> cnt;
        for (auto& x : nums) ++cnt[x];
        int ans = 0;
        for (int i = 1; i < target.size(); ++i) {
            string a = target.substr(0, i);
            string b = target.substr(i);
            int x = cnt[a], y = cnt[b];
            if (a != b) {
                ans += x * y;
            } else {
                ans += x * (y - 1);
            }
        }
        return ans;
    }
};
```

```go
func numOfPairs(nums []string, target string) (ans int) {
	cnt := map[string]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for i := 1; i < len(target); i++ {
		a, b := target[:i], target[i:]
		if a != b {
			ans += cnt[a] * cnt[b]
		} else {
			ans += cnt[a] * (cnt[a] - 1)
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
