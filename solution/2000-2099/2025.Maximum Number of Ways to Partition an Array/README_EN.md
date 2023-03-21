# [2025. Maximum Number of Ways to Partition an Array](https://leetcode.com/problems/maximum-number-of-ways-to-partition-an-array)

[中文文档](/solution/2000-2099/2025.Maximum%20Number%20of%20Ways%20to%20Partition%20an%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code>. The number of ways to <strong>partition</strong> <code>nums</code> is the number of <code>pivot</code> indices that satisfy both conditions:</p>

<ul>
	<li><code>1 &lt;= pivot &lt; n</code></li>
	<li><code>nums[0] + nums[1] + ... + nums[pivot - 1] == nums[pivot] + nums[pivot + 1] + ... + nums[n - 1]</code></li>
</ul>

<p>You are also given an integer <code>k</code>. You can choose to change the value of <strong>one</strong> element of <code>nums</code> to <code>k</code>, or to leave the array <strong>unchanged</strong>.</p>

<p>Return <em>the <strong>maximum</strong> possible number of ways to <strong>partition</strong> </em><code>nums</code><em> to satisfy both conditions after changing <strong>at most</strong> one element</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,-1,2], k = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> One optimal approach is to change nums[0] to k. The array becomes [<strong><u>3</u></strong>,-1,2].
There is one way to partition the array:
- For pivot = 2, we have the partition [3,-1 | 2]: 3 + -1 == 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0], k = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The optimal approach is to leave the array unchanged.
There are two ways to partition the array:
- For pivot = 1, we have the partition [0 | 0,0]: 0 == 0 + 0.
- For pivot = 2, we have the partition [0,0 | 0]: 0 + 0 == 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14], k = -33
<strong>Output:</strong> 4
<strong>Explanation:</strong> One optimal approach is to change nums[2] to k. The array becomes [22,4,<u><strong>-33</strong></u>,-20,-15,15,-16,7,19,-10,0,-13,-14].
There are four ways to partition the array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= k, nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def waysToPartition(self, nums: List[int], k: int) -> int:
        n = len(nums)
        s = [nums[0]] * n
        right = defaultdict(int)
        for i in range(1, n):
            s[i] = s[i - 1] + nums[i]
            right[s[i - 1]] += 1

        ans = 0
        if s[-1] % 2 == 0:
            ans = right[s[-1] // 2]

        left = defaultdict(int)
        for v, x in zip(s, nums):
            d = k - x
            if (s[-1] + d) % 2 == 0:
                t = left[(s[-1] + d) // 2] + right[(s[-1] - d) // 2]
                if ans < t:
                    ans = t
            left[v] += 1
            right[v] -= 1
        return ans
```

### **Java**

```java
class Solution {
    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n];
        s[0] = nums[0];
        Map<Integer, Integer> right = new HashMap<>();
        for (int i = 0; i < n - 1; ++i) {
            right.merge(s[i], 1, Integer::sum);
            s[i + 1] = s[i] + nums[i + 1];
        }
        int ans = 0;
        if (s[n - 1] % 2 == 0) {
            ans = right.getOrDefault(s[n - 1] / 2, 0);
        }
        Map<Integer, Integer> left = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int d = k - nums[i];
            if ((s[n - 1] + d) % 2 == 0) {
                int t = left.getOrDefault((s[n - 1] + d) / 2, 0)
                    + right.getOrDefault((s[n - 1] - d) / 2, 0);
                ans = Math.max(ans, t);
            }
            left.merge(s[i], 1, Integer::sum);
            right.merge(s[i], -1, Integer::sum);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int waysToPartition(vector<int>& nums, int k) {
        int n = nums.size();
        long long s[n];
        s[0] = nums[0];
        unordered_map<long long, int> right;
        for (int i = 0; i < n - 1; ++i) {
            right[s[i]]++;
            s[i + 1] = s[i] + nums[i + 1];
        }
        int ans = 0;
        if (s[n - 1] % 2 == 0) {
            ans = right[s[n - 1] / 2];
        }
        unordered_map<long long, int> left;
        for (int i = 0; i < n; ++i) {
            int d = k - nums[i];
            if ((s[n - 1] + d) % 2 == 0) {
                int t = left[(s[n - 1] + d) / 2] + right[(s[n - 1] - d) / 2];
                ans = max(ans, t);
            }
            left[s[i]]++;
            right[s[i]]--;
        }
        return ans;
    }
};
```

### **Go**

```go
func waysToPartition(nums []int, k int) (ans int) {
	n := len(nums)
	s := make([]int, n)
	s[0] = nums[0]
	right := map[int]int{}
	for i := range nums[:n-1] {
		right[s[i]]++
		s[i+1] = s[i] + nums[i+1]
	}
	if s[n-1]%2 == 0 {
		ans = right[s[n-1]/2]
	}
	left := map[int]int{}
	for i, x := range nums {
		d := k - x
		if (s[n-1]+d)%2 == 0 {
			t := left[(s[n-1]+d)/2] + right[(s[n-1]-d)/2]
			if ans < t {
				ans = t
			}
		}
		left[s[i]]++
		right[s[i]]--
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
