# [2106. Maximum Fruits Harvested After at Most K Steps](https://leetcode.com/problems/maximum-fruits-harvested-after-at-most-k-steps)

[中文文档](/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/README.md)

## Description

<p>Fruits are available at some positions on an infinite x-axis. You are given a 2D integer array <code>fruits</code> where <code>fruits[i] = [position<sub>i</sub>, amount<sub>i</sub>]</code> depicts <code>amount<sub>i</sub></code> fruits at the position <code>position<sub>i</sub></code>. <code>fruits</code> is already <strong>sorted</strong> by <code>position<sub>i</sub></code> in <strong>ascending order</strong>, and each <code>position<sub>i</sub></code> is <strong>unique</strong>.</p>

<p>You are also given an integer <code>startPos</code> and an integer <code>k</code>. Initially, you are at the position <code>startPos</code>. From any position, you can either walk to the <strong>left or right</strong>. It takes <strong>one step</strong> to move <strong>one unit</strong> on the x-axis, and you can walk <strong>at most</strong> <code>k</code> steps in total. For every position you reach, you harvest all the fruits at that position, and the fruits will disappear from that position.</p>

<p>Return <em>the <strong>maximum total number</strong> of fruits you can harvest</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/images/1.png" style="width: 472px; height: 115px;" />
<pre>
<strong>Input:</strong> fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
<strong>Output:</strong> 9
<strong>Explanation:</strong> 
The optimal way is to:
- Move right to position 6 and harvest 3 fruits
- Move right to position 8 and harvest 6 fruits
You moved 3 steps and harvested 3 + 6 = 9 fruits in total.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/images/2.png" style="width: 512px; height: 129px;" />
<pre>
<strong>Input:</strong> fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
<strong>Output:</strong> 14
<strong>Explanation:</strong> 
You can move at most k = 4 steps, so you cannot reach position 0 nor 10.
The optimal way is to:
- Harvest the 7 fruits at the starting position 5
- Move left to position 4 and harvest 1 fruit
- Move right to position 6 and harvest 2 fruits
- Move right to position 7 and harvest 4 fruits
You moved 1 + 3 = 4 steps and harvested 7 + 1 + 2 + 4 = 14 fruits in total.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/images/3.png" style="width: 476px; height: 100px;" />
<pre>
<strong>Input:</strong> fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong>
You can move at most k = 2 steps and cannot reach any position with fruits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= fruits.length &lt;= 10<sup>5</sup></code></li>
	<li><code>fruits[i].length == 2</code></li>
	<li><code>0 &lt;= startPos, position<sub>i</sub> &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>position<sub>i-1</sub> &lt; position<sub>i</sub></code> for any <code>i &gt; 0</code>&nbsp;(<strong>0-indexed</strong>)</li>
	<li><code>1 &lt;= amount<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt;= 2 * 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxTotalFruits(self, fruits: List[List[int]], startPos: int, k: int) -> int:
        q = deque()
        i, n = 0, len(fruits)
        ans = 0
        while i < n and fruits[i][0] <= startPos:
            if startPos - fruits[i][0] <= k:
                ans += fruits[i][1]
                q.append(fruits[i])
            i += 1

        t = ans
        while i < n and fruits[i][0] - startPos <= k:
            while (
                q
                and q[0][0] < startPos
                and fruits[i][0]
                - q[0][0]
                + min(startPos - q[0][0], fruits[i][0] - startPos)
                > k
            ):
                t -= q[0][1]
                q.popleft()
            t += fruits[i][1]
            ans = max(ans, t)
            i += 1
        return ans
```

### **Java**

```java
class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        Deque<int[]> q = new ArrayDeque<>();
        int i = 0, n = fruits.length;
        int ans = 0;
        while (i < n && fruits[i][0] <= startPos) {
            if (startPos - fruits[i][0] <= k) {
                ans += fruits[i][1];
                q.offerLast(fruits[i]);
            }
            ++i;
        }
        int t = ans;
        while (i < n && fruits[i][0] - startPos <= k) {
            while (!q.isEmpty() && q.peekFirst()[0] < startPos && fruits[i][0] - q.peekFirst()[0] + Math.min(startPos - q.peekFirst()[0], fruits[i][0] - startPos) > k) {
                t -= q.pollFirst()[1];
            }
            t += fruits[i][1];
            ans = Math.max(ans, t);
            ++i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxTotalFruits(vector<vector<int>>& fruits, int startPos, int k) {
        queue<vector<int>> q;
        int i = 0, n = fruits.size();
        int ans = 0;
        while (i < n && fruits[i][0] <= startPos) {
            if (startPos - fruits[i][0] <= k) {
                ans += fruits[i][1];
                q.push(fruits[i]);
            }
            ++i;
        }
        int t = ans;
        while (i < n && fruits[i][0] - startPos <= k) {
            while (!q.empty() && q.front()[0] < startPos && fruits[i][0] - q.front()[0] + min(startPos - q.front()[0], fruits[i][0] - startPos) > k) {
                t -= q.front()[1];
                q.pop();
            }
            t += fruits[i][1];
            ans = max(ans, t);
            ++i;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxTotalFruits(fruits [][]int, startPos int, k int) int {
	var q [][]int
	i, n := 0, len(fruits)
	ans := 0
	for i < n && fruits[i][0] <= startPos {
		if startPos-fruits[i][0] <= k {
			ans += fruits[i][1]
			q = append(q, fruits[i])
		}
		i++
	}
	t := ans
	for i < n && fruits[i][0]-startPos <= k {
		for len(q) > 0 && q[0][0] < startPos && fruits[i][0]-q[0][0]+min(startPos-q[0][0], fruits[i][0]-startPos) > k {
			t -= q[0][1]
			q = q[1:]
		}
		t += fruits[i][1]
		ans = max(ans, t)
		i++
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
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
