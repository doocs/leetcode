# [1402. Reducing Dishes](https://leetcode.com/problems/reducing-dishes)

[中文文档](/solution/1400-1499/1402.Reducing%20Dishes/README.md)

## Description

<p>A chef has collected data on the <code>satisfaction</code> level of his <code>n</code> dishes. Chef can cook any dish in 1 unit of time.</p>

<p><strong>Like-time coefficient</strong> of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level i.e. <code>time[i] * satisfaction[i]</code>.</p>

<p>Return <em>the maximum sum of <strong>like-time coefficient</strong> that the chef can obtain after dishes preparation</em>.</p>

<p>Dishes can be prepared in <strong>any </strong>order and the chef can discard some dishes to get this maximum value.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> satisfaction = [-1,-8,0,5,-9]
<strong>Output:</strong> 14
<strong>Explanation:</strong> After Removing the second and last dish, the maximum total <strong>like-time coefficient</strong> will be equal to (-1*1 + 0*2 + 5*3 = 14).
Each dish is prepared in one unit of time.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> satisfaction = [4,3,2]
<strong>Output:</strong> 20
<strong>Explanation:</strong> Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> satisfaction = [-1,-4,-5]
<strong>Output:</strong> 0
<strong>Explanation:</strong> People do not like the dishes. No dish is prepared.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == satisfaction.length</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>-1000 &lt;= satisfaction[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSatisfaction(self, satisfaction: List[int]) -> int:
        satisfaction.sort(reverse=True)
        ans = presum = 0
        for v in satisfaction:
            presum += v
            if presum > 0:
                ans += presum
            else:
                break
        return ans
```

### **Java**

```java
class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int ans = 0, presum = 0;
        for (int i = satisfaction.length - 1; i >= 0; --i) {
            presum += satisfaction[i];
            if (presum > 0) {
                ans += presum;
            } else {
                break;
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
    int maxSatisfaction(vector<int>& satisfaction) {
        sort(rbegin(satisfaction), rend(satisfaction));
        int ans = 0, presum = 0;
        for (int v : satisfaction) {
            presum += v;
            if (presum > 0)
                ans += presum;
            else
                break;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSatisfaction(satisfaction []int) int {
	sort.Ints(satisfaction)
	ans, presum := 0, 0
	for i := len(satisfaction) - 1; i >= 0; i-- {
		presum += satisfaction[i]
		if presum > 0 {
			ans += presum
		} else {
			break
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
