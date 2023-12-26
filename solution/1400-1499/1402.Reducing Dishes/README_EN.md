# [1402. Reducing Dishes](https://leetcode.com/problems/reducing-dishes)

[中文文档](/solution/1400-1499/1402.Reducing%20Dishes/README.md)

## Description

<p>A chef has collected data on the <code>satisfaction</code> level of his <code>n</code> dishes. Chef can cook any dish in 1 unit of time.</p>

<p><strong>Like-time coefficient</strong> of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level i.e. <code>time[i] * satisfaction[i]</code>.</p>

<p>Return the maximum sum of <strong>like-time coefficient </strong>that the chef can obtain after preparing some amount of dishes.</p>

<p>Dishes can be prepared in <strong>any </strong>order and the chef can discard some dishes to get this maximum value.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> satisfaction = [-1,-8,0,5,-9]
<strong>Output:</strong> 14
<strong>Explanation:</strong> After Removing the second and last dish, the maximum total <strong>like-time coefficient</strong> will be equal to (-1*1 + 0*2 + 5*3 = 14).
Each dish is prepared in one unit of time.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> satisfaction = [4,3,2]
<strong>Output:</strong> 20
<strong>Explanation:</strong> Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)
</pre>

<p><strong class="example">Example 3:</strong></p>

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

**Solution 1: Greedy + Sorting**

Suppose we only choose one dish, then we should choose the dish with the highest satisfaction $s_0$, and check whether $s_0$ is greater than 0. If $s_0 \leq 0$, then we don't cook any dishes, otherwise, we cook this dish, and the total satisfaction is $s_0$.

If we choose two dishes, then we should choose the two dishes with the highest satisfaction $s_0$ and $s_1$, and the satisfaction is $s_1 + 2 \times s_0$. At this time, we need to ensure that the satisfaction after the selection is greater than the satisfaction before the selection, that is, $s_1 + 2 \times s_0 > s_0$, which means as long as $s_1 + s_0 > 0$, we can choose these two dishes.

By analogy, we can find a rule, that is, we should choose the $k$ dishes with the highest satisfaction, and ensure that the sum of the satisfaction of the first $k$ dishes is greater than $0$.

In implementation, we can first sort the satisfaction of all dishes, and then start choosing from the dish with the highest satisfaction. Each time we add the satisfaction of the current dish, if the result of the addition is less than or equal to $0$, then we no longer choose the dishes behind, otherwise, we choose this dish.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSatisfaction(self, satisfaction: List[int]) -> int:
        satisfaction.sort(reverse=True)
        ans = s = 0
        for x in satisfaction:
            s += x
            if s <= 0:
                break
            ans += s
        return ans

```

### **Java**

```java
class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int ans = 0, s = 0;
        for (int i = satisfaction.length - 1; i >= 0; --i) {
            s += satisfaction[i];
            if (s <= 0) {
                break;
            }
            ans += s;
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
        int ans = 0, s = 0;
        for (int x : satisfaction) {
            s += x;
            if (s <= 0) {
                break;
            }
            ans += s;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSatisfaction(satisfaction []int) (ans int) {
	sort.Slice(satisfaction, func(i, j int) bool { return satisfaction[i] > satisfaction[j] })
	s := 0
	for _, x := range satisfaction {
		s += x
		if s <= 0 {
			break
		}
		ans += s
	}
	return
}
```

### **TypeScript**

```ts
function maxSatisfaction(satisfaction: number[]): number {
    satisfaction.sort((a, b) => b - a);
    let [ans, s] = [0, 0];
    for (const x of satisfaction) {
        s += x;
        if (s <= 0) {
            break;
        }
        ans += s;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
