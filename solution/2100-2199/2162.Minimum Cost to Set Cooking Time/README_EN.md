# [2162. Minimum Cost to Set Cooking Time](https://leetcode.com/problems/minimum-cost-to-set-cooking-time)

[中文文档](/solution/2100-2199/2162.Minimum%20Cost%20to%20Set%20Cooking%20Time/README.md)

## Description

<p>A generic microwave supports cooking times for:</p>

<ul>
	<li>at least <code>1</code> second.</li>
	<li>at most <code>99</code> minutes and <code>99</code> seconds.</li>
</ul>

<p>To set the cooking time, you push <strong>at most four digits</strong>. The microwave normalizes what you push as four digits by <strong>prepending zeroes</strong>. It interprets the <strong>first</strong> two digits as the minutes and the <strong>last</strong> two digits as the seconds. It then <strong>adds</strong> them up as the cooking time. For example,</p>

<ul>
	<li>You push <code>9</code> <code>5</code> <code>4</code> (three digits). It is normalized as <code>0954</code> and interpreted as <code>9</code> minutes and <code>54</code> seconds.</li>
	<li>You push <code>0</code> <code>0</code> <code>0</code> <code>8</code> (four digits). It is interpreted as <code>0</code> minutes and <code>8</code> seconds.</li>
	<li>You push <code>8</code> <code>0</code> <code>9</code> <code>0</code>. It is interpreted as <code>80</code> minutes and <code>90</code> seconds.</li>
	<li>You push <code>8</code> <code>1</code> <code>3</code> <code>0</code>. It is interpreted as <code>81</code> minutes and <code>30</code> seconds.</li>
</ul>

<p>You are given integers <code>startAt</code>, <code>moveCost</code>, <code>pushCost</code>, and <code>targetSeconds</code>. <strong>Initially</strong>, your finger is on the digit <code>startAt</code>. Moving the finger above <strong>any specific digit</strong> costs <code>moveCost</code> units of fatigue. Pushing the digit below the finger <strong>once</strong> costs <code>pushCost</code> units of fatigue.</p>

<p>There can be multiple ways to set the microwave to cook for <code>targetSeconds</code> seconds but you are interested in the way with the minimum cost.</p>

<p>Return <em>the <strong>minimum cost</strong> to set</em> <code>targetSeconds</code> <em>seconds of cooking time</em>.</p>

