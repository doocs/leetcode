# [1703. Minimum Adjacent Swaps for K Consecutive Ones](https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones)

[中文文档](/solution/1700-1799/1703.Minimum%20Adjacent%20Swaps%20for%20K%20Consecutive%20Ones/README.md)

## Description

<p>You are given an integer array, <code>nums</code>, and an integer <code>k</code>. <code>nums</code> comprises of only <code>0</code>&#39;s and <code>1</code>&#39;s. In one move, you can choose two <strong>adjacent</strong> indices and swap their values.</p>

<p>Return <em>the <strong>minimum</strong> number of moves required so that </em><code>nums</code><em> has </em><code>k</code><em> <strong>consecutive</strong> </em><code>1</code><em>&#39;s</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,0,1,0,1], k = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> In 1 move, nums could be [1,0,0,0,<u>1</u>,<u>1</u>] and have 2 consecutive 1&#39;s.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,0,0,0,0,1,1], k = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> In 5 moves, the leftmost 1 can be shifted right until nums = [0,0,0,0,0,<u>1</u>,<u>1</u>,<u>1</u>].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,0,1], k = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums already has 2 consecutive 1&#39;s.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is <code>0</code> or <code>1</code>.</li>
	<li><code>1 &lt;= k &lt;= sum(nums)</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minMoves(self, nums: List[int], k: int) -> int:
        arr = [i for i, x in enumerate(nums) if x]
        s = list(accumulate(arr, initial=0))
        ans = inf
        x = (k + 1) // 2
        y = k - x
        for i in range(x - 1, len(arr) - y):
            j = arr[i]
            ls = s[i + 1] - s[i + 1 - x]
            rs = s[i + 1 + y] - s[i + 1]
            a = (j + j - x + 1) * x // 2 - ls
            b = rs - (j + 1 + j + y) * y // 2
            ans = min(ans, a + b)
        return ans
```

### **Java**

```java
class Solution {
    public int minMoves(int[] nums, int k) {
        List<Integer> arr = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] != 0) {
                arr.add(i);
            }
        }
        int m = arr.size();
        int[] s = new int[m + 1];
        for (int i = 0; i < m; ++i) {
            s[i + 1] = s[i] + arr.get(i);
        }
        long ans = 1 << 60;
        int x = (k + 1) / 2;
        int y = k - x;
        for (int i = x - 1; i < m - y; ++i) {
            int j = arr.get(i);
            int ls = s[i + 1] - s[i + 1 - x];
            int rs = s[i + 1 + y] - s[i + 1];
            long a = (j + j - x + 1L) * x / 2 - ls;
            long b = rs - (j + 1L + j + y) * y / 2;
            ans = Math.min(ans, a + b);
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMoves(vector<int>& nums, int k) {
        vector<int> arr;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i]) {
                arr.push_back(i);
            }
        }
        int m = arr.size();
        long s[m + 1];
        s[0] = 1;
        for (int i = 0; i < m; ++i) {
            s[i + 1] = s[i] + arr[i];
        }
        long ans = 1L << 60;
        int x = (k + 1) / 2;
        int y = k - x;
        for (int i = x - 1; i < m - y; ++i) {
            int j = arr[i];
            int ls = s[i + 1] - s[i + 1 - x];
            int rs = s[i + 1 + y] - s[i + 1];
            long a = (j + j - x + 1L) * x / 2 - ls;
            long b = rs - (j + 1L + j + y) * y / 2;
            ans = min(ans, a + b);
        }
        return ans;
    }
};
```

### **Go**

```go
func minMoves(nums []int, k int) int {
	arr := []int{}
	for i, x := range nums {
		if x != 0 {
			arr = append(arr, i)
		}
	}
	s := make([]int, len(arr)+1)
	for i, x := range arr {
		s[i+1] = s[i] + x
	}
	ans := 1 << 60
	x := (k + 1) / 2
	y := k - x
	for i := x - 1; i < len(arr)-y; i++ {
		j := arr[i]
		ls := s[i+1] - s[i+1-x]
		rs := s[i+1+y] - s[i+1]
		a := (j+j-x+1)*x/2 - ls
		b := rs - (j+1+j+y)*y/2
		ans = min(ans, a+b)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
