# [1041. 困于环中的机器人](https://leetcode.cn/problems/robot-bounded-in-circle)

[English Version](/solution/1000-1099/1041.Robot%20Bounded%20In%20Circle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在无限的平面上，机器人最初位于&nbsp;<code>(0, 0)</code>&nbsp;处，面朝北方。注意:</p>

<ul>
	<li><strong>北方向</strong> 是y轴的正方向。</li>
	<li><strong>南方向</strong> 是y轴的负方向。</li>
	<li><strong>东方向</strong> 是x轴的正方向。</li>
	<li><strong>西方向</strong> 是x轴的负方向。</li>
</ul>

<p>机器人可以接受下列三条指令之一：</p>

<ul>
	<li><code>"G"</code>：直走 1 个单位</li>
	<li><code>"L"</code>：左转 90 度</li>
	<li><code>"R"</code>：右转 90 度</li>
</ul>

<p>机器人按顺序执行指令&nbsp;<code>instructions</code>，并一直重复它们。</p>

<p>只有在平面中存在环使得机器人永远无法离开时，返回&nbsp;<code>true</code>。否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>instructions = "GGLLGG"
<strong>输出：</strong>true
<strong>解释：</strong>机器人最初在(0,0)处，面向北方。
“G”:移动一步。位置:(0,1)方向:北。
“G”:移动一步。位置:(0,2).方向:北。
“L”:逆时针旋转90度。位置:(0,2).方向:西。
“L”:逆时针旋转90度。位置:(0,2)方向:南。
“G”:移动一步。位置:(0,1)方向:南。
“G”:移动一步。位置:(0,0)方向:南。
重复指令，机器人进入循环:(0,0)——&gt;(0,1)——&gt;(0,2)——&gt;(0,1)——&gt;(0,0)。
在此基础上，我们返回true。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>instructions = "GG"
<strong>输出：</strong>false
<strong>解释：</strong>机器人最初在(0,0)处，面向北方。
“G”:移动一步。位置:(0,1)方向:北。
“G”:移动一步。位置:(0,2).方向:北。
重复这些指示，继续朝北前进，不会进入循环。
在此基础上，返回false。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>instructions = "GL"
<strong>输出：</strong>true
<strong>解释：</strong>机器人最初在(0,0)处，面向北方。
“G”:移动一步。位置:(0,1)方向:北。
“L”:逆时针旋转90度。位置:(0,1).方向:西。
“G”:移动一步。位置:(- 1,1)方向:西。
“L”:逆时针旋转90度。位置:(- 1,1)方向:南。
“G”:移动一步。位置:(- 1,0)方向:南。
“L”:逆时针旋转90度。位置:(- 1,0)方向:东方。
“G”:移动一步。位置:(0,0)方向:东方。
“L”:逆时针旋转90度。位置:(0,0)方向:北。
重复指令，机器人进入循环:(0,0)——&gt;(0,1)——&gt;(- 1,1)——&gt;(- 1,0)——&gt;(0,0)。
在此基础上，我们返回true。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= instructions.length &lt;= 100</code></li>
	<li><code>instructions[i]</code>&nbsp;仅包含&nbsp;<code>'G', 'L', 'R'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们可以模拟机器人的行走过程，用一个变量 $k$ 表示机器人的方向，初始值为 $0$，表示机器人面向北方。变量 $k$ 的取值范围为 $[0, 3]$，分别表示机器人面向北、西、南、东。另外，我们用一个长度为 $4$ 的数组 $dist$ 记录机器人在四个方向上行走的距离，初始值为 $[0, 0, 0, 0]$。

遍历指令字符串 `instructions`，如果当前指令为 `'L'`，那么机器人转向西方，即 $k = (k + 1) \bmod 4$；如果当前指令为 `'R'`，那么机器人转向东方，即 $k = (k + 3) \bmod 4$；否则，机器人在当前方向上行走一步，即 $dist[k]++$。

如果给定的指令字符串 `instructions` 执行一遍后，使得机器人最终回到原点，即 $dist[0] = dist[2]$ 且 $dist[1] = dist[3]$，那么机器人一定会进入循环。因为无论重复多少次指令，机器人都回到了原点，所以机器人一定会进入循环。

如果给定的指令字符串 `instructions` 执行一遍后，机器人没回到原点，不妨假设此时机器人位于 $(x, y)$，且方向为 $k$。

-   若 $k=0$，即机器人面向北方，那么执行第二遍指令后，坐标变化量是 $(x, y)$；继续执行第三遍指令后，坐标变化量还是 $(x, y)$...累加这些变化量，机器人最终会到 $(n \times x, n \times y)$，其中 $n$ 是一个正整数。由于机器人最终没有回到原点，即 $x \neq 0$ 或 $y \neq 0$，所以 $n \times x \neq 0$ 或 $n \times y \neq 0$，因此机器人不会进入循环；
-   若 $k=1$，即机器人面向西方，那么机器人执行第二遍指令后，坐标变化量是 $(-y, x)$；继续执行第三遍执行后，坐标变化量是 $(-x, -y)$；继续执行第四遍指令后，坐标变化量是 $(y, -x)$。累加这些坐标变化量，我们可以发现，机器人最终会回到原点 $(0, 0)$；
-   若 $k=2$，即机器人面向南方，那么执行第二遍指令后，坐标变化量是 $(-x, -y)$，累加这两次坐标变化量，我们可以发现，机器人最终会回到原点 $(0, 0)$；
-   若 $k=3$，即机器人面向东方，那么执行第二遍指令后，坐标变化量是 $(y, -x)$；继续执行第三遍指令后，坐标变化量是 $(-x, -y)$；继续执行第四遍指令后，坐标变化量是 $(-y, x)$。累加这些坐标变化量，我们可以发现，机器人最终会回到原点 $(0, 0)$。

综上所述，如果给定的指令字符串 `instructions` 执行一遍后，机器人回到了原点，或者机器人的方向与初始方向不同，那么机器人一定会进入循环。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为指令字符串 `instructions` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isRobotBounded(self, instructions: str) -> bool:
        k = 0
        dist = [0] * 4
        for c in instructions:
            if c == 'L':
                k = (k + 1) % 4
            elif c == 'R':
                k = (k + 3) % 4
            else:
                dist[k] += 1
        return (dist[0] == dist[2] and dist[1] == dist[3]) or k != 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isRobotBounded(String instructions) {
        int k = 0;
        int[] dist = new int[4];
        for (int i = 0; i < instructions.length(); ++i) {
            char c = instructions.charAt(i);
            if (c == 'L') {
                k = (k + 1) % 4;
            } else if (c == 'R') {
                k = (k + 3) % 4;
            } else {
                ++dist[k];
            }
        }
        return (dist[0] == dist[2] && dist[1] == dist[3]) || (k != 0);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isRobotBounded(string instructions) {
        int dist[4]{};
        int k = 0;
        for (char& c : instructions) {
            if (c == 'L') {
                k = (k + 1) % 4;
            } else if (c == 'R') {
                k = (k + 3) % 4;
            } else {
                ++dist[k];
            }
        }
        return (dist[0] == dist[2] && dist[1] == dist[3]) || k;
    }
};
```

### **Go**

```go
func isRobotBounded(instructions string) bool {
	dist := [4]int{}
	k := 0
	for _, c := range instructions {
		if c == 'L' {
			k = (k + 1) % 4
		} else if c == 'R' {
			k = (k + 3) % 4
		} else {
			dist[k]++
		}
	}
	return (dist[0] == dist[2] && dist[1] == dist[3]) || k != 0
}
```

### **TypeScript**

```ts
function isRobotBounded(instructions: string): boolean {
    const dist: number[] = new Array(4).fill(0);
    let k = 0;
    for (const c of instructions) {
        if (c === 'L') {
            k = (k + 1) % 4;
        } else if (c === 'R') {
            k = (k + 3) % 4;
        } else {
            ++dist[k];
        }
    }
    return (dist[0] === dist[2] && dist[1] === dist[3]) || k !== 0;
}
```

### **...**

```

```

<!-- tabs:end -->
