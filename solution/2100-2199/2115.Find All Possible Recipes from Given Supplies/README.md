# [2115. 从给定原材料中找到所有可以做出的菜](https://leetcode.cn/problems/find-all-possible-recipes-from-given-supplies)

[English Version](/solution/2100-2199/2115.Find%20All%20Possible%20Recipes%20from%20Given%20Supplies/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有 <code>n</code>&nbsp;道不同菜的信息。给你一个字符串数组&nbsp;<code>recipes</code>&nbsp;和一个二维字符串数组&nbsp;<code>ingredients</code>&nbsp;。第&nbsp;<code>i</code>&nbsp;道菜的名字为&nbsp;<code>recipes[i]</code>&nbsp;，如果你有它&nbsp;<strong>所有</strong>&nbsp;的原材料&nbsp;<code>ingredients[i]</code>&nbsp;，那么你可以&nbsp;<strong>做出</strong>&nbsp;这道菜。一道菜的原材料可能是&nbsp;<strong>另一道</strong>&nbsp;菜，也就是说&nbsp;<code>ingredients[i]</code>&nbsp;可能包含&nbsp;<code>recipes</code>&nbsp;中另一个字符串。</p>

<p>同时给你一个字符串数组&nbsp;<code>supplies</code>&nbsp;，它包含你初始时拥有的所有原材料，每一种原材料你都有无限多。</p>

<p>请你返回你可以做出的所有菜。你可以以 <strong>任意顺序</strong>&nbsp;返回它们。</p>

<p>注意两道菜在它们的原材料中可能互相包含。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
<b>输出：</b>["bread"]
<strong>解释：</strong>
我们可以做出 "bread" ，因为我们有原材料 "yeast" 和 "flour" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
<b>输出：</b>["bread","sandwich"]
<strong>解释：</strong>
我们可以做出 "bread" ，因为我们有原材料 "yeast" 和 "flour" 。
我们可以做出 "sandwich" ，因为我们有原材料 "meat" 且可以做出原材料 "bread" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
<b>输出：</b>["bread","sandwich","burger"]
<strong>解释：</strong>
我们可以做出 "bread" ，因为我们有原材料 "yeast" 和 "flour" 。
我们可以做出 "sandwich" ，因为我们有原材料 "meat" 且可以做出原材料 "bread" 。
我们可以做出 "burger" ，因为我们有原材料 "meat" 且可以做出原材料 "bread" 和 "sandwich" 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><b>输入：</b>recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast"]
<b>输出：</b>[]
<strong>解释：</strong>
我们没法做出任何菜，因为我们只有原材料 "yeast" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == recipes.length == ingredients.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= ingredients[i].length, supplies.length &lt;= 100</code></li>
	<li><code>1 &lt;= recipes[i].length, ingredients[i][j].length, supplies[k].length &lt;= 10</code></li>
	<li><code>recipes[i], ingredients[i][j]</code>&nbsp;和&nbsp;<code>supplies[k]</code>&nbsp;只包含小写英文字母。</li>
	<li>所有&nbsp;<code>recipes</code> 和&nbsp;<code>supplies</code>&nbsp;中的值互不相同。</li>
	<li><code>ingredients[i]</code>&nbsp;中的字符串互不相同。</li>
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

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
