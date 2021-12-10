# [1041. 困于环中的机器人](https://leetcode-cn.com/problems/robot-bounded-in-circle)

[English Version](/solution/1000-1099/1041.Robot%20Bounded%20In%20Circle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在无限的平面上，机器人最初位于&nbsp;<code>(0, 0)</code>&nbsp;处，面朝北方。机器人可以接受下列三条指令之一：</p>

<ul>
	<li><code>&quot;G&quot;</code>：直走 1 个单位</li>
	<li><code>&quot;L&quot;</code>：左转 90 度</li>
	<li><code>&quot;R&quot;</code>：右转 90 度</li>
</ul>

<p>机器人按顺序执行指令&nbsp;<code>instructions</code>，并一直重复它们。</p>

<p>只有在平面中存在环使得机器人永远无法离开时，返回&nbsp;<code>true</code>。否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>&quot;GGLLGG&quot;
<strong>输出：</strong>true
<strong>解释：</strong>
机器人从 (0,0) 移动到 (0,2)，转 180 度，然后回到 (0,0)。
重复这些指令，机器人将保持在以原点为中心，2 为半径的环中进行移动。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>&quot;GG&quot;
<strong>输出：</strong>false
<strong>解释：</strong>
机器人无限向北移动。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>&quot;GL&quot;
<strong>输出：</strong>true
<strong>解释：</strong>
机器人按 (0, 0) -&gt; (0, 1) -&gt; (-1, 1) -&gt; (-1, 0) -&gt; (0, 0) -&gt; ... 进行移动。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= instructions.length &lt;= 100</code></li>
	<li><code>instructions[i]</code> 在&nbsp;<code>{&#39;G&#39;, &#39;L&#39;, &#39;R&#39;}</code>&nbsp;中</li>
</ol>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isRobotBounded(self, instructions: str) -> bool:
        direction = [0] * 4
        cur = 0
        for i in range(len(instructions)):
            if instructions[i] == 'L':
                cur = cur + 1 if cur < 3 else 0
            elif instructions[i] == 'R':
                cur = cur - 1 if cur > 0 else 3
            else:
                direction[cur] += 1
        return cur != 0 or (direction[0] == direction[2] and direction[1] == direction[3])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isRobotBounded(String instructions) {
        int[] direction = new int[4];
        int cur = 0;
        char[] chars = instructions.toCharArray();
        for (char c : chars) {
            if (c == 'L') {
                cur = cur < 3 ? cur + 1 : 0;
            } else if (c == 'R') {
                cur = cur > 0 ? cur - 1 : 3;
            } else {
                direction[cur] += 1;
            }
        }
        return cur != 0 || (direction[0] == direction[2] && direction[1] == direction[3]);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
