# [489. 扫地机器人](https://leetcode-cn.com/problems/robot-room-cleaner)

[English Version](/solution/0400-0499/0489.Robot%20Room%20Cleaner/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>房间（用格栅表示）中有一个扫地机器人。格栅中的每一个格子有空和障碍物两种可能。</p>

<p>扫地机器人提供4个API，可以向前进，向左转或者向右转。每次转弯90度。</p>

<p>当扫地机器人试图进入障碍物格子时，它的碰撞传感器会探测出障碍物，使它停留在原地。</p>

<p>请利用提供的4个API编写让机器人清理整个房间的算法。</p>

<pre>interface Robot {
&nbsp; // 若下一个方格为空，则返回true，并移动至该方格
&nbsp; // 若下一个方格为障碍物，则返回false，并停留在原地
&nbsp; boolean move();

  // 在调用turnLeft/turnRight后机器人会停留在原位置
&nbsp; // 每次转弯90度
&nbsp; void turnLeft();
&nbsp; void turnRight();

  // 清理所在方格
  void clean();
}
</pre>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong>
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

<strong>解析:</strong>
房间格栅用0或1填充。0表示障碍物，1表示可以通过。
机器人从row=1，col=3的初始位置出发。在左上角的一行以下，三列以右。
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>输入只用于初始化房间和机器人的位置。你需要&ldquo;盲解&rdquo;这个问题。换而言之，你必须在对房间和机器人位置一无所知的情况下，只使用4个给出的API解决问题。&nbsp;</li>
	<li>扫地机器人的初始位置一定是空地。</li>
	<li>扫地机器人的初始方向向上。</li>
	<li>所有可抵达的格子都是相连的，亦即所有标记为1的格子机器人都可以抵达。</li>
	<li>可以假定格栅的四周都被墙包围。</li>
</ol>

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
