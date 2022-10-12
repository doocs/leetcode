# [1686. Stone Game VI](https://leetcode.com/problems/stone-game-vi)

[中文文档](/solution/1600-1699/1686.Stone%20Game%20VI/README.md)

## Description

<p>Alice and Bob take turns playing a game, with Alice starting first.</p>

<p>There are <code>n</code> stones in a pile. On each player&#39;s turn, they can <strong>remove</strong> a stone from the pile and receive points based on the stone&#39;s value. Alice and Bob may <strong>value the stones differently</strong>.</p>

<p>You are given two integer arrays of length <code>n</code>, <code>aliceValues</code> and <code>bobValues</code>. Each <code>aliceValues[i]</code> and <code>bobValues[i]</code> represents how Alice and Bob, respectively, value the <code>i<sup>th</sup></code> stone.</p>

<p>The winner is the person with the most points after all the stones are chosen. If both players have the same amount of points, the game results in a draw. Both players will play <strong>optimally</strong>.&nbsp;Both players know the other&#39;s values.</p>

<p>Determine the result of the game, and:</p>

<ul>
	<li>If Alice wins, return <code>1</code>.</li>
	<li>If Bob wins, return <code>-1</code>.</li>
	<li>If the game results in a draw, return <code>0</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> aliceValues = [1,3], bobValues = [2,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
If Alice takes stone 1 (0-indexed) first, Alice will receive 3 points.
Bob can only choose stone 0, and will only receive 2 points.
Alice wins.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> aliceValues = [1,2], bobValues = [3,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
If Alice takes stone 0, and Bob takes stone 1, they will both have 1 point.
Draw.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> aliceValues = [2,4,3], bobValues = [1,6,7]
<strong>Output:</strong> -1
<strong>Explanation:</strong>
Regardless of how Alice plays, Bob will be able to have more points than Alice.
For example, if Alice takes stone 1, Bob can take stone 2, and Alice takes stone 0, Alice will have 6 points to Bob&#39;s 7.
Bob wins.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == aliceValues.length == bobValues.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= aliceValues[i], bobValues[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def stoneGameVI(self, aliceValues: List[int], bobValues: List[int]) -> int:
        arr = [(a + b, i)
               for i, (a, b) in enumerate(zip(aliceValues, bobValues))]
        arr.sort(reverse=True)
        a = sum(aliceValues[v[1]] for i, v in enumerate(arr) if i % 2 == 0)
        b = sum(bobValues[v[1]] for i, v in enumerate(arr) if i % 2 == 1)
        if a > b:
            return 1
        if a < b:
            return -1
        return 0
```

### **Java**

```java
class Solution {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {aliceValues[i] + bobValues[i], i};
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        int a = 0, b = 0;
        for (int i = 0; i < n; ++i) {
            int j = arr[i][1];
            if (i % 2 == 0) {
                a += aliceValues[j];
            } else {
                b += bobValues[j];
            }
        }
        if (a == b) {
            return 0;
        }
        return a > b ? 1 : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int stoneGameVI(vector<int>& aliceValues, vector<int>& bobValues) {
        int n = aliceValues.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {aliceValues[i] + bobValues[i], i};
        }
        sort(arr.rbegin(), arr.rend());
        int a = 0, b = 0;
        for (int i = 0; i < n; ++i) {
            int j = arr[i].second;
            if (i % 2 == 0) {
                a += aliceValues[j];
            } else {
                b += bobValues[j];
            }
        }
        if (a == b) return 0;
        return a > b ? 1 : -1;
    }
};
```

### **Go**

```go
func stoneGameVI(aliceValues []int, bobValues []int) int {
	arr := make([][]int, len(aliceValues))
	for i, a := range aliceValues {
		b := bobValues[i]
		arr[i] = []int{a + b, i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] > arr[j][0] })
	a, b := 0, 0
	for i, v := range arr {
		if i%2 == 0 {
			a += aliceValues[v[1]]
		} else {
			b += bobValues[v[1]]
		}
	}
	if a == b {
		return 0
	}
	if a > b {
		return 1
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
