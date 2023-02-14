# [1125. 最小的必要团队](https://leetcode.cn/problems/smallest-sufficient-team)

[English Version](/solution/1100-1199/1125.Smallest%20Sufficient%20Team/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>作为项目经理，你规划了一份需求的技能清单 <code>req_skills</code>，并打算从备选人员名单 <code>people</code> 中选出些人组成一个「必要团队」（ 编号为 <code>i</code> 的备选人员 <code>people[i]</code> 含有一份该备选人员掌握的技能列表）。</p>

<p>所谓「必要团队」，就是在这个团队中，对于所需求的技能列表 <code>req_skills</code> 中列出的每项技能，团队中至少有一名成员已经掌握。可以用每个人的编号来表示团队中的成员：</p>

<ul>
	<li>例如，团队 <code>team = [0, 1, 3]</code> 表示掌握技能分别为 <code>people[0]</code>，<code>people[1]</code>，和 <code>people[3]</code> 的备选人员。</li>
</ul>

<p>请你返回 <strong>任一</strong> 规模最小的必要团队，团队成员用人员编号表示。你可以按 <strong>任意顺序</strong> 返回答案，题目数据保证答案存在。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
<strong>输出：</strong>[0,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
<strong>输出：</strong>[1,2]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= req_skills.length <= 16</code></li>
	<li><code>1 <= req_skills[i].length <= 16</code></li>
	<li><code>req_skills[i]</code> 由小写英文字母组成</li>
	<li><code>req_skills</code> 中的所有字符串 <strong>互不相同</strong></li>
	<li><code>1 <= people.length <= 60</code></li>
	<li><code>0 <= people[i].length <= 16</code></li>
	<li><code>1 <= people[i][j].length <= 16</code></li>
	<li><code>people[i][j]</code> 由小写英文字母组成</li>
	<li><code>people[i]</code> 中的所有字符串 <strong>互不相同</strong></li>
	<li><code>people[i]</code> 中的每个技能是 <code>req_skills</code> 中的技能</li>
	<li>题目数据保证「必要团队」一定存在</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩 + 记忆化搜索**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
