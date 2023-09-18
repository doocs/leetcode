# [2860. Happy Students](https://leetcode.com/problems/happy-students)

[中文文档](/solution/2800-2899/2860.Happy%20Students/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code> where <code>n</code> is the total number of students in the class. The class teacher tries to select a group of students so that all the students remain happy.</p>

<p>The <code>i<sup>th</sup></code> student will become happy if one of these two conditions is met:</p>

<ul>
	<li>The student is selected and the total number of selected students is<strong> strictly greater than</strong> <code>nums[i]</code>.</li>
	<li>The student is not selected and the total number of selected students is <strong>strictly</strong> <strong>less than</strong> <code>nums[i]</code>.</li>
</ul>

<p>Return <em>the number of ways to select a group of students so that everyone remains happy.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
The two possible ways are:
The class teacher selects no student.
The class teacher selects both students to form the group. 
If the class teacher selects just one student to form a group then the both students will not be happy. Therefore, there are only two possible ways.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,0,3,3,6,7,2,7]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
The three possible ways are:
The class teacher selects the student with index = 1 to form the group.
The class teacher selects the students with index = 1, 2, 3, 6 to form the group.
The class teacher selects all the students to form the group.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; nums.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countWays(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)
        ans = 0
        for i in range(n + 1):
            if i and nums[i - 1] >= i:
                continue
            if i < n and nums[i] <= i:
                continue
        return ans
```

### **Java**

```java
class Solution {
    public int countWays(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            if ((i == 0 || nums.get(i - 1) < i) && (i == n || nums.get(i) > i)) {
                ans++;
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
    int countWays(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        int n = nums.size();
        for (int i = 0; i <= n; ++i) {
            if ((i && nums[i - 1] >= i) || (i < n && nums[i] <= i)) {
                continue;
            }
            ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func countWays(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	for i := 0; i <= n; i++ {
		if (i > 0 && nums[i-1] >= i) || (i < n && nums[i] <= i) {
			continue
		}
		ans++
	}
	return
}
```

### **TypeScript**

```ts
function countWays(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i <= n; ++i) {
        if ((i && nums[i - 1] >= i) || (i < n && nums[i] <= i)) {
            continue;
        }
        ++ans;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