<p>Remember that one minute consists of <code>60</code> seconds.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2162.Minimum%20Cost%20to%20Set%20Cooking%20Time/images/1.png" style="width: 506px; height: 210px;" />
<pre>
<strong>Input:</strong> startAt = 1, moveCost = 2, pushCost = 1, targetSeconds = 600
<strong>Output:</strong> 6
<strong>Explanation:</strong> The following are the possible ways to set the cooking time.
- 1 0 0 0, interpreted as 10 minutes and 0 seconds.
&nbsp; The finger is already on digit 1, pushes 1 (with cost 1), moves to 0 (with cost 2), pushes 0 (with cost 1), pushes 0 (with cost 1), and pushes 0 (with cost 1).
&nbsp; The cost is: 1 + 2 + 1 + 1 + 1 = 6. This is the minimum cost.
- 0 9 6 0, interpreted as 9 minutes and 60 seconds. That is also 600 seconds.
&nbsp; The finger moves to 0 (with cost 2), pushes 0 (with cost 1), moves to 9 (with cost 2), pushes 9 (with cost 1), moves to 6 (with cost 2), pushes 6 (with cost 1), moves to 0 (with cost 2), and pushes 0 (with cost 1).
&nbsp; The cost is: 2 + 1 + 2 + 1 + 2 + 1 + 2 + 1 = 12.
- 9 6 0, normalized as 0960 and interpreted as 9 minutes and 60 seconds.
&nbsp; The finger moves to 9 (with cost 2), pushes 9 (with cost 1), moves to 6 (with cost 2), pushes 6 (with cost 1), moves to 0 (with cost 2), and pushes 0 (with cost 1).
&nbsp; The cost is: 2 + 1 + 2 + 1 + 2 + 1 = 9.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2162.Minimum%20Cost%20to%20Set%20Cooking%20Time/images/2.png" style="width: 505px; height: 73px;" />
<pre>
<strong>Input:</strong> startAt = 0, moveCost = 1, pushCost = 2, targetSeconds = 76
<strong>Output:</strong> 6
<strong>Explanation:</strong> The optimal way is to push two digits: 7 6, interpreted as 76 seconds.
The finger moves to 7 (with cost 1), pushes 7 (with cost 2), moves to 6 (with cost 1), and pushes 6 (with cost 2). The total cost is: 1 + 2 + 1 + 2 = 6
Note other possible ways are 0076, 076, 0116, and 116, but none of them produces the minimum cost.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= startAt &lt;= 9</code></li>
	<li><code>1 &lt;= moveCost, pushCost &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= targetSeconds &lt;= 6039</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCostSetTime(
        self, startAt: int, moveCost: int, pushCost: int, targetSeconds: int
    ) -> int:
        def f(m, s):
            if not 0 <= m < 100 or not 0 <= s < 100:
                return inf
            arr = [m // 10, m % 10, s // 10, s % 10]
            i = 0
            while i < 4 and arr[i] == 0:
                i += 1
            t = 0
            prev = startAt
            for v in arr[i:]:
                if v != prev:
                    t += moveCost
                t += pushCost
                prev = v
            return t

        m, s = divmod(targetSeconds, 60)
        ans = min(f(m, s), f(m - 1, s + 60))
        return ans
```

### **Java**

```java
class Solution {
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int m = targetSeconds / 60;
        int s = targetSeconds % 60;
        return Math.min(
            f(m, s, startAt, moveCost, pushCost), f(m - 1, s + 60, startAt, moveCost, pushCost));
    }

    private int f(int m, int s, int prev, int moveCost, int pushCost) {
        if (m < 0 || m > 99 || s < 0 || s > 99) {
            return Integer.MAX_VALUE;
        }
        int[] arr = new int[] {m / 10, m % 10, s / 10, s % 10};
        int i = 0;
        for (; i < 4 && arr[i] == 0; ++i)
            ;
        int t = 0;
        for (; i < 4; ++i) {
            if (arr[i] != prev) {
                t += moveCost;
            }
            t += pushCost;
            prev = arr[i];
        }
        return t;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int m = targetSeconds / 60, s = targetSeconds % 60;
        return min(f(m, s, startAt, moveCost, pushCost), f(m - 1, s + 60, startAt, moveCost, pushCost));
    }

    int f(int m, int s, int prev, int moveCost, int pushCost) {
        if (m < 0 || m > 99 || s < 0 || s > 99) return INT_MAX;
        vector<int> arr = {m / 10, m % 10, s / 10, s % 10};
        int i = 0;
        for (; i < 4 && arr[i] == 0; ++i)
            ;
        int t = 0;
        for (; i < 4; ++i) {
            if (arr[i] != prev) t += moveCost;
            t += pushCost;
            prev = arr[i];
        }
        return t;
    }
};
```

### **Go**

```go
func minCostSetTime(startAt int, moveCost int, pushCost int, targetSeconds int) int {
	m, s := targetSeconds/60, targetSeconds%60
	f := func(m, s int) int {
		if m < 0 || m > 99 || s < 0 || s > 99 {
			return 0x3f3f3f3f
		}
		arr := []int{m / 10, m % 10, s / 10, s % 10}
		i := 0
		for ; i < 4 && arr[i] == 0; i++ {
		}
		t := 0
		prev := startAt
		for ; i < 4; i++ {
			if arr[i] != prev {
				t += moveCost
			}
			t += pushCost
			prev = arr[i]
		}
		return t
	}
	return min(f(m, s), f(m-1, s+60))
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
