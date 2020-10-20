# [1125. 最小的必要团队](https://leetcode-cn.com/problems/smallest-sufficient-team)

[English Version](/solution/1100-1199/1125.Smallest%20Sufficient%20Team/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>作为项目经理，你规划了一份需求的技能清单&nbsp;<code>req_skills</code>，并打算从备选人员名单&nbsp;<code>people</code>&nbsp;中选出些人组成一个「必要团队」（ 编号为&nbsp;<code>i</code>&nbsp;的备选人员&nbsp;<code>people[i]</code>&nbsp;含有一份该备选人员掌握的技能列表）。</p>

<p>所谓「必要团队」，就是在这个团队中，对于所需求的技能列表&nbsp;<code>req_skills</code> 中列出的每项技能，团队中至少有一名成员已经掌握。</p>

<p>我们可以用每个人的编号来表示团队中的成员：例如，团队&nbsp;<code>team = [0, 1, 3]</code>&nbsp;表示掌握技能分别为&nbsp;<code>people[0]</code>，<code>people[1]</code>，和&nbsp;<code>people[3]</code>&nbsp;的备选人员。</p>

<p>请你返回 <strong>任一</strong>&nbsp;规模最小的必要团队，团队成员用人员编号表示。你可以按任意顺序返回答案，本题保证答案存在。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>req_skills = [&quot;java&quot;,&quot;nodejs&quot;,&quot;reactjs&quot;], people = [[&quot;java&quot;],[&quot;nodejs&quot;],[&quot;nodejs&quot;,&quot;reactjs&quot;]]
<strong>输出：</strong>[0,2]
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre><strong>输入：</strong>req_skills = [&quot;algorithms&quot;,&quot;math&quot;,&quot;java&quot;,&quot;reactjs&quot;,&quot;csharp&quot;,&quot;aws&quot;], people = [[&quot;algorithms&quot;,&quot;math&quot;,&quot;java&quot;],[&quot;algorithms&quot;,&quot;math&quot;,&quot;reactjs&quot;],[&quot;java&quot;,&quot;csharp&quot;,&quot;aws&quot;],[&quot;reactjs&quot;,&quot;csharp&quot;],[&quot;csharp&quot;,&quot;math&quot;],[&quot;aws&quot;,&quot;java&quot;]]
<strong>输出：</strong>[1,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= req_skills.length &lt;= 16</code></li>
	<li><code>1 &lt;= people.length &lt;= 60</code></li>
	<li><code>1 &lt;= people[i].length, req_skills[i].length, people[i][j].length&nbsp;&lt;= 16</code></li>
	<li><code>req_skills</code>&nbsp;和&nbsp;<code>people[i]</code>&nbsp;中的元素分别各不相同</li>
	<li><code>req_skills[i][j], people[i][j][k]</code>&nbsp;都由小写英文字母组成</li>
	<li>本题保证「必要团队」一定存在</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
