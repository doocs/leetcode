# [2350. Shortest Impossible Sequence of Rolls](https://leetcode.com/problems/shortest-impossible-sequence-of-rolls)

[中文文档](/solution/2300-2399/2350.Shortest%20Impossible%20Sequence%20of%20Rolls/README.md)

## Description

<p>You are given an integer array <code>rolls</code> of length <code>n</code> and an integer <code>k</code>. You roll a <code>k</code> sided dice numbered from <code>1</code> to <code>k</code>, <code>n</code> times, where the result of the <code>i<sup>th</sup></code> roll is <code>rolls[i]</code>.</p>

<p>Return<em> the length of the <strong>shortest</strong> sequence of rolls that <strong>cannot</strong> be taken from </em><code>rolls</code>.</p>

<p>A <strong>sequence of rolls</strong> of length <code>len</code> is the result of rolling a <code>k</code> sided dice <code>len</code> times.</p>

<p><strong>Note</strong> that the sequence taken does not have to be consecutive as long as it is in order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> rolls = [4,2,1,2,3,3,2,4,1], k = 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> Every sequence of rolls of length 1, [1], [2], [3], [4], can be taken from rolls.
Every sequence of rolls of length 2, [1, 1], [1, 2], ..., [4, 4], can be taken from rolls.
The sequence [1, 4, 2] cannot be taken from rolls, so we return 3.
Note that there are other sequences that cannot be taken from rolls.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> rolls = [1,1,2,2], k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> Every sequence of rolls of length 1, [1], [2], can be taken from rolls.
The sequence [2, 1] cannot be taken from rolls, so we return 2.
Note that there are other sequences that cannot be taken from rolls but [2, 1] is the shortest.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> rolls = [1,1,3,2,2,2,3,3], k = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> The sequence [4] cannot be taken from rolls, so we return 1.
Note that there are other sequences that cannot be taken from rolls but [4] is the shortest.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == rolls.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= rolls[i] &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def shortestSequence(self, rolls: List[int], k: int) -> int:
        ans = 1
        s = set()
        for v in rolls:
            s.add(v)
            if len(s) == k:
                ans += 1
                s.clear()
        return ans
```

### **Java**

```java
class Solution {
    public int shortestSequence(int[] rolls, int k) {
        Set<Integer> s = new HashSet<>();
        int ans = 1;
        for (int v : rolls) {
            s.add(v);
            if (s.size() == k) {
                s.clear();
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
    int shortestSequence(vector<int>& rolls, int k) {
        unordered_set<int> s;
        int ans = 1;
        for (int v : rolls) {
            s.insert(v);
            if (s.size() == k) {
                s.clear();
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func shortestSequence(rolls []int, k int) int {
	s := map[int]bool{}
	ans := 1
	for _, v := range rolls {
		s[v] = true
		if len(s) == k {
			ans++
			s = map[int]bool{}
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
