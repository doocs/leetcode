# [1838. Frequency of the Most Frequent Element](https://leetcode.com/problems/frequency-of-the-most-frequent-element)

[中文文档](/solution/1800-1899/1838.Frequency%20of%20the%20Most%20Frequent%20Element/README.md)

## Description

<p>The <strong>frequency</strong> of an element is the number of times it occurs in an array.</p>

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. In one operation, you can choose an index of <code>nums</code> and increment the element at that index by <code>1</code>.</p>

<p>Return <em>the <strong>maximum possible frequency</strong> of an element after performing <strong>at most</strong> </em><code>k</code><em> operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4], k = 5
<strong>Output:</strong> 3<strong>
Explanation:</strong> Increment the first element three times and the second element two times to make nums = [4,4,4].
4 has a frequency of 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,8,13], k = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are multiple optimal solutions:
- Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
- Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
- Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,9,6], k = 2
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        nums.sort()
        l, r, n = 0, 1, len(nums)
        ans, window = 1, 0
        while r < n:
            window += (nums[r] - nums[r - 1]) * (r - l)
            while window > k:
                window -= nums[r] - nums[l]
                l += 1
            r += 1
            ans = max(ans, r - l)
        return ans
```

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        def check(cnt):
            for i in range(n + 1 - cnt):
                j = i + cnt - 1
                if nums[j] * cnt - (s[j + 1] - s[i]) <= k:
                    return True
            return False

        nums.sort()
        s = list(accumulate(nums, initial=0))
        n = len(nums)
        left, right = 1, n
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 1, window = 0;
        for (int l = 0, r = 1; r < n; ++r) {
            window += (nums[r] - nums[r - 1]) * (r - l);
            while (window > k) {
                window -= (nums[r] - nums[l++]);
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

```java
class Solution {
    private long[] s;
    private int[] nums;
    private int n;
    private int k;

    public int maxFrequency(int[] nums, int k) {
        n = nums.length;
        Arrays.sort(nums);
        this.nums = nums;
        this.s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        this.k = k;
        int left = 1, right = n;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int cnt) {
        for (int i = 0; i < n + 1 - cnt; ++i) {
            int j = i + cnt - 1;
            if (1L * nums[j] * cnt - (s[j + 1] - s[i]) <= k) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int ans = 1;
        long long window = 0;
        for (int l = 0, r = 1; r < n; ++r) {
            window += 1LL * (nums[r] - nums[r - 1]) * (r - l);
            while (window > k) {
                window -= (nums[r] - nums[l++]);
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        long long s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int left = 1, right = n;
        auto check = [&](int cnt) {
            for (int i = 0; i < n + 1 - cnt; ++i) {
                int j = i + cnt - 1;
                if (1LL * nums[j] * cnt - (s[j + 1] - s[i]) <= k) {
                    return true;
                }
            }
            return false;
        };
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func maxFrequency(nums []int, k int) int {
	sort.Ints(nums)
	ans, window := 1, 0
	for l, r := 0, 1; r < len(nums); r++ {
		window += (nums[r] - nums[r-1]) * (r - l)
		for window > k {
			window -= nums[r] - nums[l]
			l++
		}
		ans = max(ans, r-l+1)
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
func maxFrequency(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	left, right := 1, n
	check := func(cnt int) bool {
		for i := 0; i < n+1-cnt; i++ {
			j := i + cnt - 1
			if nums[j]*cnt-(s[j+1]-s[i]) <= k {
				return true
			}
		}
		return false
	}
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maxFrequency = function (nums, k) {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let window = 0;
    const n = nums.length;
    for (let l = 0, r = 1; r < n; ++r) {
        window += (nums[r] - nums[r - 1]) * (r - l);
        while (window > k) {
            window -= nums[r] - nums[l++];
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
};
```

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maxFrequency = function (nums, k) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    const check = cnt => {
        for (let i = 0; i < n + 1 - cnt; ++i) {
            const j = i + cnt - 1;
            if (nums[j] * cnt - (s[j + 1] - s[i]) <= k) {
                return true;
            }
        }
        return false;
    };
    let left = 1;
    let right = n;
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
};
```

### **TypeScript**

```ts
function maxFrequency(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let window = 0;
    const n = nums.length;
    for (let l = 0, r = 1; r < n; ++r) {
        window += (nums[r] - nums[r - 1]) * (r - l);
        while (window > k) {
            window -= nums[r] - nums[l++];
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
```

```ts
function maxFrequency(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    const check = (cnt: number) => {
        for (let i = 0; i < n + 1 - cnt; ++i) {
            const j = i + cnt - 1;
            if (nums[j] * cnt - (s[j + 1] - s[i]) <= k) {
                return true;
            }
        }
        return false;
    };
    let left = 1;
    let right = n;
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
