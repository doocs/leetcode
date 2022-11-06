# [2459. Sort Array by Moving Items to Empty Space](https://leetcode.com/problems/sort-array-by-moving-items-to-empty-space)

[中文文档](/solution/2400-2499/2459.Sort%20Array%20by%20Moving%20Items%20to%20Empty%20Space/README.md)

## Description

<p>You are given an integer array <code>nums</code> of size <code>n</code> containing <strong>each</strong> element from <code>0</code> to <code>n - 1</code> (<strong>inclusive</strong>). Each of the elements from <code>1</code> to <code>n - 1</code> represents an item, and the element <code>0</code> represents an empty space.</p>

<p>In one operation, you can move <strong>any</strong> item to the empty space. <code>nums</code> is considered to be sorted if the numbers of all the items are in <strong>ascending</strong> order and the empty space is either at the beginning or at the end of the array.</p>

<p>For example, if <code>n = 4</code>, <code>nums</code> is sorted if:</p>

<ul>
	<li><code>nums = [0,1,2,3]</code> or</li>
	<li><code>nums = [1,2,3,0]</code></li>
</ul>

<p>...and considered to be unsorted otherwise.</p>

<p>Return <em>the <strong>minimum</strong> number of operations needed to sort </em><code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,0,3,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
- Move item 2 to the empty space. Now, nums = [4,0,2,3,1].
- Move item 1 to the empty space. Now, nums = [4,1,2,3,0].
- Move item 4 to the empty space. Now, nums = [0,1,2,3,4].
It can be proven that 3 is the minimum number of operations needed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,0]
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums is already sorted so return 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,2,4,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
- Move item 2 to the empty space. Now, nums = [1,2,0,4,3].
- Move item 3 to the empty space. Now, nums = [1,2,3,4,0].
It can be proven that 2 is the minimum number of operations needed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; n</code></li>
	<li>All the values of <code>nums</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sortArray(self, nums: List[int]) -> int:
        def f(nums, k):
            vis = [False] * n
            cnt = 0
            for i, v in enumerate(nums):
                if i == v or vis[i]:
                    continue
                cnt += 1
                j = i
                while not vis[j]:
                    vis[j] = True
                    cnt += 1
                    j = nums[j]
            return cnt - 2 * (nums[k] != k)

        n = len(nums)
        a = f(nums, 0)
        b = f([(v - 1 + n) % n for v in nums], n - 1)
        return min(a, b)
```

### **Java**

```java
class Solution {
    public int sortArray(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = (nums[i] - 1 + n) % n;
        }
        int a = f(nums, 0);
        int b = f(arr, n - 1);
        return Math.min(a, b);
    }

    private int f(int[] nums, int k) {
        boolean[] vis = new boolean[nums.length];
        int cnt = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i == nums[i] || vis[i]) {
                continue;
            }
            ++cnt;
            int j = nums[i];
            while (!vis[j]) {
                vis[j] = true;
                ++cnt;
                j = nums[j];
            }
        }
        if (nums[k] != k) {
            cnt -= 2;
        }
        return cnt;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int sortArray(vector<int>& nums) {
        int n = nums.size();
        auto f = [&](vector<int>& nums, int k) {
            vector<bool> vis(n);
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (i == nums[i] || vis[i]) continue;
                int j = i;
                ++cnt;
                while (!vis[j]) {
                    vis[j] = true;
                    ++cnt;
                    j = nums[j];
                }
            }
            if (nums[k] != k) cnt -= 2;
            return cnt;
        };

        int a = f(nums, 0);
        vector<int> arr = nums;
        for (int& v : arr) v = (v - 1 + n) % n;
        int b = f(arr, n - 1);
        return min(a, b);
    }
};
```

### **Go**

```go
func sortArray(nums []int) int {
	n := len(nums)
	f := func(nums []int, k int) int {
		vis := make([]bool, n)
		cnt := 0
		for i, v := range nums {
			if i == v || vis[i] {
				continue
			}
			cnt++
			j := i
			for !vis[j] {
				vis[j] = true
				cnt++
				j = nums[j]
			}
		}
		if nums[k] != k {
			cnt -= 2
		}
		return cnt
	}
	a := f(nums, 0)
	arr := make([]int, n)
	for i, v := range nums {
		arr[i] = (v - 1 + n) % n
	}
	b := f(arr, n-1)
	return min(a, b)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
