# [1040. Moving Stones Until Consecutive II](https://leetcode.com/problems/moving-stones-until-consecutive-ii)

[中文文档](/solution/1000-1099/1040.Moving%20Stones%20Until%20Consecutive%20II/README.md)

## Description

<p>There are some stones in different positions on the X-axis. You are given an integer array <code>stones</code>, the positions of the stones.</p>

<p>Call a stone an <strong>endpoint stone</strong> if it has the smallest or largest position. In one move, you pick up an <strong>endpoint stone</strong> and move it to an unoccupied position so that it is no longer an <strong>endpoint stone</strong>.</p>

<ul>
	<li>In particular, if the stones are at say, <code>stones = [1,2,5]</code>, you cannot move the endpoint stone at position <code>5</code>, since moving it to any position (such as <code>0</code>, or <code>3</code>) will still keep that stone as an endpoint stone.</li>
</ul>

<p>The game ends when you cannot make any more moves (i.e., the stones are in three consecutive positions).</p>

<p>Return <em>an integer array </em><code>answer</code><em> of length </em><code>2</code><em> where</em>:</p>

<ul>
	<li><code>answer[0]</code> <em>is the minimum number of moves you can play, and</em></li>
	<li><code>answer[1]</code> <em>is the maximum number of moves you can play</em>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stones = [7,4,9]
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> We can move 4 -&gt; 8 for one move to finish the game.
Or, we can move 9 -&gt; 5, 4 -&gt; 6 for two moves to finish the game.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stones = [6,5,4,3,10]
<strong>Output:</strong> [2,3]
<strong>Explanation:</strong> We can move 3 -&gt; 8 then 10 -&gt; 7 to finish the game.
Or, we can move 3 -&gt; 7, 4 -&gt; 8, 5 -&gt; 9 to finish the game.
Notice we cannot move 10 -&gt; 2 to finish the game, because that would be an illegal move.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= stones.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= stones[i] &lt;= 10<sup>9</sup></code></li>
	<li>All the values of <code>stones</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numMovesStonesII(self, stones: List[int]) -> List[int]:
        stones.sort()
        mi = n = len(stones)
        mx = max(stones[-1] - stones[1] + 1, stones[-2] - stones[0] + 1) - (n - 1)
        i = 0
        for j, x in enumerate(stones):
            while x - stones[i] + 1 > n:
                i += 1
            if j - i + 1 == n - 1 and x - stones[i] == n - 2:
                mi = min(mi, 2)
            else:
                mi = min(mi, n - (j - i + 1))
        return [mi, mx]
```

### **Java**

```java
class Solution {
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;
        int mi = n;
        int mx = Math.max(stones[n - 1] - stones[1] + 1, stones[n - 2] - stones[0] + 1) - (n - 1);
        for (int i = 0, j = 0; j < n; ++j) {
            while (stones[j] - stones[i] + 1 > n) {
                ++i;
            }
            if (j - i + 1 == n - 1 && stones[j] - stones[i] == n - 2) {
                mi = Math.min(mi, 2);
            } else {
                mi = Math.min(mi, n - (j - i + 1));
            }
        }
        return new int[] {mi, mx};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> numMovesStonesII(vector<int>& stones) {
        sort(stones.begin(), stones.end());
        int n = stones.size();
        int mi = n;
        int mx = max(stones[n - 1] - stones[1] + 1, stones[n - 2] - stones[0] + 1) - (n - 1);
        for (int i = 0, j = 0; j < n; ++j) {
            while (stones[j] - stones[i] + 1 > n) {
                ++i;
            }
            if (j - i + 1 == n - 1 && stones[j] - stones[i] == n - 2) {
                mi = min(mi, 2);
            } else {
                mi = min(mi, n - (j - i + 1));
            }
        }
        return {mi, mx};
    }
};
```

### **Go**

```go
func numMovesStonesII(stones []int) []int {
	sort.Ints(stones)
	n := len(stones)
	mi := n
	mx := max(stones[n-1]-stones[1]+1, stones[n-2]-stones[0]+1) - (n - 1)
	i := 0
	for j, x := range stones {
		for x-stones[i]+1 > n {
			i++
		}
		if j-i+1 == n-1 && stones[j]-stones[i] == n-2 {
			mi = min(mi, 2)
		} else {
			mi = min(mi, n-(j-i+1))
		}
	}
	return []int{mi, mx}
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
function numMovesStonesII(stones: number[]): number[] {
    stones.sort((a, b) => a - b);
    const n = stones.length;
    let mi = n;
    const mx =
        Math.max(stones[n - 1] - stones[1] + 1, stones[n - 2] - stones[0] + 1) -
        (n - 1);
    for (let i = 0, j = 0; j < n; ++j) {
        while (stones[j] - stones[i] + 1 > n) {
            ++i;
        }
        if (j - i + 1 === n - 1 && stones[j] - stones[i] === n - 2) {
            mi = Math.min(mi, 2);
        } else {
            mi = Math.min(mi, n - (j - i + 1));
        }
    }
    return [mi, mx];
}
```

### **...**

```

```

<!-- tabs:end -->
