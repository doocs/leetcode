# [2190. Most Frequent Number Following Key In an Array](https://leetcode.com/problems/most-frequent-number-following-key-in-an-array)

[中文文档](/solution/2100-2199/2190.Most%20Frequent%20Number%20Following%20Key%20In%20an%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>.<strong> </strong>You are also given an integer <code>key</code>, which is present in <code>nums</code>.</p>

<p>For every unique integer <code>target</code> in <code>nums</code>, <strong>count</strong> the number of times <code>target</code> immediately follows an occurrence of <code>key</code> in <code>nums</code>. In other words, count the number of indices <code>i</code> such that:</p>

<ul>
	<li><code>0 &lt;= i &lt;= nums.length - 2</code>,</li>
	<li><code>nums[i] == key</code> and,</li>
	<li><code>nums[i + 1] == target</code>.</li>
</ul>

<p>Return <em>the </em><code>target</code><em> with the <strong>maximum</strong> count</em>. The test cases will be generated such that the <code>target</code> with maximum count is unique.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,100,200,1,100], key = 1
<strong>Output:</strong> 100
<strong>Explanation:</strong> For target = 100, there are 2 occurrences at indices 1 and 4 which follow an occurrence of key.
No other integers follow an occurrence of key, so we return 100.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2,2,3], key = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> For target = 2, there are 3 occurrences at indices 1, 2, and 3 which follow an occurrence of key.
For target = 3, there is only one occurrence at index 4 which follows an occurrence of key.
target = 2 has the maximum number of occurrences following an occurrence of key, so we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li>The test cases will be generated such that the answer is unique.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mostFrequent(self, nums: List[int], key: int) -> int:
        cnt = Counter()
        mx = ans = 0
        for i, v in enumerate(nums[:-1]):
            if v == key:
                target = nums[i + 1]
                cnt[target] += 1
                if mx < cnt[target]:
                    mx = cnt[target]
                    ans = nums[i + 1]
        return ans
```

### **Java**

```java
class Solution {
    public int mostFrequent(int[] nums, int key) {
        int[] cnt = new int[1010];
        int mx = 0, ans = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == key) {
                int target = nums[i + 1];
                ++cnt[target];
                if (mx < cnt[target]) {
                    mx = cnt[target];
                    ans = nums[i + 1];
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int mostFrequent(vector<int>& nums, int key) {
        vector<int> cnt(1010);
        int mx = 0, ans = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            if (nums[i] == key) {
                int target = nums[i + 1];
                ++cnt[target];
                if (mx < cnt[target]) {
                    mx = cnt[target];
                    ans = nums[i + 1];
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func mostFrequent(nums []int, key int) int {
	cnt := make([]int, 1010)
	mx, ans := 0, 0
	for i, v := range nums[:len(nums)-1] {
		if v == key {
			target := nums[i+1]
			cnt[target]++
			if mx < cnt[target] {
				mx = cnt[target]
				ans = nums[i+1]
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
