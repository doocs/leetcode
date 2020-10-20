# [789. 逃脱阻碍者](https://leetcode-cn.com/problems/escape-the-ghosts)

[English Version](/solution/0700-0799/0789.Escape%20The%20Ghosts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>你在进行一个简化版的吃豆人游戏。你从&nbsp;<code>(0, 0)</code>&nbsp;点开始出发，你的目的地是&nbsp;<code>(target[0], target[1])</code>&nbsp;。地图上有一些阻碍者，第 i 个阻碍者从&nbsp;<code>(ghosts[i][0], ghosts[i][1])</code>&nbsp;出发。</p>

<p>每一回合，你和阻碍者们*可以*同时向东，西，南，北四个方向移动，每次可以移动到距离原位置1个单位的新位置。</p>

<p>如果你可以在任何阻碍者抓住你之前到达目的地（阻碍者可以采取任意行动方式），则被视为逃脱成功。如果你和阻碍者同时到达了一个位置（包括目的地）都不算是逃脱成功。</p>

<p>当且仅当你有可能成功逃脱时，输出 True。</p>

<pre><strong>示例 1:</strong>
<strong>输入：</strong> 
ghosts = [[1, 0], [0, 3]]
target = [0, 1]
<strong>输出：</strong>true
<strong>解释：
</strong>你可以直接一步到达目的地(0,1)，在(1, 0)或者(0, 3)位置的阻碍者都不可能抓住你。 
</pre>

<pre><strong>示例 2:</strong>
<strong>输入：</strong> 
ghosts = [[1, 0]]
target = [2, 0]
<strong>输出：</strong>false
<strong>解释：</strong>
你需要走到位于(2, 0)的目的地，但是在(1, 0)的阻碍者位于你和目的地之间。 
</pre>

<pre><strong>示例 3:</strong>
<strong>输入：</strong> 
ghosts = [[2, 0]]
target = [1, 0]
<strong>输出：</strong>false
<strong>解释：
</strong>阻碍者可以和你同时达到目的地。 
</pre>

<p><strong>说明：</strong></p>

<ul>
	<li>所有的点的坐标值的绝对值 &lt;=&nbsp;<code>10000</code>。</li>
	<li>阻碍者的数量不会超过&nbsp;<code>100</code>。</li>
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
