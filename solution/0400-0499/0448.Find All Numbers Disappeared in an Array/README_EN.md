# [448. Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array)

[中文文档](/solution/0400-0499/0448.Find%20All%20Numbers%20Disappeared%20in%20an%20Array/README.md)

## Description

<p>Given an array <code>nums</code> of <code>n</code> integers where <code>nums[i]</code> is in the range <code>[1, n]</code>, return <em>an array of all the integers in the range</em> <code>[1, n]</code> <em>that do not appear in</em> <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [4,3,2,7,8,2,3,1]
<strong>Output:</strong> [5,6]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1,1]
<strong>Output:</strong> [2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= n</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you do it without extra space and in <code>O(n)</code> runtime? You may assume the returned list does not count as extra space.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findDisappearedNumbers(self, nums: List[int]) -> List[int]:
        s = set(nums)
        return [x for x in range(1, len(nums) + 1) if x not in s]
```

```python
class Solution:
    def findDisappearedNumbers(self, nums: List[int]) -> List[int]:
        for x in nums:
            i = abs(x) - 1
            if nums[i] > 0:
                nums[i] *= -1
        return [i + 1 for i in range(len(nums)) if nums[i] > 0]
```

### **Java**

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        boolean[] s = new boolean[n + 1];
        for (int x : nums) {
            s[x] = true;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!s[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int x : nums) {
            int i = Math.abs(x) - 1;
            if (nums[i] > 0) {
                nums[i] *= -1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
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
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        int n = nums.size();
        bool s[n + 1];
        memset(s, false, sizeof(s));
        for (int& x : nums) {
            s[x] = true;
        }
        vector<int> ans;
        for (int i = 1; i <= n; ++i) {
            if (!s[i]) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        int n = nums.size();
        for (int& x : nums) {
            int i = abs(x) - 1;
            if (nums[i] > 0) {
                nums[i] = -nums[i];
            }
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                ans.push_back(i + 1);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findDisappearedNumbers(nums []int) (ans []int) {
	n := len(nums)
	s := make([]bool, n+1)
	for _, x := range nums {
		s[x] = true
	}
	for i := 1; i <= n; i++ {
		if !s[i] {
			ans = append(ans, i)
		}
	}
	return
}
```

```go
func findDisappearedNumbers(nums []int) (ans []int) {
	n := len(nums)
	for _, x := range nums {
		i := abs(x) - 1
		if nums[i] > 0 {
			nums[i] = -nums[i]
		}
	}
	for i := 0; i < n; i++ {
		if nums[i] > 0 {
			ans = append(ans, i+1)
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function findDisappearedNumbers(nums: number[]): number[] {
    const n = nums.length;
    const s: boolean[] = new Array(n + 1).fill(false);
    for (const x of nums) {
        s[x] = true;
    }
    const ans: number[] = [];
    for (let i = 1; i <= n; ++i) {
        if (!s[i]) {
            ans.push(i);
        }
    }
    return ans;
}
```

```ts
function findDisappearedNumbers(nums: number[]): number[] {
    const n = nums.length;
    for (const x of nums) {
        const i = Math.abs(x) - 1;
        if (nums[i] > 0) {
            nums[i] *= -1;
        }
    }
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (nums[i] > 0) {
            ans.push(i + 1);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
