# [487. Max Consecutive Ones II](https://leetcode.com/problems/max-consecutive-ones-ii)

[中文文档](/solution/0400-0499/0487.Max%20Consecutive%20Ones%20II/README.md)

## Description

<p>Given a binary array <code>nums</code>, return <em>the maximum number of consecutive </em><code>1</code><em>&#39;s in the array if you can flip at most one</em> <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,1,1,0]
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
- If we flip the first zero, nums becomes [1,1,1,1,0] and we have 4 consecutive ones.
- If we flip the second zero, nums becomes [1,0,1,1,1] and we have 3 consecutive ones.
The max number of consecutive ones is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,1,1,0,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
- If we flip the first zero, nums becomes [1,1,1,1,0,1] and we have 4 consecutive ones.
- If we flip the second zero, nums becomes [1,0,1,1,1,1] and we have 4 consecutive ones.
The max number of consecutive ones is 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> What if the input numbers come in one by one as an infinite stream? In other words, you can&#39;t store all numbers coming from the stream as it&#39;s too large to hold in memory. Could you solve it efficiently?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        ans = nums.count(1)
        n = len(nums)
        left = [0] * n
        right = [0] * n
        for i, v in enumerate(nums):
            if v:
                left[i] = 1 if i == 0 else left[i - 1] + 1
        for i in range(n - 1, -1, -1):
            v = nums[i]
            if v:
                right[i] = 1 if i == n - 1 else right[i + 1] + 1
        ans = 0
        for i, v in enumerate(nums):
            t = 0
            if i:
                t += left[i - 1]
            if i < n - 1:
                t += right[i + 1]
            ans = max(ans, t + 1)
        return ans
```

```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        ans = 1
        cnt = j = 0
        for i, v in enumerate(nums):
            if v == 0:
                cnt += 1
            while cnt > 1:
                if nums[j] == 0:
                    cnt -= 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        l = r = 0
        k = 1
        while r < len(nums):
            if nums[r] == 0:
                k -= 1
            if k < 0:
                if nums[l] == 0:
                    k += 1
                l += 1
            r += 1
        return r - l
```

### **Java**

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                left[i] = i == 0 ? 1 : left[i - 1] + 1;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (nums[i] == 1) {
                right[i] = i == n - 1 ? 1 : right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int t = 0;
            if (i > 0) {
                t += left[i - 1];
            }
            if (i < n - 1) {
                t += right[i + 1];
            }
            ans = Math.max(ans, t + 1);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int j = 0, cnt = 0;
        int ans = 1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                ++cnt;
            }
            while (cnt > 1) {
                if (nums[j++] == 0) {
                    --cnt;
                }
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int l = 0, r = 0;
        int k = 1;
        while (r < nums.length) {
            if (nums[r++] == 0) {
                --k;
            }
            if (k < 0 && nums[l++] == 0) {
                ++k;
            }
        }
        return r - l;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n), right(n);
        for (int i = 0; i < n; ++i) {
            if (nums[i]) {
                left[i] = i == 0 ? 1 : left[i - 1] + 1;
            }
        }
        for (int i = n - 1; ~i; --i) {
            if (nums[i]) {
                right[i] = i == n - 1 ? 1 : right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int t = 0;
            if (i) {
                t += left[i - 1];
            }
            if (i < n - 1) {
                t += right[i + 1];
            }
            ans = max(ans, t + 1);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int ans = 1;
        int cnt = 0, j = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == 0) {
                ++cnt;
            }
            while (cnt > 1) {
                if (nums[j++] == 0) {
                    --cnt;
                }
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int l = 0, r = 0;
        int k = 1;
        while (r < nums.size()) {
            if (nums[r++] == 0) {
                --k;
            }
            if (k < 0 && nums[l++] == 0) {
                ++k;
            }
        }
        return r - l;
    }
};
```

### **Go**

```go
func findMaxConsecutiveOnes(nums []int) int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i, v := range nums {
		if v == 1 {
			if i == 0 {
				left[i] = 1
			} else {
				left[i] = left[i-1] + 1
			}
		}
	}
	for i := n - 1; i >= 0; i-- {
		if nums[i] == 1 {
			if i == n-1 {
				right[i] = 1
			} else {
				right[i] = right[i+1] + 1
			}
		}
	}
	ans := 0
	for i := range nums {
		t := 0
		if i > 0 {
			t += left[i-1]
		}
		if i < n-1 {
			t += right[i+1]
		}
		ans = max(ans, t+1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func findMaxConsecutiveOnes(nums []int) int {
	ans := 1
	j, cnt := 0, 0
	for i, v := range nums {
		if v == 0 {
			cnt++
		}
		for cnt > 1 {
			if nums[j] == 0 {
				cnt--
			}
			j++
		}
		ans = max(ans, i-j+1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func findMaxConsecutiveOnes(nums []int) int {
	l, r := 0, 0
	k := 1
	for ; r < len(nums); r++ {
		if nums[r] == 0 {
			k--
		}
		if k < 0 {
			if nums[l] == 0 {
				k++
			}
			l++
		}
	}
	return r - l
}
```

### **...**

```

```

<!-- tabs:end -->
