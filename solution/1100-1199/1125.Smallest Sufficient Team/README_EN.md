# [1125. Smallest Sufficient Team](https://leetcode.com/problems/smallest-sufficient-team)

[中文文档](/solution/1100-1199/1125.Smallest%20Sufficient%20Team/README.md)

## Description

<p>In a project, you have a list of required skills <code>req_skills</code>, and a list of people. The <code>i<sup>th</sup></code> person <code>people[i]</code> contains a list of skills that the person has.</p>

<p>Consider a sufficient team: a set of people such that for every required skill in <code>req_skills</code>, there is at least one person in the team who has that skill. We can represent these teams by the index of each person.</p>

<ul>
	<li>For example, <code>team = [0, 1, 3]</code> represents the people with skills <code>people[0]</code>, <code>people[1]</code>, and <code>people[3]</code>.</li>
</ul>

<p>Return <em>any sufficient team of the smallest possible size, represented by the index of each person</em>. You may return the answer in <strong>any order</strong>.</p>

<p>It is <strong>guaranteed</strong> an answer exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
<strong>Output:</strong> [0,2]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
<strong>Output:</strong> [1,2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= req_skills.length &lt;= 16</code></li>
	<li><code>1 &lt;= req_skills[i].length &lt;= 16</code></li>
	<li><code>req_skills[i]</code> consists of lowercase English letters.</li>
	<li>All the strings of <code>req_skills</code> are <strong>unique</strong>.</li>
	<li><code>1 &lt;= people.length &lt;= 60</code></li>
	<li><code>0 &lt;= people[i].length &lt;= 16</code></li>
	<li><code>1 &lt;= people[i][j].length &lt;= 16</code></li>
	<li><code>people[i][j]</code> consists of lowercase English letters.</li>
	<li>All the strings of <code>people[i]</code> are <strong>unique</strong>.</li>
	<li>Every skill in <code>people[i]</code> is a skill in <code>req_skills</code>.</li>
	<li>It is guaranteed a sufficient team exists.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def smallestSufficientTeam(self, req_skills: List[str], people: List[List[str]]) -> List[int]:
        @cache
        def dfs(i, state):
            if i == n:
                return [] if state == (1 << m) - 1 else None
            ans1 = dfs(i + 1, state)
            ans2 = dfs(i + 1, state | ps[i])
            if ans1 is None and ans2 is None:
                return None
            if ans1 is None:
                return [i] + ans2
            if ans2 is None:
                return ans1
            return min(ans1, [i] + ans2, key=len)

        d = {s: i for i, s in enumerate(req_skills)}
        m = len(req_skills)
        n = len(people)
        ps = [0] * n
        for i, skills in enumerate(people):
            for skill in skills:
                ps[i] |= 1 << d[skill]
        return dfs(0, 0)
```

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private int[] ps;
    private int[][][] f;
    private static final int MX = 100;

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        m = req_skills.length;
        n = people.size();
        ps = new int[n];
        f = new int[n][1 << m][];
        Map<String, Integer> d = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            d.put(req_skills[i], i);
        }
        for (int i = 0; i < n; ++i) {
            for (String skill : people.get(i)) {
                ps[i] |= 1 << d.get(skill);
            }
        }
        return dfs(0, 0);
    }

    private int[] dfs(int i, int state) {
        if (i == n) {
            return state == (1 << m) - 1 ? new int[0] : add(new int[0], MX);
        }
        if (f[i][state] != null) {
            return f[i][state];
        }
        int[] ans1 = dfs(i + 1, state);
        int[] ans2 = dfs(i + 1, state | ps[i]);
        if (ans1.length > 0 && ans1[0] == MX && ans2.length > 0 && ans2[0] == MX) {
            return f[i][state] = ans1;
        }
        if (ans1.length > 0 && ans1[0] == MX) {
            return f[i][state] = add(ans2, i);
        }
        if (ans2.length > 0 && ans2[0] == MX) {
            return f[i][state] = ans1;
        }
        if (ans1.length < ans2.length + 1) {
            return f[i][state] = ans1;
        }
        return f[i][state] = add(ans2, i);
    }

    private int[] add(int[] nums, int x) {
        int[] copy = Arrays.copyOf(nums, nums.length + 1);
        copy[copy.length - 1] = x;
        return copy;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
