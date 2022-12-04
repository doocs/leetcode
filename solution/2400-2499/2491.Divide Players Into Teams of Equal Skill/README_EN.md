# [2491. Divide Players Into Teams of Equal Skill](https://leetcode.com/problems/divide-players-into-teams-of-equal-skill)

[中文文档](/solution/2400-2499/2491.Divide%20Players%20Into%20Teams%20of%20Equal%20Skill/README.md)

## Description

<p>You are given a positive integer array <code>skill</code> of <strong>even</strong> length <code>n</code> where <code>skill[i]</code> denotes the skill of the <code>i<sup>th</sup></code> player. Divide the players into <code>n / 2</code> teams of size <code>2</code> such that the total skill of each team is <strong>equal</strong>.</p>

<p>The <strong>chemistry</strong> of a team is equal to the <strong>product</strong> of the skills of the players on that team.</p>

<p>Return <em>the sum of the <strong>chemistry</strong> of all the teams, or return </em><code>-1</code><em> if there is no way to divide the players into teams such that the total skill of each team is equal.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> skill = [3,2,5,1,3,4]
<strong>Output:</strong> 22
<strong>Explanation:</strong> 
Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> skill = [3,4]
<strong>Output:</strong> 12
<strong>Explanation:</strong> 
The two players form a team with a total skill of 7.
The chemistry of the team is 3 * 4 = 12.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> skill = [1,1,2,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> 
There is no way to divide the players into teams such that the total skill of each team is equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= skill.length &lt;= 10<sup>5</sup></code></li>
	<li><code>skill.length</code> is even.</li>
	<li><code>1 &lt;= skill[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def dividePlayers(self, skill: List[int]) -> int:
        skill.sort()
        t = skill[0] + skill[-1]
        i, j = 0, len(skill) - 1
        ans = 0
        while i < j:
            if skill[i] + skill[j] != t:
                return -1
            ans += skill[i] * skill[j]
            i, j = i + 1, j - 1
        return ans
```

### **Java**

```java
class Solution {
    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);
        int n = skill.length;
        int t = skill[0] + skill[n - 1];
        long ans = 0;
        for (int i = 0, j = n - 1; i < j; ++i, --j) {
            if (skill[i] + skill[j] != t) {
                return -1;
            }
            ans += (long) skill[i] * skill[j];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long dividePlayers(vector<int>& skill) {
        sort(skill.begin(), skill.end());
        int n = skill.size();
        int t = skill[0] + skill[n - 1];
        long long ans = 0;
        for (int i = 0, j = n - 1; i < j; ++i, --j) {
            if (skill[i] + skill[j] != t) return -1;
            ans += 1ll * skill[i] * skill[j];
        }
        return ans;
    }
};
```

### **Go**

```go
func dividePlayers(skill []int) (ans int64) {
	sort.Ints(skill)
	n := len(skill)
	t := skill[0] + skill[n-1]
	for i, j := 0, n-1; i < j; i, j = i+1, j-1 {
		if skill[i]+skill[j] != t {
			return -1
		}
		ans += int64(skill[i] * skill[j])
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
