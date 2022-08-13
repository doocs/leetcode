# [2358. Maximum Number of Groups Entering a Competition](https://leetcode.com/problems/maximum-number-of-groups-entering-a-competition)

[中文文档](/solution/2300-2399/2358.Maximum%20Number%20of%20Groups%20Entering%20a%20Competition/README.md)

## Description

<p>You are given a positive integer array <code>grades</code> which represents the grades of students in a university. You would like to enter <strong>all</strong> these students into a competition in <strong>ordered</strong> non-empty groups, such that the ordering meets the following conditions:</p>

<ul>
	<li>The sum of the grades of students in the <code>i<sup>th</sup></code> group is <strong>less than</strong> the sum of the grades of students in the <code>(i + 1)<sup>th</sup></code> group, for all groups (except the last).</li>
	<li>The total number of students in the <code>i<sup>th</sup></code> group is <strong>less than</strong> the total number of students in the <code>(i + 1)<sup>th</sup></code> group, for all groups (except the last).</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of groups that can be formed</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> grades = [10,6,12,7,3,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The following is a possible way to form 3 groups of students:
- 1<sup>st</sup> group has the students with grades = [12]. Sum of grades: 12. Student count: 1
- 2<sup>nd</sup> group has the students with grades = [6,7]. Sum of grades: 6 + 7 = 13. Student count: 2
- 3<sup>rd</sup> group has the students with grades = [10,3,5]. Sum of grades: 10 + 3 + 5 = 18. Student count: 3
It can be shown that it is not possible to form more than 3 groups.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grades = [8,8]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can only form 1 group, since forming 2 groups would lead to an equal number of students in both groups.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= grades.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grades[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumGroups(self, grades: List[int]) -> int:
        grades.sort()
        ans = 1
        prev = [1, grades[0]]
        curr = [0, 0]
        for v in grades[1:]:
            curr[0] += 1
            curr[1] += v
            if prev[0] < curr[0] and prev[1] < curr[1]:
                prev = curr
                curr = [0, 0]
                ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int maximumGroups(int[] grades) {
        Arrays.sort(grades);
        int ans = 1;
        int[] prev = new int[]{1, grades[0]};
        int[] curr = new int[]{0, 0};
        for (int i = 1; i < grades.length; ++i) {
            curr[0]++;
            curr[1] += grades[i];
            if (prev[0] < curr[0] && prev[1] < curr[1]) {
                prev = curr;
                curr = new int[]{0, 0};
                ++ans;
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
    int maximumGroups(vector<int>& grades) {
        sort(grades.begin(), grades.end());
        int ans = 1;
        vector<int> prev = {1, grades[0]};
        vector<int> curr = {0, 0};
        for (int i = 1; i < grades.size(); ++i) {
            curr[0]++;
            curr[1] += grades[i];
            if (prev[0] < curr[0] && prev[1] < curr[1]) {
                prev = curr;
                curr = {0, 0};
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumGroups(grades []int) int {
	sort.Ints(grades)
	ans := 1
	prev := []int{1, grades[0]}
	curr := []int{0, 0}
	for _, v := range grades[1:] {
		curr[0]++
		curr[1] += v
		if prev[0] < curr[0] && prev[1] < curr[1] {
			prev = curr
			curr = []int{0, 0}
			ans++
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
