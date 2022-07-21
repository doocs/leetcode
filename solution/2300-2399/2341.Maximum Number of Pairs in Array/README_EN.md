# [2341. Maximum Number of Pairs in Array](https://leetcode.com/problems/maximum-number-of-pairs-in-array)

[中文文档](/solution/2300-2399/2341.Maximum%20Number%20of%20Pairs%20in%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. In one operation, you may do the following:</p>

<ul>
	<li>Choose <strong>two</strong> integers in <code>nums</code> that are <strong>equal</strong>.</li>
	<li>Remove both integers from <code>nums</code>, forming a <strong>pair</strong>.</li>
</ul>

<p>The operation is done on <code>nums</code> as many times as possible.</p>

<p>Return <em>a <strong>0-indexed</strong> integer array </em><code>answer</code><em> of size </em><code>2</code><em> where </em><code>answer[0]</code><em> is the number of pairs that are formed and </em><code>answer[1]</code><em> is the number of leftover integers in </em><code>nums</code><em> after doing the operation as many times as possible</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,2,1,3,2,2]
<strong>Output:</strong> [3,1]
<strong>Explanation:</strong>
Form a pair with nums[0] and nums[3] and remove them from nums. Now, nums = [3,2,3,2,2].
Form a pair with nums[0] and nums[2] and remove them from nums. Now, nums = [2,2,2].
Form a pair with nums[0] and nums[1] and remove them from nums. Now, nums = [2].
No more pairs can be formed. A total of 3 pairs have been formed, and there is 1 number leftover in nums.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1]
<strong>Output:</strong> [1,0]
<strong>Explanation:</strong> Form a pair with nums[0] and nums[1] and remove them from nums. Now, nums = [].
No more pairs can be formed. A total of 1 pair has been formed, and there are 0 numbers leftover in nums.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0]
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> No pairs can be formed, and there is 1 number leftover in nums.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfPairs(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        s = sum(v // 2 for v in cnt.values())
        return [s, len(nums) - s * 2]
```

### **Java**

```java
class Solution {
    public int[] numberOfPairs(int[] nums) {
        int[] cnt = new int[101];
        for (int v : nums) {
            ++cnt[v];
        }
        int s = 0;
        for (int v : cnt) {
            s += v / 2;
        }
        return new int[]{s, nums.length - s * 2};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> numberOfPairs(vector<int>& nums) {
        vector<int> cnt(101);
        for (int v : nums) ++cnt[v];
        int s = 0;
        for (int v : cnt) s += v / 2;
        vector<int> ans(2);
        ans[0] = s;
        ans[1] = nums.size() - s * 2;
        return ans;
    }
};
```

### **Go**

```go
func numberOfPairs(nums []int) []int {
    cnt := make([]int, 101)
    for _, v := range nums {
        cnt[v]++
    }
    s := 0
    for _, v := range cnt {
        s += v / 2
    }
    return []int{s, len(nums) - s * 2}
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
