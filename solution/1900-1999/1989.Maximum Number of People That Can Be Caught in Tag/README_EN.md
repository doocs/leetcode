# [1989. Maximum Number of People That Can Be Caught in Tag](https://leetcode.com/problems/maximum-number-of-people-that-can-be-caught-in-tag)

[中文文档](/solution/1900-1999/1989.Maximum%20Number%20of%20People%20That%20Can%20Be%20Caught%20in%20Tag/README.md)

## Description

<p>You are playing a game of tag with your friends. In tag, people are divided into two teams: people who are &quot;it&quot;, and people who are not &quot;it&quot;. The people who are &quot;it&quot; want to catch as many people as possible who are not &quot;it&quot;.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code>team</code> containing only zeros (denoting people who are <strong>not</strong> &quot;it&quot;) and ones (denoting people who are &quot;it&quot;), and an integer <code>dist</code>. A person who is &quot;it&quot; at index <code>i</code> can catch any <strong>one</strong> person whose index is in the range <code>[i - dist, i + dist]</code> (<strong>inclusive</strong>) and is <strong>not</strong> &quot;it&quot;.</p>

<p>Return <em>the <strong>maximum</strong> number of people that the people who are &quot;it&quot; can catch</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> team = [0,1,0,1,0], dist = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The person who is &quot;it&quot; at index 1 can catch people in the range [i-dist, i+dist] = [1-3, 1+3] = [-2, 4].
They can catch the person who is not &quot;it&quot; at index 2.
The person who is &quot;it&quot; at index 3 can catch people in the range [i-dist, i+dist] = [3-3, 3+3] = [0, 6].
They can catch the person who is not &quot;it&quot; at index 0.
The person who is not &quot;it&quot; at index 4 will not be caught because the people at indices 1 and 3 are already catching one person.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> team = [1], dist = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong>
There are no people who are not &quot;it&quot; to catch.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> team = [0], dist = 1
<strong>Output:</strong> 0
<strong>Explanation:
</strong>There are no people who are &quot;it&quot; to catch people.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= team.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= team[i] &lt;= 1</code></li>
	<li><code>1 &lt;= dist &lt;= team.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def catchMaximumAmountofPeople(self, team: List[int], dist: int) -> int:
        ans = j = 0
        n = len(team)
        for i, x in enumerate(team):
            if x:
                while j < n and (team[j] or i - j > dist):
                    j += 1
                if j < n and abs(i - j) <= dist:
                    ans += 1
                    j += 1
        return ans
```

### **Java**

```java
class Solution {
    public int catchMaximumAmountofPeople(int[] team, int dist) {
        int ans = 0;
        int n = team.length;
        for (int i = 0, j = 0; i < n; ++i) {
            if (team[i] == 1) {
                while (j < n && (team[j] == 1 || i - j > dist)) {
                    ++j;
                }
                if (j < n && Math.abs(i - j) <= dist) {
                    ++ans;
                    ++j;
                }
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
    int catchMaximumAmountofPeople(vector<int>& team, int dist) {
        int ans = 0;
        int n = team.size();
        for (int i = 0, j = 0; i < n; ++i) {
            if (team[i]) {
                while (j < n && (team[j] || i - j > dist)) {
                    ++j;
                }
                if (j < n && abs(i -  j) <= dist) {
                    ++ans;
                    ++j;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func catchMaximumAmountofPeople(team []int, dist int) (ans int) {
	n := len(team)
	for i, j := 0, 0; i < n; i++ {
		if team[i] == 1 {
			for j < n && (team[j] == 1 || i-j > dist) {
				j++
			}
			if j < n && abs(i-j) <= dist {
				ans++
				j++
			}
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

### **...**

```

```

<!-- tabs:end -->
