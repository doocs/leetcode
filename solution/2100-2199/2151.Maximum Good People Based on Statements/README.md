# [2151. 基于陈述统计最多好人数](https://leetcode-cn.com/problems/maximum-good-people-based-on-statements)

[English Version](/solution/2100-2199/2151.Maximum%20Good%20People%20Based%20on%20Statements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>游戏中存在两种角色：</p>

<ul>
	<li><strong>好人</strong>：该角色只说真话。</li>
	<li><strong>坏人</strong>：该角色可能说真话，也可能说假话。</li>
</ul>

<p>给你一个下标从 <strong>0</strong> 开始的二维整数数组 <code>statements</code> ，大小为 <code>n x n</code> ，表示 <code>n</code> 个玩家对彼此角色的陈述。具体来说，<code>statements[i][j]</code> 可以是下述值之一：</p>

<ul>
	<li><code>0</code> 表示 <code>i</code> 的陈述认为 <code>j</code> 是 <strong>坏人</strong> 。</li>
	<li><code>1</code> 表示 <code>i</code> 的陈述认为 <code>j</code> 是 <strong>好人</strong> 。</li>
	<li><code>2</code> 表示 <code>i</code> 没有对 <code>j</code> 作出陈述。</li>
</ul>

<p>另外，玩家不会对自己进行陈述。形式上，对所有&nbsp;<code>0 &lt;= i &lt; n</code> ，都有 <code>statements[i][i] = 2</code> 。</p>

<p>根据这 <code>n</code> 个玩家的陈述，返回可以认为是 <strong>好人</strong> 的 <strong>最大</strong> 数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2151.Maximum%20Good%20People%20Based%20on%20Statements/images/logic1.jpg" style="width: 600px; height: 262px;">
<pre><strong>输入：</strong>statements = [[2,1,2],[1,2,2],[2,0,2]]
<strong>输出：</strong>2
<strong>解释：</strong>每个人都做一条陈述。
- 0 认为 1 是好人。
- 1 认为 0 是好人。
- 2 认为 1 是坏人。
以 2 为突破点。
- 假设 2 是一个好人：
    - 基于 2 的陈述，1 是坏人。
    - 那么可以确认 1 是坏人，2 是好人。
    - 基于 1 的陈述，由于 1 是坏人，那么他在陈述时可能：
        - 说真话。在这种情况下会出现矛盾，所以假设无效。
        - 说假话。在这种情况下，0 也是坏人并且在陈述时说假话。
    - <strong>在认为 2 是好人的情况下，这组玩家中只有一个好人。</strong>
- 假设 2 是一个坏人：
    - 基于 2 的陈述，由于 2 是坏人，那么他在陈述时可能：
        - 说真话。在这种情况下，0 和 1 都是坏人。
            - <strong>在认为 2 是坏人但说真话的情况下，这组玩家中没有一个好人。</strong>
        - 说假话。在这种情况下，1 是好人。
            - 由于 1 是好人，0 也是好人。
            - <strong>在认为 2 是坏人且说假话的情况下，这组玩家中有两个好人。</strong>
在最佳情况下，至多有两个好人，所以返回 2 。
注意，能得到此结论的方法不止一种。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2151.Maximum%20Good%20People%20Based%20on%20Statements/images/logic2.jpg" style="width: 600px; height: 262px;">
<pre><strong>输入：</strong>statements = [[2,0],[0,2]]
<strong>输出：</strong>1
<strong>解释：</strong>每个人都做一条陈述。
- 0 认为 1 是坏人。
- 1 认为 0 是坏人。
以 0 为突破点。
- 假设 0 是一个好人：
    - 基于与 0 的陈述，1 是坏人并说假话。
    - <strong>在认为 0 是好人的情况下，这组玩家中只有一个好人。</strong>
- 假设 0 是一个坏人：
    - 基于 0 的陈述，由于 0 是坏人，那么他在陈述时可能：
        - 说真话。在这种情况下，0 和 1 都是坏人。
            - <strong>在认为 0 是坏人但说真话的情况下，这组玩家中没有一个好人。</strong>
        - 说假话。在这种情况下，1 是好人。
            - <strong>在认为 0 是坏人且说假话的情况下，这组玩家中只有一个好人。</strong>
在最佳情况下，至多有一个好人，所以返回 1 。 
注意，能得到此结论的方法不止一种。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == statements.length == statements[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 15</code></li>
	<li><code>statements[i][j]</code> 的值为 <code>0</code>、<code>1</code> 或 <code>2</code></li>
	<li><code>statements[i][i] == 2</code></li>
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
